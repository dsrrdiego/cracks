package com.work1.cracks.repos.aux;

import org.springframework.data.jpa.repository.JpaRepository;

import com.work1.cracks.modelos.aux.StatusEvents;

public interface RepoStatusEvents extends JpaRepository<StatusEvents,Long>{
    
}
