package com.work1.cracks.modelos;


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
    @Table(name = "User_Interest")
    
public class UserInterest {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        
}
