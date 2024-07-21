package com.work1.cracks.modelos;

import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import com.work1.cracks.dtos.EventDto;
import com.work1.cracks.modelos.aux.CategoryEvents;
import com.work1.cracks.modelos.aux.Coordenadas;
import com.work1.cracks.modelos.aux.StatusEvents;

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

    @Column
    String title;

    @Column
    String description;

    @ManyToOne
    private StatusEvents status;

    @ManyToOne
    private Coordenadas location;

    @Column
    private LocalDateTime dateInit;

    @Column
    private LocalDateTime dateEnd;

    @Column
    private String picture;

    @Column
    private int maxParticipantes;

    @Column
    private boolean enabled;

    @Column
    private boolean approvalRequired;

    @ManyToOne
    private CategoryEvents category;

    @Column
    private String urlShare;


    @Column
    private LocalDateTime registerDate;


    @Transient
    private ArrayList<String> goals;

    @Transient
    private ArrayList<String> sports;

    public Events(EventDto dto){
        title=dto.getTitle();
        description=dto.getBody();
        dateInit=dto.getDateInit();
        dateEnd=dto.getDateEnd();
        maxParticipantes=dto.getMaxParticipants();
        enabled=true;
        approvalRequired=false;
        urlShare=dto.getUrlShare();
    }
    
  

}
