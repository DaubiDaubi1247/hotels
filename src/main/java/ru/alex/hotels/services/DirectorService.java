package ru.alex.hotels.services;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import ru.alex.hotels.dto.DirectorDto;
import ru.alex.hotels.entitys.DirectorEntity;
import ru.alex.hotels.exceptions.DirectorAlreadyExist;
import ru.alex.hotels.exceptions.DirectorNotFound;

import java.util.List;

public interface DirectorService {
    DirectorDto addDirector(@Valid DirectorDto directorDto) throws DirectorAlreadyExist;

    DirectorEntity getDirectorEntityById(@Min(1) Long id) throws DirectorNotFound;

    List<DirectorDto> getDirectorList();
}
