package com.work1.cracks.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PullGoalsDto {
    Long id;
    String title;

    public PullGoalsDto(Long id, String title){
        this.id=id;
        this.title=title;
    }
}
