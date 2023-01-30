package ru.alex.hotels.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Director {
    private Long id;

    @NotBlank(message = "ФИО директора должны быть заполнены")
    private String fcs;

    @Pattern(regexp = "(\\+7|8)\\d{10}", message = "Телефон должен соотвествовать шаблону \\+7|8)\\d{10}")
    private String phone;

}
