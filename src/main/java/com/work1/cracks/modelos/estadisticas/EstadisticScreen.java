package com.work1.cracks.modelos.estadisticas;

import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import com.work1.cracks.modelos.Goals;
import com.work1.cracks.modelos.Sports;
import com.work1.cracks.modelos.User;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "EstadisticScreen")
public class   EstadisticScreen{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   

}