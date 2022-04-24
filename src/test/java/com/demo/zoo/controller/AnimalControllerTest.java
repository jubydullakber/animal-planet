package com.demo.zoo.controller;

import com.demo.zoo.model.dto.AnimalDto;
import com.demo.zoo.model.dto.command.CreateAnimalDto;
import com.demo.zoo.service.AnimalService;
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

@WebMvcTest(AnimalController.class)
public class AnimalControllerTest {
    @Autowired
    private MockMvc mockMvc;

    AnimalDto animalDto = null;
    CreateAnimalDto createAnimalDto = null;
    @MockBean
    private AnimalService animalService;
    private ObjectMapper mapper;

    @BeforeEach
    public void setUp() {
        animalDto = AnimalDto.builder()
                .animalId(Long.parseLong("1"))
                .located(LocalDateTime.now())
                .preference(Long.parseLong("33"))
                .type("<=")
                .title("Lion").build();
        createAnimalDto = CreateAnimalDto.builder()
                .preference(Long.parseLong("33"))
                .type("<=")
                .title("Lion").build();

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

    }

    @Test
    public void getAnimalTest() throws Exception {
        when(animalService.getAnimal(Long.parseLong("1"))).thenReturn(animalDto);
        this.mockMvc.perform(get("/api/v1/animal/1")
                        .contentType("application/json")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Lion")));
    }

    @Test
    public void putAnimalTest() throws Exception {
        when(animalService.updateAnimal(Long.parseLong("1"), createAnimalDto)).thenReturn(animalDto);
        this.mockMvc.perform(put("/api/v1/animal/1")
                        .contentType("application/json")
                        .content(mapper.writeValueAsString(1))
                        .content(mapper.writeValueAsString(createAnimalDto))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andExpect(content().string(containsString("Lion")));
    }

    @Test
    public void deleteAnimalTest() throws Exception {
        when(animalService.deleteAnimal(Long.parseLong("1"))).thenReturn(animalDto);
        this.mockMvc.perform(delete("/api/v1/animal/1")
                        .contentType("application/json")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andExpect(content().string(containsString("Lion")));
    }

    @Test
    public void createAnimalTest() throws Exception {
        when(animalService.createAnimal(createAnimalDto)).thenReturn(animalDto);
        this.mockMvc.perform(post("/api/v1/animal")
                        .contentType("application/json")
                        .content(mapper.writeValueAsString(createAnimalDto))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().string(containsString("Lion")));
    }
}
