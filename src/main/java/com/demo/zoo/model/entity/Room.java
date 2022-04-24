package com.demo.zoo.model.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "ROOMS")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roomId;
    @Column(name = "TITLE")
    private String title;
    @Column(name = "SIZE")
    private Long size;
    @CreationTimestamp
    @Column(name = "CREATED",updatable = false)
    private LocalDateTime created;
    @Version
    private Long version;
    @OneToMany(mappedBy = "rooms")
    private Set<Favorites> favorites;
    @Column(name = "STATUS")
    private char status;
}
