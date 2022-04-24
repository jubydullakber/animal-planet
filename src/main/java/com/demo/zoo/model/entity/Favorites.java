package com.demo.zoo.model.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "FAVORITES")
public class Favorites {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "animalId")
    private Animal animals;
    @ManyToOne
    @JoinColumn(name = "roomId")
    private Room rooms;
    @Column(name = "STATUS")
    private char status;
}
