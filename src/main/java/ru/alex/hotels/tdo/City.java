package ru.alex.hotels.tdo;

import jakarta.validation.constraints.NotBlank;
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
    private String index;
}
