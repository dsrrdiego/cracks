package com.work1.cracks.controlers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.work1.cracks.modelos.Interest;
import com.work1.cracks.repos.RepoInterest;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@RestController
public class InterestsController {

    @PersistenceContext
    private EntityManager entityManager;

    @GetMapping("/userActivities/{id}")
    public ResponseEntity<List<String>> goals(@PathVariable Long id) {
        String jpql = "SELECT i.goal_sport_interest.title FROM Interest i WHERE TYPE(i.owner) = InterestUser AND TYPE(i.goal_sport_interest)=Goals AND i.owner.user.id=:id";
        TypedQuery<String> query = entityManager.createQuery(jpql, String.class);
        query.setParameter("id",id);
        return new ResponseEntity<>(query.getResultList(), HttpStatus.OK);
    }

    @GetMapping("/userSports/{id}")
    public ResponseEntity<List<String>> sports(@PathVariable Long id){
        String jpql = "SELECT i.goal_sport_interest.title FROM Interest i WHERE TYPE(i.owner) = InterestUser AND TYPE(i.goal_sport_interest)=Sports AND i.owner.user.id=:id";
        TypedQuery<String> query = entityManager.createQuery(jpql, String.class);
        query.setParameter("id",id);

    return new ResponseEntity<>(query.getResultList(),HttpStatus.OK);
    }

}
