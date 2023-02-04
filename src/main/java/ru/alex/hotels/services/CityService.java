package ru.alex.hotels.services;

import jakarta.validation.Valid;
import ru.alex.hotels.dto.CityDto;
import ru.alex.hotels.entity.City;

public interface CityService {
    CityDto createCity(@Valid CityDto cityDto);

    City getCityEntityByName(String cityName);
}
