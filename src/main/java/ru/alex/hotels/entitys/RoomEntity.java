package ru.alex.hotels.entitys;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RoomEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer roomNumber;

    @Column(
            columnDefinition = "boolean default false"
    )
    private Boolean hasTV;

    @Column(
            columnDefinition = "integer default 1"
    )
    private Integer countBeds;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private HotelEntity hotel;
}
