package com.work1.cracks.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.work1.cracks.modelos.User;

public interface RepoUser extends JpaRepository<User,Long>{
    
}
