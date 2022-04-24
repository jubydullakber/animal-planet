package com.demo.zoo.repository;

import com.demo.zoo.model.dto.AnimalBriefDto;
import com.demo.zoo.model.entity.Animal;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {

    @Query("SELECT new com.demo.zoo.model.dto.AnimalBriefDto( A.title,A.located ) FROM Animal A JOIN AnimalAssignRoom AAR  WHERE AAR.status =:status")
    List<AnimalBriefDto> findAnimalInfoNotAssignedRoom(@Param("status") char status, Sort sort);

    @Query("SELECT new com.demo.zoo.model.dto.AnimalBriefDto( A.title,A.located ) FROM Animal A JOIN AnimalAssignRoom AAR  WHERE AAR.roomId =:roomId")
    List<AnimalBriefDto> findAnimalByInRoomId(@Param("roomId") Long roomId,Sort sort);

    Animal findAnimalByTitle(String title);
}
