package com.work1.cracks.modelos;

import lombok.NoArgsConstructor;


import com.work1.cracks.interfaces.Relation;


import com.work1.cracks.modelos.aux.RoleParticipants;
import com.work1.cracks.modelos.aux.StatusParticipants;

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

    @ManyToOne
    private StatusParticipants status;

    @ManyToOne
    private RoleParticipants role;

    private String linked;

    private boolean attended;

    @Enumerated(EnumType.STRING)
    private Relation relation;

    private boolean achievementLevel;

    private float commitementLevel;


}
