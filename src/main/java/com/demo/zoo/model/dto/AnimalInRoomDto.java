package com.demo.zoo.model.dto;

import com.demo.zoo.model.entity.Room;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AnimalInRoomDto {
    private RoomDto roomDto;
    private AnimalSortCriteria sortCriteria;
}
