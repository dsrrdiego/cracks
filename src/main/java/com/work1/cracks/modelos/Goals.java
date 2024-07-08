package com.work1.cracks.modelos;

import java.time.LocalDateTime;

import com.work1.cracks.modelos.aux.CategoryGoals;
import com.work1.cracks.modelos.aux.CommunityGoals;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity

@Data
@Inheritance(strategy = InheritanceType.JOINED)

// @Table(name = "Goals")

public class Goals {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String title;

    @ManyToOne
    private CategoryGoals category;

    @ManyToOne
    private CommunityGoals community;
}
