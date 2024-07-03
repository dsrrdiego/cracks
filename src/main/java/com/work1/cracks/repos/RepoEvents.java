package com.work1.cracks.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.work1.cracks.modelos.Events;

public interface RepoEvents extends JpaRepository<Events,Long>{
    
}
