package ru.alex.hotels.tdo;

import jakarta.validation.constraints.NotBlank;
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

    @NotBlank(message = "")
    private String fcs;

    private String phone;

}
