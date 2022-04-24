package com.demo.zoo.model.entity;


import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ANIMAL_ASSIGN_ROOM")
public class AnimalAssignRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "animal_Id")
    private Animal animals;
    @Column(name = "roomId")
    private Long roomId;
    @Column(name = "STATUS")
    private char status;
}
