package ru.alex.hotels.services;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import ru.alex.hotels.dto.DirectorDto;
import ru.alex.hotels.entity.Director;
import ru.alex.hotels.exceptions.DirectorAlreadyExist;
import ru.alex.hotels.exceptions.DirectorNotFound;

import java.util.List;

public interface DirectorService {
    DirectorDto addDirector(@Valid DirectorDto directorDto) throws DirectorAlreadyExist;

    Director getDirectorEntityById(@Min(1) Long id) throws DirectorNotFound;

    List<DirectorDto> getDirectorList();
}
