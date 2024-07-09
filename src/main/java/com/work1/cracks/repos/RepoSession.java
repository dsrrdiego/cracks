package com.work1.cracks.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.work1.cracks.modelos.Session;

public interface RepoSession extends JpaRepository<Session,Long>{

    
    Session getPasswrdById(Long id);
    
}
