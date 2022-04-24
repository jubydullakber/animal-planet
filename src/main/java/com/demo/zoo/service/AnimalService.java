package com.demo.zoo.service;

import com.demo.zoo.model.dto.AnimalDto;
import com.demo.zoo.model.dto.command.CreateAnimalDto;

public interface AnimalService {

    AnimalDto createAnimal(CreateAnimalDto animalDto);

    AnimalDto updateAnimal(Long animalId , CreateAnimalDto animalDto);

    AnimalDto getAnimal(Long animalId);

    AnimalDto deleteAnimal(Long animalId);

}
