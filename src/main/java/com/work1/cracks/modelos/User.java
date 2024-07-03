package com.work1.cracks.modelos;

import lombok.NoArgsConstructor;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Users")
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, unique=true)
    private String name;
    
    @Column
    private String about;

    @Column
    private LocalDate birthDate;

    @Column
    private String gender;

    @Column
    private String email;

    @Column
    private String phone;

    @ManyToOne
    private Cities city;

    @Column
    private String picture;












}
