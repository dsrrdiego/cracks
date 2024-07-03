package com.work1.cracks.modelos;

import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.hibernate.bytecode.internal.bytebuddy.PrivateAccessorException;

import com.work1.cracks.interfaces.Relation;
import com.work1.cracks.interfaces.Role;

// import com.work1.cracks.modelos.Events;

import com.work1.cracks.interfaces.StatusEvent;
import com.work1.cracks.interfaces.StatusParticipants;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Participants")
public class Participants {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Events event;

    @Enumerated(EnumType.STRING)
    private StatusParticipants status;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String linked;

    private boolean attended;

    @Enumerated(EnumType.STRING)
    private Relation relation;

    private boolean achievementLevel;

    private float commitementLevel;

    //Statics?











}
