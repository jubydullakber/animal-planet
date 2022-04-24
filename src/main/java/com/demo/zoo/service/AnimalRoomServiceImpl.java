package com.demo.zoo.service;

import com.demo.zoo.enums.StatusEnum;
import com.demo.zoo.model.dto.*;
import com.demo.zoo.model.entity.AnimalAssignRoom;
import com.demo.zoo.model.entity.Animal;
import com.demo.zoo.model.entity.Favorites;
import com.demo.zoo.model.entity.Room;
import com.demo.zoo.repository.AnimalRepository;
import com.demo.zoo.repository.RoomRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class AnimalRoomServiceImpl implements AnimalRoomService {

    private final AnimalRepository animalRepository;
    private final RoomRepository roomRepository;

    @Autowired
    public AnimalRoomServiceImpl(AnimalRepository animalRepository, RoomRepository roomRepository) {
        this.animalRepository = animalRepository;
        this.roomRepository = roomRepository;
    }

    @Override
    public AnimalRoomDto assignRooms(Long animalId, Long roomId, char Status) {
        Optional<Animal> animals = animalRepository.findById(animalId);
        Animal animals1 = animals.orElseThrow();
        AnimalAssignRoom animalAssignRoom = AnimalAssignRoom.builder().roomId(roomId).animals(animals1).status('Y').build();
        Set<AnimalAssignRoom> animalAssignRoomSet = new HashSet<>();
        animalAssignRoomSet.add(animalAssignRoom);
        animals1.setAnimalAssignRooms(animalAssignRoomSet);
        Animal saveAnimal = animalRepository.save(animals1);
        AnimalRoomDto roomDto = new AnimalRoomDto();
        BeanUtils.copyProperties(saveAnimal, roomDto);
        roomDto.setRoomId(roomId);
        log.info("[AR] assign room response ,{}", roomDto);
        return roomDto;

    }

    @Override
    public AnimalRoomDto favoriteRoom(Long animalId, Long roomId, char status) {
        Optional<Animal> animals = animalRepository.findById(animalId);
        Animal animals1 = animals.orElseThrow();
        Optional<Room> rooms = roomRepository.findById(animalId);
        Room rooms1 = rooms.orElseThrow();
        Favorites favorites = Favorites.builder().
                rooms(rooms1).animals(animals1).status('Y').build();
        AnimalRoomDto roomDto = new AnimalRoomDto();
        BeanUtils.copyProperties(animals1, roomDto);
        roomDto.setRoomId(roomId);
        log.info("[AR] List of Animals favoriteRoom in a zoo ,{}", roomDto);
        return roomDto;
    }

    @Override
    public List<AnimalBriefDto> animalsInZooNotInRoom(AnimalSortCriteria sortCriteria) {
        List<AnimalSortingInfo> sortingCriteria = sortCriteria.getSortingCriteria();
        Sort sort = Sort.by(Sort.DEFAULT_DIRECTION);
        for (AnimalSortingInfo info : sortingCriteria) {
            sort = sort.and(Sort.by(Sort.Direction.fromString(info.getSortingFieldOrder())
                    , info.getSortingFieldName()));
        }
        List<AnimalBriefDto> animalBriefDtos = animalRepository.findAnimalInfoNotAssignedRoom(StatusEnum.ACTIVE.getStatus(), sort);
        log.info("[AR] List of Animals in a zoo ,{}", animalBriefDtos);
        return animalBriefDtos;
    }

    @Override
    public List<AnimalBriefDto> animalsInSpecificRoom(AnimalInRoomDto animalInRoomDto) {
        RoomDto roomDto = animalInRoomDto.getRoomDto();
        List<AnimalSortingInfo> sortingCriteria = animalInRoomDto.getSortCriteria().getSortingCriteria();
        Sort sort = Sort.by(Sort.DEFAULT_DIRECTION);
        for (AnimalSortingInfo info : sortingCriteria) {
            sort = sort.and(Sort.by(Sort.Direction.fromString(info.getSortingFieldOrder())
                    , info.getSortingFieldName()));
        }
        List<AnimalBriefDto> animalByInRooBriefDtos = animalRepository.findAnimalByInRoomId(roomDto.getRoomId(), sort);
        log.info("[AR] List of Animals in a  room response ,{}", animalByInRooBriefDtos);
        return animalByInRooBriefDtos;
    }

    @Override
    public List<RoomTitleDto> listOfFavoriteRoomsByAnimalTitle(String animalTitle) {
        Animal animal = animalRepository.findAnimalByTitle(animalTitle);
        List<RoomTitleDto> favoriteRooms = roomRepository.findFavoriteRoomTitleByAnimalId(animal.getAnimalId(), StatusEnum.ACTIVE.getStatus());
        log.info("[AR] List of favorite room response ,{}", favoriteRooms);
        return favoriteRooms;
    }
}
