package com.work1.cracks.modelos;

import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.hibernate.bytecode.internal.bytebuddy.PrivateAccessorException;

import com.work1.cracks.interfaces.StatusEvent;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Events")
public class Events {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    String title;
    String description;

    //interest

    @ManyToMany
    private List<Sports> sports;

    @Enumerated(EnumType.STRING)
    private StatusEvent status;

    private String location;
    private LocalDate dateInit;
    private LocalDate dateEnd;
    private LocalTime hourInit;
    private LocalTime hourEnd;
    private String picture;
    private int maxParticipantes;
    private String visibility;
    private boolean enabled;
    private boolean approvalRequired;
    private String category;
    private String urlShare;
    private int visibilizations;
    private int likes;
    private LocalDate registerDate;










}
