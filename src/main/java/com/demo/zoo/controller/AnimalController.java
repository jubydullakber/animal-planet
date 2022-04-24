package com.demo.zoo.controller;

import com.demo.zoo.model.dto.AnimalDto;
import com.demo.zoo.model.dto.command.CreateAnimalDto;
import com.demo.zoo.service.AnimalService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/animal")
public class AnimalController {

    private final AnimalService animalService;

    @Autowired
    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @Operation(description = "Get an Animal")
    @GetMapping(value = "/{animalId}")
    public ResponseEntity<AnimalDto> getAnimalWithId(@PathVariable("animalId") Long animalId) {
        log.info("[Animal] Get with id {}",animalId);
        AnimalDto animal = animalService.getAnimal(animalId);
        return new ResponseEntity<>(animal, HttpStatus.OK);
    }

    @Operation(description = "Create an Animal")
    @PostMapping
    public ResponseEntity<AnimalDto> createAnimal(@RequestBody CreateAnimalDto animalDto) {
        log.info("[Animal] Create  {}",animalDto);
        AnimalDto animal = animalService.createAnimal(animalDto);
        return new ResponseEntity<>(animal, HttpStatus.CREATED);
    }

    @Operation(description = "Update an Animal")
    @PutMapping(value = "/{animalId}")
    public ResponseEntity<AnimalDto> updateAnimal(@PathVariable("animalId") Long animalId, @RequestBody CreateAnimalDto animalDto) {
        log.info("[Animal] Update with id {} and Animal Info",animalId , animalDto);
        AnimalDto animal = animalService.updateAnimal(animalId, animalDto);
        return new ResponseEntity<>(animal, HttpStatus.NO_CONTENT);
    }

    @Operation(description = "Delete an Animal")
    @DeleteMapping("/{animalId}")
    public ResponseEntity<AnimalDto> deleteAnimal(@PathVariable("animalId") Long animalId) {
        log.info("[Animal] Delete with id {}",animalId);
        AnimalDto animal = animalService.deleteAnimal(animalId);
        return new ResponseEntity<>(animal, HttpStatus.NO_CONTENT);
    }


}
