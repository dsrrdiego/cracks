package com.work1.cracks.controlers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.work1.cracks.modelos.Participants;
import com.work1.cracks.repos.RepoParticipants;

@RestController
public class ParticipantsController {
    @Autowired
    private RepoParticipants repoParticipants;

    @GetMapping("/pullPassedEventsByUser/{id}")
    public List<Participants> passedEventByUser(@PathVariable Long id) {
        return repoParticipants.findPasadosById(id);
    }

}
