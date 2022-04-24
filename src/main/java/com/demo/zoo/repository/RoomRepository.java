package com.demo.zoo.repository;

import com.demo.zoo.model.dto.RoomTitleDto;
import com.demo.zoo.model.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    @Query("SELECT new com.demo.zoo.model.dto.RoomTitleDto( R.title ) FROM Room R JOIN Favorites F WHERE F.animals.animalId =:animalId and F.status=:status")
    List<RoomTitleDto> findFavoriteRoomTitleByAnimalId(@Param("animalId") Long animalId, @Param("status") char status);
}
