package com.work1.cracks.modelos;



import java.time.LocalDate;
import java.util.Date;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Session")
public class Session {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private User users;

    @Column(nullable=false)
    private String passwrd;
    
    @Column
    private Boolean verified;

    @Column
    private LocalDate reigsterDate;

    public Session(User u,String psw){
        users=u;
        passwrd=psw;
        reigsterDate=LocalDate.now();
    }

}
