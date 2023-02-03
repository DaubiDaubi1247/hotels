package ru.alex.hotels.entitys;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

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

    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
