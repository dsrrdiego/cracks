package com.work1.cracks.repos.aux;

import org.springframework.data.jpa.repository.JpaRepository;

import com.work1.cracks.modelos.aux.StatusNotification;

public interface RepoStatusNotification extends JpaRepository<StatusNotification,Long>{
    
}
