package ru.alex.hotels.services;

import jakarta.validation.constraints.NotBlank;
import ru.alex.hotels.dto.CityDto;
import ru.alex.hotels.entitys.City;
import ru.alex.hotels.exceptions.CItyAlreadyExist;
import ru.alex.hotels.exceptions.CityNotFound;
import ru.alex.hotels.exceptions.WrongIndex;

public interface CityService {
    CityDto createCity(CityDto cityDto) throws CItyAlreadyExist, WrongIndex, CityNotFound;

    City getCityEntityByName(@NotBlank String cityName) throws CityNotFound;
}
