package com.work1.cracks.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.work1.cracks.modelos.User;

public interface RepoUser extends JpaRepository<User,Long>{
    // List<User> findByName(String name);
    boolean existsByName(String name);
    User findByName(String name);
    @SuppressWarnings("null")
    Optional<User> findById(Long id);
}
