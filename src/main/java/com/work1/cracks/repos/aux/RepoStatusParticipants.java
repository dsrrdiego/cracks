package com.work1.cracks.repos.aux;

import org.springframework.data.jpa.repository.JpaRepository;

import com.work1.cracks.modelos.aux.StatusParticipants;

public interface RepoStatusParticipants extends JpaRepository<StatusParticipants,Long>{
    
}
