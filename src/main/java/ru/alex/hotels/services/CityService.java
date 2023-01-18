package ru.alex.hotels.services;

import ru.alex.hotels.exceptions.CItyAlreadyExist;
import ru.alex.hotels.exceptions.WrongIndex;
import ru.alex.hotels.tdo.City;

public interface CityService {
    City createCity(City city) throws CItyAlreadyExist, WrongIndex;
}
