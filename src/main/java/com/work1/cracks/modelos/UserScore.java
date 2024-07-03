package com.work1.cracks.modelos;

    

import java.time.LocalDate;

import com.work1.cracks.interfaces.NameUserScore;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "UserScore")
public class UserScore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Events event;

    @Enumerated(EnumType.STRING)
    private NameUserScore name;
    
    private float score;

    private float rangeInit;

    private boolean visibility;

    private float rangeEnd;

    private LocalDate registerDate;
}