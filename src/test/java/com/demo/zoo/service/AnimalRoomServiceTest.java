/*
package com.demo.zoo.service;

import com.demo.zoo.repository.AnimalRepository;
import com.demo.zoo.repository.RoomRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AnimalRoomServiceTest {

    @Autowired
    private AnimalRoomService animalRoomService;

    @MockBean
    private  AnimalRepository animalRepository;
    @MockBean
    private  RoomRepository roomRepository;

    class AnimalRoomServiceContextConfig{
        @Bean
        public AnimalRoomService getAnimalRoomService() {
            animalRoomService = new AnimalRoomServiceImpl(animalRepository, roomRepository);
            return animalRoomService;
        }
    }
    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void assignRooms() {
        animalRoomService.assignRooms(Long.parseLong("1"),Long.parseLong("1"),'Y');
    }

    @Test
    void favoriteRoom() {
    }

    @Test
    void animalsInZooNotInRoom() {
    }

    @Test
    void animalsInSpecificRoom() {
    }

    @Test
    void listOfFavoriteRoomsByAnimalTitle() {
    }
}*/
