package com.demo.zoo.service;

import com.demo.zoo.model.dto.RoomDto;
import com.demo.zoo.model.dto.command.CreateRoomDto;

public interface RoomService {
    RoomDto createRoom(CreateRoomDto dto);
    RoomDto updateRoom(Long roomId , CreateRoomDto dto);
    RoomDto getRoom(Long roomId);
    RoomDto deleteRoom(Long roomId);
}
