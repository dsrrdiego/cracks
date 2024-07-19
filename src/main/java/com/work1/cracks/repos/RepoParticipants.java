package com.work1.cracks.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.work1.cracks.modelos.Participants;

public interface RepoParticipants extends JpaRepository<Participants,Long>{
    // @Query("SELECT p FROM Participants p WHERE p.user.id=:id")
    @Query("SELECT p FROM Participants p WHERE p.user.id=:id AND p.event.dateInit < NOW()")
    List<Participants> findPasadosById(@Param("id") Long id);

    @Query("SELECT p FROM Participants p WHERE p.user.id=:id")
    List<Participants> findByUserId(Long id);
    
}
