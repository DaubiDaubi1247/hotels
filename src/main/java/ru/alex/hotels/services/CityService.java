package ru.alex.hotels.services;

import jakarta.validation.constraints.NotBlank;
import ru.alex.hotels.entitys.CityEntity;
import ru.alex.hotels.exceptions.CItyAlreadyExist;
import ru.alex.hotels.exceptions.CityNotFound;
import ru.alex.hotels.exceptions.WrongIndex;
import ru.alex.hotels.dto.City;

public interface CityService {
    City createCity(City city) throws CItyAlreadyExist, WrongIndex, CityNotFound;

    CityEntity getCityEntityByName(@NotBlank String cityName) throws CityNotFound;
}
