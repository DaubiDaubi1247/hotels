package ru.alex.hotels.services;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import ru.alex.hotels.entitys.DirectorEntity;
import ru.alex.hotels.exceptions.DirectorAlreadyExist;
import ru.alex.hotels.exceptions.DirectorNotFound;
import ru.alex.hotels.tdo.Director;

import java.util.List;

public interface DirectorService {
    Director addDirector(@Valid Director director) throws DirectorAlreadyExist;

    DirectorEntity getDirectorEntityById(@Min(1) Long id) throws DirectorNotFound;

    List<Director> getDirectorList();
}
