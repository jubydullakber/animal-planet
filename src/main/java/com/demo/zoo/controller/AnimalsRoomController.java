package com.demo.zoo.controller;

import com.demo.zoo.enums.StatusEnum;
import com.demo.zoo.model.dto.*;
import com.demo.zoo.service.AnimalRoomService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/")
public class AnimalsRoomController {

    private final AnimalRoomService animalRoomService;

    @Autowired
    public AnimalsRoomController(AnimalRoomService animalRoomService) {
        this.animalRoomService = animalRoomService;
    }

    @Operation(description = "Assign room to a animal")
    @PutMapping(value = "/{roomId}/assign/to/{animalId}")
    public ResponseEntity<AnimalRoomDto> assignRoomToAnimal(@PathVariable("animalId") Long animalId,
                                                            @PathVariable("roomId") Long roomId) {
        log.info("[AR] Assign room to a animal with animal id {} and room id {}",animalId,roomId);
        AnimalRoomDto animalRoomDto = animalRoomService.assignRooms(animalId, roomId, StatusEnum.ACTIVE.getStatus());
        return new ResponseEntity<>(animalRoomDto, HttpStatus.NO_CONTENT);
    }

    @Operation(description = "Assign favorite room for a animal")
    @PutMapping(value = "/{animalId}/favorite/{roomId}")
    public ResponseEntity<AnimalRoomDto> favoritesRoomToAnimal(@PathVariable("animalId") Long animalId,
                                                               @PathVariable("roomId") Long roomId) {
        log.info("[AR] Favorite room to a animal with animal id {} and room id {}",animalId,roomId);
        AnimalRoomDto animalRoomDto = animalRoomService.favoriteRoom(animalId, roomId, StatusEnum.ACTIVE.getStatus());
        return new ResponseEntity<>(animalRoomDto, HttpStatus.NO_CONTENT);
    }

    @Operation(description ="Find un assigned room for animal in the zoo")
    @PostMapping(value = "/find-unassigned-room-animal")
    public ResponseEntity<List<AnimalBriefDto>> findAnimalNotAssignRoom(@RequestBody AnimalSortCriteria sortCriteria) {
        log.info("[AR] find animal in a zoo with sort criteria",sortCriteria);
        List<AnimalBriefDto> animalBriefDtos = animalRoomService.animalsInZooNotInRoom(sortCriteria);
        return new ResponseEntity<>(animalBriefDtos, HttpStatus.CREATED);
    }

    @Operation(description ="Find animal in a specific room")
    @PostMapping(value = "/find-animal-in-room")
    public ResponseEntity<List<AnimalBriefDto>> findAnimalsInSpecificRoom(
            @RequestBody AnimalInRoomDto animalInRoomDto) {
        log.info("[AR] find animal in a room with sort and room id {}",animalInRoomDto);
        List<AnimalBriefDto> animalBriefDtos = animalRoomService.animalsInSpecificRoom(animalInRoomDto);
        return new ResponseEntity<>(animalBriefDtos, HttpStatus.CREATED);
    }

    @Operation(description ="Fina Favorite room title of a animal")
    @GetMapping(value = "/find-favorite-room/{animalTitle}")
    public ResponseEntity<List<RoomTitleDto>> findFavoritesRoomList(@PathVariable("animalTitle") String animalTitle) {
        log.info("[AR] find favorite room {} ",animalTitle);
        List<RoomTitleDto> roomTitleDtos = animalRoomService.listOfFavoriteRoomsByAnimalTitle(animalTitle);
        return new ResponseEntity<>(roomTitleDtos, HttpStatus.OK);
    }
}
