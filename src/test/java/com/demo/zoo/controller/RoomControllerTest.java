package com.demo.zoo.controller;

import com.demo.zoo.model.dto.RoomDto;
import com.demo.zoo.model.dto.command.CreateRoomDto;
import com.demo.zoo.service.RoomService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RoomController.class)
public class RoomControllerTest {

    @Autowired
    private MockMvc mockMvc;

    RoomDto room = null;
    CreateRoomDto createRoom = null;
    @MockBean
    private RoomService roomService;
    ObjectMapper mapper;

    @BeforeEach
    public void setUp(){
        room = RoomDto.builder()
                .roomId(Long.parseLong("1"))
                .created(LocalDateTime.now())
                .size(Long.parseLong("33"))
                .title("Nice Room").build();
        createRoom = CreateRoomDto.builder()
                .size(Long.parseLong("33"))
                .title("Nice Room").build();

        mapper= new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

    }

    @Test
    public void getRoomTest() throws Exception {
        when(roomService.getRoom(Long.parseLong("1"))).thenReturn(room);
        this.mockMvc.perform(get("/api/v1/room/1")
                        .contentType("application/json")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Nice Room")));
    }

    @Test
    public void putRoomTest() throws Exception {
        when(roomService.updateRoom(Long.parseLong("1"),createRoom)).thenReturn(room);
        this.mockMvc.perform(put("/api/v1/room/1")
                        .contentType("application/json")
                        .content(mapper.writeValueAsString(createRoom))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andExpect(content().string(containsString("Nice Room")));
    }

    @Test
    public void deleteRoomTest() throws Exception {
        when(roomService.deleteRoom(Long.parseLong("1"))).thenReturn(room);
        this.mockMvc.perform(delete("/api/v1/room/1")
                        .contentType("application/json")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andExpect(content().string(containsString("Nice Room")));
    }
    @Test
    public void createRoomTest() throws Exception {
        when(roomService.createRoom(createRoom)).thenReturn(room);
        this.mockMvc.perform(post("/api/v1/room")
                        .contentType("application/json")
                        .content(mapper.writeValueAsString(createRoom))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().string(containsString("Nice Room")));
    }
}
