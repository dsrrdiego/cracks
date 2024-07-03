package com.work1.cracks.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.work1.cracks.modelos.Participants;

public interface RepoParticipants extends JpaRepository<Participants,Long>{
    
}
