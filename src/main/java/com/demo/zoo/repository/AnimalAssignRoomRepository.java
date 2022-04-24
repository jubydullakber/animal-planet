package com.demo.zoo.repository;

import com.demo.zoo.model.entity.AnimalAssignRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalAssignRoomRepository extends JpaRepository<AnimalAssignRoom, Long> {
}
