package ru.alex.hotels.services.impl;

import ru.alex.hotels.repositories.CityRepository;
import ru.alex.hotels.services.CityService;
import ru.alex.hotels.tdo.City;

public class CityServiceImpl implements CityService {
    private CityRepository cityRepository;

    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public City createCity(City city) {
        if (cityRepository.findByName(city.getName()).isPresent())

    }
}
