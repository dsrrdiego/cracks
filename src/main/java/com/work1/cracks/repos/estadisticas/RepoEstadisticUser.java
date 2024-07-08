package com.work1.cracks.repos.estadisticas;

import org.springframework.data.jpa.repository.JpaRepository;

import com.work1.cracks.modelos.estadisticas.EstadisticUser;

public interface RepoEstadisticUser extends JpaRepository<EstadisticUser,Long> {
    
}
