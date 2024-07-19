package com.work1.cracks.controlers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.work1.cracks.dtos.PullGoalsDto;
import com.work1.cracks.modelos.Events;
import com.work1.cracks.repos.RepoEvents;
import com.work1.cracks.repos.RepoInterest;
import com.work1.cracks.servicios.GoalSportsService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@RestController
public class EventsController {
    @Autowired
    private RepoEvents repoEvents;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private GoalSportsService goalSportsService;

    @GetMapping("/pullEvents/{pagina}/{cantidad}")
    public ResponseEntity<ArrayList<Events>> pullEvents(@PathVariable int cantidad, @PathVariable int pagina) {
        PageRequest page=PageRequest.of(pagina,cantidad);
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

}
