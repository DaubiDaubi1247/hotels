package ru.alex.hotels.dto;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.alex.hotels.validations.annotations.MyMinConstraint;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Room {
    private Long id;

    @MyMinConstraint(min = -1, message = "номер комнаты должен быть > -1")
    private Integer roomNumber;

    private Boolean hasTV;

    @Min(1)
    private Integer countBeds;
}
