package ru.alex.hotels.services;

import jakarta.validation.Valid;
import ru.alex.hotels.dto.DirectorDto;
import ru.alex.hotels.entity.Director;

import java.util.List;

public interface DirectorService {
    DirectorDto addDirector(@Valid DirectorDto directorDto);

    Director getDirectorEntityById(Long id);

    List<DirectorDto> getDirectorList();
}
