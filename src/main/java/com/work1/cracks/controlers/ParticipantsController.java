package com.work1.cracks.controlers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.work1.cracks.modelos.Participants;
import com.work1.cracks.repos.RepoParticipants;
import com.work1.cracks.servicios.GoalSportsService;

@RestController
public class ParticipantsController {
    @Autowired
    private RepoParticipants repoParticipants;
    @Autowired 
    private GoalSportsService goalSportsService;

    @GetMapping("/pullPassedEventsByUser/{id}")
    public List<Participants> passedEventByUser(@PathVariable Long id) {
        List<Participants> lista=repoParticipants.findPasadosById(id);
        for (Participants p  : lista) {
            p.getEvent().setGoals(goalSportsService.getEventsGoals(p.getEvent().getId()));
            p.getEvent().setSports(goalSportsService.getEventsSports(p.getEvent().getId()));
        }

        return lista;
    }

}
