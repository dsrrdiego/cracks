package com.work1.cracks.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.work1.cracks.modelos.Notifications;

public interface RepoNotifications extends JpaRepository<Notifications,Long>{
    
}
