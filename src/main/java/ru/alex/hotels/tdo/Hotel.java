package ru.alex.hotels.tdo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.alex.hotels.entitys.DirectorEntity;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Hotel {
    private Long id;
    private String name;
    private DirectorEntity directorID;
}
