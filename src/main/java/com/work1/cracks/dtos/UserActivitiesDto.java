package com.work1.cracks.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
// @AllArgsConstructor
public class UserActivitiesDto {
    Long id;
    Long userId;
    String title;

    public UserActivitiesDto(Long id, Long userId, String title){
        this.id=id;
        this.userId=userId;
        this.title=title;
    }
}
