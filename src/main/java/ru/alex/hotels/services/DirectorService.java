package ru.alex.hotels.services;

import ru.alex.hotels.exceptions.DirectorAlreadyExist;
import ru.alex.hotels.exceptions.InvalidPhone;
import ru.alex.hotels.tdo.Director;

import java.util.List;

public interface DirectorService {
    Director addDirector(Director director) throws DirectorAlreadyExist, InvalidPhone;

    List<Director> getDirectorList();
}
