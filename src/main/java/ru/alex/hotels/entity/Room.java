package ru.alex.hotels.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "room")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "room_number")
    private Integer roomNumber;

    @Column(
            columnDefinition = "boolean default false",
            name = "has_tv"
    )
    private Boolean hasTv;

    @Column(
            columnDefinition = "integer default 1",
            name = "count_beds"
    )
    private Integer countBeds;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;
}
