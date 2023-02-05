package ru.alex.hotels.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ru.alex.hotels.validator.annotations.MyMinConstraint;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class RoomDto {
    private Long id;

    protected Boolean hasTv;
    private Integer roomNumber;

//    @MyMinConstraint(min = -1, message = "номер комнаты должен быть > -1")
    protected Integer countBeds;
}
