package com.work1.cracks.controlers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.work1.cracks.dtos.ParticipantsDto;
import com.work1.cracks.modelos.Participants;
import com.work1.cracks.repos.RepoEvents;
import com.work1.cracks.repos.RepoParticipants;
import com.work1.cracks.repos.RepoUser;
import com.work1.cracks.repos.aux.RepoRoleParticipants;
import com.work1.cracks.repos.aux.RepoStatusParticipants;
import com.work1.cracks.servicios.GoalSportsService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Participantes",description = "Controladores de lo referente a la participación de los usuarios en los eventos")
@RestController
public class ParticipantsController {
    @Autowired
    private RepoParticipants repoParticipants;
    @Autowired
    private GoalSportsService goalSportsService;
    @Autowired
    RepoUser repoUser;
    @Autowired
    RepoEvents repoEvents;
    @Autowired
    RepoStatusParticipants repoStatus;
    @Autowired
    RepoRoleParticipants repoRole;

    @Operation(summary = "Sobre eventos finalizados",description = "Lista los eventos finalizados de los cuales estuvo vinculado.")
    @ApiResponse(responseCode = "200", description = "Se pudo entregar el listado")
    @ApiResponse(responseCode = "401", description = "No esta autorizado")
    @GetMapping("/pullPassedEventsByUser/{id}")
    public List<Participants> passedEventByUser( @PathVariable Long id) {
        List<Participants> lista = repoParticipants.findPasadosById(id);
        for (Participants p : lista) {
            p.getEvent().setGoals(goalSportsService.getEventsGoals(p.getEvent().getId()));
            p.getEvent().setSports(goalSportsService.getEventsSports(p.getEvent().getId()));
        }

        return lista;
    }

    @GetMapping("/pullParticipantsEventById/{id}")
    public ResponseEntity<List<Participants>> pullParticipantsById(@PathVariable Long id) {
        return new ResponseEntity<List<Participants>>(repoParticipants.findByUserId(id), HttpStatus.OK);
    }

    @PostMapping("/pushParticipant")
    public ResponseEntity<String> pushParticipant(@RequestBody ParticipantsDto dto) {
        try {

            Participants p = new Participants();
            p.setUser(repoUser.findById(dto.getUserId()).get());
            p.setEvent(repoEvents.findById(dto.getEventId()).get());
            p.setStatus(repoStatus.findById(dto.getStatus()).get());
            p.setRole(repoRole.findById(dto.getRole()).get());
            repoParticipants.save(p);
            return new ResponseEntity<String>("Participante registrado exitosamente", HttpStatus.OK);
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<String>("El usuario ya está registrado para este evento.", HttpStatus.CONFLICT);

        } catch (Exception e) {
            return new ResponseEntity<String>("Error inesperado. " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
