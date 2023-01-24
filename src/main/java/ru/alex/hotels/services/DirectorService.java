package ru.alex.hotels.services;

import ru.alex.hotels.exceptions.DirectorAlreadyExist;
import ru.alex.hotels.exceptions.HotelNotFoundException;
import ru.alex.hotels.tdo.Director;

public interface DirectorService {
    Director addDirector(Long hotelId, Director director) throws HotelNotFoundException, DirectorAlreadyExist;
}
