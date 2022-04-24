package com.demo.zoo.service;

import com.demo.zoo.model.dto.AnimalDto;
import com.demo.zoo.model.dto.command.CreateAnimalDto;
import com.demo.zoo.model.entity.Animal;
import com.demo.zoo.repository.AnimalRepository;
import com.demo.zoo.repository.RoomRepository;
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

@SpringBootTest
class AnimalServiceImplTest {

    @Autowired
    private  AnimalService animalService;

    @MockBean
    private  AnimalRepository animalRepository;

    @MockBean
    private  RoomRepository roomRepository;

    private Animal animal;
    private Animal savedAnimal;
    private CreateAnimalDto createAnimalDto;
    private AnimalDto animalDto;

    @BeforeEach
    void setUp() {
        animal = Animal.builder()
                .title("Lion")
                .status('Y')
                .preference(Long.parseLong("33"))
                .type("<=").build();
        savedAnimal = Animal.builder().animalId(Long.parseLong("1"))
                .title("Lion")
                .status('Y')
                .preference(Long.parseLong("33"))
                .type("<=").build();
        createAnimalDto = CreateAnimalDto.builder()
                .title("Lion")
                .preference(Long.parseLong("33"))
                .type("<=").build();
        animalDto = AnimalDto.builder().animalId(Long.parseLong("1"))
                .title("Lion")
                .preference(Long.parseLong("33"))
                .type("<=").build();
    }

    @TestConfiguration
    class AnimalServiceContextConfig {

        @Bean
        public AnimalService getAnimalService() {
            animalService = new AnimalServiceImpl(animalRepository, roomRepository);

            return animalService;
        }
    }

    @Test
    void createAnimal() {
        Mockito.when(animalRepository.save(animal)).thenReturn(savedAnimal);
        AnimalDto animalCreated = animalService.createAnimal(createAnimalDto);
        assertThat(animalCreated.getTitle()).isEqualTo(animal.getTitle());
        assertThat(animalCreated.getType()).isEqualTo(animal.getType());
        assertThat(animalCreated.getPreference()).isEqualTo(animal.getPreference());
    }

    @Test
    void updateAnimal() {
        Optional<Animal> savedOps = Optional.of(savedAnimal);
        Mockito.when(animalRepository.findById(Long.parseLong("1"))).thenReturn(savedOps);
        Mockito.when(animalRepository.save(animal)).thenReturn(savedAnimal);
        AnimalDto animalCreated = animalService.updateAnimal(Long.parseLong("1"),createAnimalDto);
        assertThat(animalCreated.getTitle()).isEqualTo(animal.getTitle());
        assertThat(animalCreated.getType()).isEqualTo(animal.getType());
        assertThat(animalCreated.getPreference()).isEqualTo(animal.getPreference());
    }

    @Test
    void getAnimal() {
        Optional<Animal> savedOps = Optional.of(savedAnimal);
        Mockito.when(animalRepository.findById(Long.parseLong("1"))).thenReturn(savedOps);
        AnimalDto animalCreated = animalService.getAnimal(Long.parseLong("1"));
        assertThat(animalCreated.getTitle()).isEqualTo(animal.getTitle());
        assertThat(animalCreated.getType()).isEqualTo(animal.getType());
        assertThat(animalCreated.getPreference()).isEqualTo(animal.getPreference());
    }

    @Test
    void deleteAnimal() {
        Optional<Animal> savedOps = Optional.of(savedAnimal);
        Mockito.when(animalRepository.findById(Long.parseLong("1"))).thenReturn(savedOps);
        Mockito.when(animalRepository.save(savedAnimal)).thenReturn(animal);
        AnimalDto animalCreated = animalService.deleteAnimal(Long.parseLong("1"));
        assertThat(animalCreated.getTitle()).isEqualTo(savedAnimal.getTitle());
        assertThat(animalCreated.getType()).isEqualTo(savedAnimal.getType());
        assertThat(animalCreated.getPreference()).isEqualTo(savedAnimal.getPreference());
    }
}