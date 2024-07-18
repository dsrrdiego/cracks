package com.work1.cracks.controlers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.work1.cracks.modelos.Session;
import com.work1.cracks.repos.RepoSession;

@RestController

public class SessionController {

    @Autowired
    private RepoSession repoSession;
    @GetMapping("/pullSession/{id}")
    public ResponseEntity<Session> pullSession(@PathVariable Long id){
        Session s = repoSession.findByUserId(id);
        return new ResponseEntity<Session>(s,HttpStatus.OK)  ;
    }
    
}
