package com.work1.cracks.controlers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.AbstractFileResolvingResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.yaml.snakeyaml.events.Event;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.work1.cracks.dtos.EventDto;
import com.work1.cracks.modelos.Events;
import com.work1.cracks.modelos.User;
import com.work1.cracks.modelos.aux.Coordenadas;
import com.work1.cracks.repos.RepoEvents;
import com.work1.cracks.repos.RepoUser;
import com.work1.cracks.repos.aux.RepoCategoryEvents;
import com.work1.cracks.repos.aux.RepoCoordenadas;
import com.work1.cracks.repos.aux.RepoStatusEvents;
import com.work1.cracks.servicios.GoalSportsService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@RestController
public class EventsController {
    @Autowired
    private RepoEvents repoEvents;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private GoalSportsService goalSportsService;

    @Autowired
    private RepoUser repoUser;

    @Autowired
    private RepoStatusEvents repoStatus;

    @Autowired
    private RepoCategoryEvents repoCategory;
    @Autowired
    private RepoCoordenadas repoCoordenadas;

    @GetMapping("/pullEvents/{pagina}/{cantidad}")
    public ResponseEntity<ArrayList<Events>> pullEvents(@PathVariable int cantidad, @PathVariable int pagina) {
        PageRequest page = PageRequest.of(pagina, cantidad);
        ArrayList<Events> lista = (ArrayList<Events>) repoEvents.findPage(page);
        for (Events e : lista) {

            e.setGoals(goalSportsService.getEventsGoals(e.getId()));

            e.setSports(goalSportsService.getEventsSports(e.getId()));
        }
        return new ResponseEntity<ArrayList<Events>>(lista, HttpStatus.OK);
    }

    @GetMapping("/pullEventById/{id}")
    public ResponseEntity<Events> events(@PathVariable Long id) {
        Events e = repoEvents.fiXIde(id);
        e.setGoals(goalSportsService.getEventsGoals(id));
        e.setSports(goalSportsService.getEventsSports(id));

        return new ResponseEntity<Events>(e, HttpStatus.OK);
    }
    @Operation(summary = "Carga de evento", description = "{'nombre':'dasdas','nombre3':'dasdas'}")

    @PostMapping("/postEvent")
    public ResponseEntity<String> postEvent(@RequestBody EventDto e) {
        // ObjectMapper om=new ObjectMapper();
        // EventDto e=om.readValue(event,EventDto.class);
        Events nuevo = new Events(e);
        Coordenadas coords = new Coordenadas(e.getLocaltionLat(), e.getLocaltionLon(), e.getLocation_description(),
                e.getLocation_address());
        repoCoordenadas.save(coords);
        nuevo.setLocation(coords);
        nuevo.setUser(repoUser.findById(e.getUser_id()).get());
        nuevo.setStatus(repoStatus.findById(e.getStatus_id()).get());
        nuevo.setCategory(repoCategory.findById(e.getCategory()).get());
        nuevo.setRegisterDate(LocalDateTime.now());
        repoEvents.save(nuevo);
        return new ResponseEntity<String>("{'id':" + nuevo.getId() + "}", HttpStatus.OK);

    }

    @PostMapping(value = "/eventPicture", consumes = "multipart/form-data")
    public void eventPicture(@RequestParam("id") Long id, @RequestParam("picture") MultipartFile picture)
            throws IOException {
        Path path = Paths.get("bd/eventPictures/" + id + ".jpg");
        Files.createDirectories(path.getParent());
        byte[] pictureB = picture.getBytes();
        Files.write(path, pictureB);

        Events e=repoEvents.findById(id).get();
        e.setPicture(path.getFileName().toString());
        System.out.println("\n\n"+path.toString()+"\n\n");
        repoEvents.save(e);

    }

    @GetMapping("/eventPicture/{id}")
    public ResponseEntity<Resource> eventPicture(@PathVariable Long id) throws MalformedURLException {
        Path path = Paths.get("bd/eventPictures/").resolve(id + ".jpg");
        Resource resource = new UrlResource(path.toUri());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG); // Cambia el tipo de contenido

     
        return new ResponseEntity<>(resource, headers, HttpStatus.OK);

    }

}
