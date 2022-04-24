package com.demo.zoo.service;

import com.demo.zoo.enums.StatusEnum;
import com.demo.zoo.exceptions.RoomNotFoundException;
import com.demo.zoo.model.dto.RoomDto;
import com.demo.zoo.model.dto.command.CreateRoomDto;
import com.demo.zoo.model.entity.Room;
import com.demo.zoo.repository.RoomRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
public class RoomServiceImpl implements RoomService {
    private final RoomRepository roomRepository;

    @Autowired
    public RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public RoomDto createRoom(CreateRoomDto createRoomDto) {
        Room room = new Room();
        BeanUtils.copyProperties(createRoomDto, room);
        room.setCreated(LocalDateTime.now());
        roomRepository.save(room);
        RoomDto dto = new RoomDto();
        BeanUtils.copyProperties(room, dto);
        log.info("[ROOM Service] create room response {}",dto);
        return dto;
    }

    @Override
    public RoomDto updateRoom(Long roomId ,CreateRoomDto
            createRoomDto) {
        RoomDto dto = new RoomDto();
        Room existingRoom = roomRepository.findById(roomId)
                .orElseThrow(()->new RoomNotFoundException("Room Not Found with room Id : " + roomId));
        log.info("[ROOM Service] update room not found with id  {}",roomId);
        BeanUtils.copyProperties(createRoomDto, existingRoom);
        roomRepository.save(existingRoom);
        BeanUtils.copyProperties(existingRoom, dto);
        log.info("[ROOM Service] Update room response {}",dto);
        return dto;
    }

    @Override
    public RoomDto getRoom(Long roomId) {
        Room existingRoom = roomRepository.findById(roomId)
                .orElseThrow(()->new RoomNotFoundException("Room Not Found with room Id : " + roomId));
        log.info("[ROOM Service] Get room not found {}",roomId);
        RoomDto roomDto = new RoomDto();
        BeanUtils.copyProperties(existingRoom, roomDto);
        log.info("[ROOM Service] Get room response {}",roomDto);
        return roomDto;
    }

    @Override
    public RoomDto deleteRoom(Long roomId) {
        Room existingRoom = roomRepository.findById(roomId)
                .orElseThrow(()->new RoomNotFoundException("Room Not Found with room Id : " + roomId));
        existingRoom.setStatus(StatusEnum.INACTIVE.getStatus());
        RoomDto dto = new RoomDto();
        roomRepository.save(existingRoom);
        BeanUtils.copyProperties(existingRoom,dto);
        log.info("[ROOM Service] delete room response {}",dto);
        return dto;
    }
}
