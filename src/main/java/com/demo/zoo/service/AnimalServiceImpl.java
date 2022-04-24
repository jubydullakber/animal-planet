package com.demo.zoo.service;

import com.demo.zoo.enums.StatusEnum;
import com.demo.zoo.exceptions.AnimalNotFoundException;
import com.demo.zoo.model.dto.AnimalDto;
import com.demo.zoo.model.dto.command.CreateAnimalDto;
import com.demo.zoo.model.entity.Animal;
import com.demo.zoo.repository.AnimalRepository;
import com.demo.zoo.repository.RoomRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Slf4j
@Service
public class AnimalServiceImpl implements AnimalService {

    private final AnimalRepository animalRepository;
    private final RoomRepository roomRepository;

    @Autowired
    public AnimalServiceImpl(AnimalRepository animalRepository, RoomRepository roomRepository) {
        this.animalRepository = animalRepository;
        this.roomRepository = roomRepository;
    }

    @Override
    @Transactional
    public AnimalDto createAnimal(CreateAnimalDto animalDto) {
        Animal animal = new Animal();
        BeanUtils.copyProperties(animalDto, animal);
        animal.setLocated(LocalDateTime.now());
        animalRepository.save(animal);
        AnimalDto dto = new AnimalDto();
        BeanUtils.copyProperties(animalDto, dto);
        dto.setAnimalId(animal.getAnimalId());
        log.info("[Animal Service] Create Animal Response {}",dto);
        return dto;
    }

    @Override
    public AnimalDto updateAnimal(Long animalId, CreateAnimalDto animalDto) {
        Animal animal = animalRepository.findById(animalId).orElseThrow(() -> new AnimalNotFoundException("Animal Not Found with id : " + animalId));
        log.info("[Animal Service] Update Animal Not found with id  {}",animalId);
        Animal updatedAnimal = new Animal();
        BeanUtils.copyProperties(animal, updatedAnimal);
        animalRepository.save(updatedAnimal);
        AnimalDto dto = new AnimalDto();
        BeanUtils.copyProperties(updatedAnimal, dto);
        log.info("[Animal Service] update Animal Response {}",dto);
        return dto;
    }

    @Override
    public AnimalDto getAnimal(Long animalId) {
        Animal animal = animalRepository.findById(animalId).orElseThrow(() -> new AnimalNotFoundException("Animal Not Found with id : " + animalId));
        log.info("[Animal Service] Get Animal Not found with id  {}",animalId);
        AnimalDto dto = new AnimalDto();
        BeanUtils.copyProperties(animal, dto);
        log.info("[Animal Service] Get Animal Response {}",dto);
        return dto;
    }

    @Override
    public AnimalDto deleteAnimal(Long animalId) {
        AnimalDto dto = new AnimalDto();
        Animal animal = animalRepository.findById(animalId).orElseThrow(() -> new AnimalNotFoundException("Animal Not Found with id : " + animalId));
        log.info("[Animal Service] Delete Animal Not found with id  {}",animalId);
        animal.setStatus(StatusEnum.INACTIVE.getStatus());
        animalRepository.save(animal);
        BeanUtils.copyProperties(animal, dto);
        log.info("[Animal Service] Delete Animal Response {}",dto);
        return dto;
    }

}
