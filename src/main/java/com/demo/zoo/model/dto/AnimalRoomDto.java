package com.demo.zoo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class AnimalRoomDto {

    private Long animalId;
    private String title;
    private LocalDateTime located;
    private String type;
    private Long preference;
    private Long roomId;
    private String roomTitle;
}
