package com.work1.cracks.modelos;

import lombok.NoArgsConstructor;

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
@Table(name = "Participants", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"user_id", "event_id"})
})
public class Participants {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Events event;

    @ManyToOne
    private StatusParticipants status;

    @ManyToOne
    private RoleParticipants role;


    // no van mas?
    // private String linked;

    // private boolean attended;

    // @Enumerated(EnumType.STRING)
    // private Relation relation;

    // private boolean achievementLevel;

    // private float commitementLevel;

    // public Participants(ParticipantsDto d){
        
    // }

}
