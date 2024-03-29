package ru.alex.hotels.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class HotelDto {
    private Long id;
    @NotBlank(message = "Имя отеля должно быть заполено")
    private String name;
}
