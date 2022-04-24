package com.demo.zoo.controller;

import com.demo.zoo.model.dto.*;
import com.demo.zoo.model.entity.Room;
import com.demo.zoo.service.AnimalRoomService;
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
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AnimalsRoomController.class)
class AnimalsRoomControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AnimalRoomService animalRoomService;

    private AnimalRoomDto animalRoomDto;
    private AnimalBriefDto animalBriefDto;
    private RoomTitleDto roomTitleDto;
    private List<AnimalBriefDto> animalBriefDtoList = new ArrayList<>();
    private List<RoomTitleDto> roomTitleDtoList = new ArrayList<>();
    private AnimalSortCriteria zooSortCriteria = new AnimalSortCriteria();
    private AnimalSortCriteria roomSortCriteria = new AnimalSortCriteria();
    private RoomDto roomDto;
    private AnimalInRoomDto animalInRoomDto;
    private ObjectMapper mapper;

    @BeforeEach
    public void setup(){
        animalRoomDto = AnimalRoomDto.builder().animalId(Long.parseLong("1"))
                .roomTitle("GREEN ROOM")
                .animalId(Long.parseLong("1"))
                .located(LocalDateTime.now())
                .preference(Long.parseLong("33"))
                .title("Lion")
                .build();
        animalBriefDto = AnimalBriefDto.builder().title("Lion").located(LocalDateTime.now()).build();
        animalBriefDtoList.add(animalBriefDto);
        roomTitleDto = RoomTitleDto.builder().title("GREEN ROOM").build();
        roomTitleDtoList.add(roomTitleDto);
        List<AnimalSortingInfo> sortingCriteria1 = new ArrayList<>();
        List<AnimalSortingInfo> sortingCriteria2 = new ArrayList<>();
        sortingCriteria2.add(AnimalSortingInfo.builder().sortingFieldName("TITLE").sortingFieldOrder("ASC").build());
        zooSortCriteria.setSortingCriteria(sortingCriteria1);
        roomSortCriteria.setSortingCriteria(sortingCriteria2);
        sortingCriteria1.add(AnimalSortingInfo.builder().sortingFieldName("LOCATED").sortingFieldOrder("DESC").build());
        roomDto = RoomDto.builder().roomId(Long.parseLong("1")).size(Long.parseLong("33")).title("GREEN ROOM").build();
        mapper= new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        animalInRoomDto = AnimalInRoomDto.builder().roomDto(roomDto).sortCriteria(roomSortCriteria).build();
    }
    @Test
    void assignRoomToAnimal() throws Exception {
        when(animalRoomService.assignRooms(Long.parseLong("1"),Long.parseLong("1"),'Y')).thenReturn(animalRoomDto);
        this.mockMvc.perform(put("/api/v1/1/assign/to/1")
                        .contentType("application/json")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andExpect(content().string(containsString("GREEN ROOM")));

    }

    @Test
    void favoritesRoomToAnimal() throws Exception {
        when(animalRoomService.favoriteRoom(Long.parseLong("1"),Long.parseLong("1"),'Y')).thenReturn(animalRoomDto);
        this.mockMvc.perform(put("/api/v1/1/favorite/1")
                        .contentType("application/json")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andExpect(content().string(containsString("GREEN ROOM")));
    }

    @Test
    void findAnimalNotAssignRoom() throws Exception {
        when(animalRoomService.animalsInZooNotInRoom(zooSortCriteria)).thenReturn(animalBriefDtoList);
        this.mockMvc.perform(post("/api/v1/find-unassigned-room-animal")
                        .contentType("application/json")
                        .content(mapper.writeValueAsString(zooSortCriteria))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().string(containsString("Lion")));
    }

    @Test
    void findAnimalsInSpecificRoom() throws Exception {
        when(animalRoomService.animalsInSpecificRoom(animalInRoomDto)).thenReturn(animalBriefDtoList);
        this.mockMvc.perform(post("/api/v1/find-animal-in-room")
                        .contentType("application/json")
                        .content(mapper.writeValueAsString(animalInRoomDto))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().string(containsString("Lion")));
    }

    @Test
    void findFavoritesRoomList() throws Exception {
        when(animalRoomService.listOfFavoriteRoomsByAnimalTitle("Lion")).thenReturn(roomTitleDtoList);
        this.mockMvc.perform(get("/api/v1//find-favorite-room/Lion")
                        .contentType("application/json")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("GREEN ROOM")));
    }
}