package com.work1.cracks.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import com.work1.cracks.modelos.Events;

public interface RepoEvents extends JpaRepository<Events,Long>{
    @Query(value = "SELECT e.* FROM events e WHERE e.date_Init > NOW() ORDER BY id ASC LIMIT :limit OFFSET :offset ", nativeQuery = true)
    List<Events> findPage(
        @Param("limit") int limit, 
        @Param("offset") int offset
    );
    
}
