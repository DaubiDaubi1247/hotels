package ru.alex.hotels.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class RoomDto {
    private Long id;

    protected Boolean hasTv;
    private Integer roomNumber;
    protected Integer countBeds;
}
