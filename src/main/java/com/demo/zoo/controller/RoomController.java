package com.demo.zoo.controller;

import com.demo.zoo.model.dto.RoomDto;
import com.demo.zoo.model.dto.command.CreateRoomDto;
import com.demo.zoo.service.RoomService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/room")
public class RoomController {

    private final RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @Operation(description = "Get A Room")
    @GetMapping("/{roomId}")
    public ResponseEntity<RoomDto> getRoom(@PathVariable("roomId") Long roomId) {
        log.info("[ROOM] get room with id {}", roomId);
        RoomDto roomDto = roomService.getRoom(roomId);
        return new ResponseEntity<>(roomDto, HttpStatus.OK);
    }

    @Operation(description = "Create A Room")
    @PostMapping
    public ResponseEntity<RoomDto> createRoom(@RequestBody CreateRoomDto createRoomDto) {
        log.info("[ROOM] Create room with id {}", createRoomDto);
        RoomDto room = roomService.createRoom(createRoomDto);
        return new ResponseEntity<>(room, HttpStatus.CREATED);
    }

    @Operation(description = "Update A Room")
    @PutMapping("/{roomId}")
    public ResponseEntity<RoomDto> updateRoom(@PathVariable("roomId") Long roomId, @RequestBody CreateRoomDto createRoomDto) {
        log.info("[ROOM] update room with id {} and room info {}", roomId, createRoomDto);
        RoomDto room = roomService.updateRoom(roomId, createRoomDto);
        return new ResponseEntity<>(room, HttpStatus.NO_CONTENT);
    }

    @Operation(description = "Delete A Room")
    @DeleteMapping("/{roomId}")
    public ResponseEntity<RoomDto> deleteRoom(@PathVariable("roomId") Long roomId) {
        log.info("[ROOM] Delete room with id {}",roomId);
        RoomDto roomDto = roomService.deleteRoom(roomId);
        return new ResponseEntity<>(roomDto, HttpStatus.NO_CONTENT);
    }
}
