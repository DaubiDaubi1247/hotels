package ru.alex.hotels.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class City {
    private Long id;

    @NotBlank(message = "Имя города должно быть заполнено")
    private String name;

    @NotBlank(message = "Индекс города должен быть заполнен")
    @Pattern(regexp = "\\d+", message = "Индекс должен содержать только цифры")
    private String index;
}
