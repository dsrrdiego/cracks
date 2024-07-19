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

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@RestController
public class EventsController {
    @Autowired
    private RepoEvents repoEvents;

    @Autowired
    private RepoInterest repoInterest;

    @PersistenceContext
    private EntityManager entityManager;

    @GetMapping("/pullEvents/{filaIni}/{cantidad}")
    public ResponseEntity<ArrayList<Events>> pullEvents(@PathVariable int filaIni, @PathVariable int cantidad) {
        ArrayList<Events> lista = (ArrayList<Events>) repoEvents.findPage(cantidad, filaIni - 1);
        for (Events e : lista) {
            System.out.println(e.getTitle());
            // ArrayList<String> goals=repoInterest.
            String jpql = "SELECT i.goal_sport_interest.title FROM Interest i WHERE TYPE(i.owner) = InterestEvent AND TYPE(i.goal_sport_interest)=Goals AND i.owner.event.id=:id";
            TypedQuery<String> query = entityManager.createQuery(jpql, String.class);
            query.setParameter("id", e.getId());
            ArrayList<String> goals=(ArrayList<String>) query.getResultList();
            e.setGoals(goals);

            jpql = "SELECT i.goal_sport_interest.title FROM Interest i WHERE TYPE(i.owner) = InterestEvent AND TYPE(i.goal_sport_interest)=Sports AND i.owner.event.id=:id";
            query = entityManager.createQuery(jpql, String.class);
            query.setParameter("id", e.getId());
            ArrayList<String> sports=(ArrayList<String>) query.getResultList();
            e.setSports(sports);
        }
        return new ResponseEntity<ArrayList<Events>>(lista, HttpStatus.OK);
    }

   

    // @GetMapping("/event/{id}")
    // public ResponseEntity<Optional<Events>> events(@PathVariable Long id) {

    // Optional<Events> e = repoEvents.findById(id);
    // return new ResponseEntity<Optional<Events>>(e, HttpStatus.OK);

    // }

    // @GetMapping("/event/{pageInit}/{cantidad}")
    // public List<Events> eventPaginado(@PathVariable int pageInit, @PathVariable
    // int cantidad) {
    // return repoEvents.findPage(cantidad,pageInit-1);
    // }

}
