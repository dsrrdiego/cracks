package com.work1.cracks.modelos;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import com.work1.cracks.interfaces.SessionRol;
import com.work1.cracks.interfaces.TypeLogin;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity

@Data
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name="OwnerInterest")
public class OwnerInterest {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

}