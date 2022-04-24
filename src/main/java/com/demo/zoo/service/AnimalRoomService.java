package com.demo.zoo.service;

import com.demo.zoo.model.dto.*;
import com.demo.zoo.model.entity.Room;

import java.util.List;

public interface AnimalRoomService {
    AnimalRoomDto assignRooms(Long animalId, Long roomId, char status);

    AnimalRoomDto favoriteRoom(Long animalId, Long roomId, char status);

    List<AnimalBriefDto> animalsInZooNotInRoom(AnimalSortCriteria sortCriteria);

    List<AnimalBriefDto> animalsInSpecificRoom(AnimalInRoomDto animalInRoomDto);

    List<RoomTitleDto> listOfFavoriteRoomsByAnimalTitle(String animalTitle);
}
