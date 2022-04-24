package com.demo.zoo.model.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "ANIMALS")
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long animalId;
    @Column(name = "TITLE")
    private String title;
    @CreationTimestamp
    @Column(name = "LOCATED")
    private LocalDateTime located;
    @Column(name = "TYPE")
    private String type;
    @Column(name = "PREFERENCE")
    private Long preference;
    @Version
    private Long version;
    @OneToMany(mappedBy = "animals")
    private Set<Favorites> favorites;
    @OneToMany(mappedBy = "animals")
    private Set<AnimalAssignRoom> animalAssignRooms;
    @Column(name = "STATUS")
    private char status;
}
