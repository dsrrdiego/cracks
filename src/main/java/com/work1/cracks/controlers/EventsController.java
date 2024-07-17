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

import com.work1.cracks.modelos.Events;
import com.work1.cracks.repos.RepoEvents;

@RestController
public class EventsController {
    @Autowired
    private RepoEvents repoEvents;

    @GetMapping("/event/{id}")
    public ResponseEntity<Optional<Events>> events(@PathVariable Long id) {

        Optional<Events> e = repoEvents.findById(id);
        return new ResponseEntity<Optional<Events>>(e, HttpStatus.OK);

    }

    @GetMapping("/event/{pageInit}/{cantidad}")
    public List<Events> eventPaginado(@PathVariable int pageInit, @PathVariable int cantidad) {
        return repoEvents.findPage(cantidad,pageInit-1);
    }

}
