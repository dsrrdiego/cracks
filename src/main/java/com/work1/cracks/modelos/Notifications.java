package com.work1.cracks.modelos;

import java.time.LocalDate;

import com.work1.cracks.interfaces.ActiocNotification;
import com.work1.cracks.interfaces.StatusNotification;
import com.work1.cracks.interfaces.TypeNotification;

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
@Table(name = "Notification")
public class Notifications {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Events event;

    @Enumerated(EnumType.STRING)
    private StatusNotification status;

    @Enumerated(EnumType.STRING)
    private TypeNotification type;

    private String title;

    private int message;

    @Enumerated(EnumType.STRING)
    private ActiocNotification action;

    private boolean automaticDelete;

    private LocalDate visualizationDate;
    
    private LocalDate registerDate;

}
