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
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity

@Data
@EqualsAndHashCode(callSuper = true)

public class InterestEvent extends InterestPadre {

    @ManyToOne
    private Events event;
}
