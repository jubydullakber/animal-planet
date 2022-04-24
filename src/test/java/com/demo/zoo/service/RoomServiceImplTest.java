package com.demo.zoo.service;

import com.demo.zoo.model.dto.RoomDto;
import com.demo.zoo.model.dto.command.CreateRoomDto;
import com.demo.zoo.model.entity.Room;
import com.demo.zoo.repository.RoomRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RoomServiceImplTest {

    @Autowired
    private RoomService roomService;

    @MockBean
    private RoomRepository roomRepository;

    private Room room;
    private Room savedRoom;
    private RoomDto roomDto;
    private CreateRoomDto createRoomDto;
    @BeforeEach
    void setUp() {
        LocalDateTime created = LocalDateTime.now();
        room = Room.builder()
                .title("GREEN ROOM")
                .size(Long.parseLong("15"))
                .build();
        savedRoom = Room.builder()
                .roomId(Long.parseLong("1"))
                .title("GREEN ROOM")
                .size(Long.parseLong("15"))
                .build();
        roomDto = RoomDto.builder()
                .roomId(Long.parseLong("1"))
                .title("GREEN ROOM")
                .size(Long.parseLong("15"))
                .created(created)
                .build();
        createRoomDto = CreateRoomDto.builder()
                .title("GREEN ROOM")
                .size(Long.parseLong("15"))
                .build();
    }

    @AfterEach
    void tearDown() {
    }

    @TestConfiguration
    class RoomServiceContextConfig {

        @Bean
        public RoomService getRoomService() {
            roomService = new RoomServiceImpl(roomRepository);
            return roomService;
        }
    }

    @Test
    void createRoom() {
        Mockito.when(roomRepository.save(room)).thenReturn(savedRoom);
        RoomDto room = roomService.createRoom(createRoomDto);
        assertThat(room.getRoomId()).isEqualTo(savedRoom.getRoomId());
        assertThat(room.getTitle()).isEqualTo(savedRoom.getTitle());
        assertThat(room.getSize()).isEqualTo(savedRoom.getSize());
    }

    @Test
    void updateRoom() {
        Optional<Room> roomOptional = Optional.of(savedRoom);
        Mockito.when(roomRepository.save(room)).thenReturn(savedRoom);
        Mockito.when(roomRepository.findById(Long.parseLong("1"))).thenReturn(roomOptional);
        RoomDto room = roomService.updateRoom(Long.parseLong("1"),createRoomDto);
        assertThat(room.getRoomId()).isEqualTo(roomDto.getRoomId());
        assertThat(room.getTitle()).isEqualTo(roomDto.getTitle());
        assertThat(room.getSize()).isEqualTo(roomDto.getSize());
    }

    @Test
    void getRoom() {
        Optional<Room> roomOptional = Optional.of(savedRoom);
        Mockito.when(roomRepository.findById(Long.parseLong("1"))).thenReturn(roomOptional);
        RoomDto room = roomService.getRoom(Long.parseLong("1"));
        assertThat(room.getRoomId()).isEqualTo(roomDto.getRoomId());
        assertThat(room.getTitle()).isEqualTo(roomDto.getTitle());
        assertThat(room.getSize()).isEqualTo(roomDto.getSize());
    }

    @Test
    void deleteRoom() {
        Optional<Room> roomOptional = Optional.of(savedRoom);
        Mockito.when(roomRepository.save(room)).thenReturn(savedRoom);
        Mockito.when(roomRepository.findById(Long.parseLong("1"))).thenReturn(roomOptional);
        RoomDto room = roomService.deleteRoom(Long.parseLong("1"));
        assertThat(room.getRoomId()).isEqualTo(roomDto.getRoomId());
        assertThat(room.getTitle()).isEqualTo(roomDto.getTitle());
        assertThat(room.getSize()).isEqualTo(roomDto.getSize());

    }
}