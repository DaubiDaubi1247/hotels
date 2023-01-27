package ru.alex.hotels.services.impl;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import ru.alex.hotels.entitys.CityEntity;
import ru.alex.hotels.exceptions.CItyAlreadyExist;
import ru.alex.hotels.exceptions.WrongIndex;
import ru.alex.hotels.mappers.CityMapper;
import ru.alex.hotels.repositories.CityRepository;
import ru.alex.hotels.services.CityService;
import ru.alex.hotels.tdo.City;

@Service
@Validated
public class CityServiceImpl implements CityService {
    private final CityRepository cityRepository;

    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public City createCity(City city) throws CItyAlreadyExist, WrongIndex {
        if (isCityAlreadyExist(city))
            throw new CItyAlreadyExist("город с названием = " + city.getName() + " уже существует");

        if (!containOnlyNumbers(city))
            throw new WrongIndex("индекс города = " + city.getIndex() + " содержит не числовые символы");

        if (cityRepository.findByIndex(city.getIndex()).isPresent())
            throw new CItyAlreadyExist("город с индексом = " + city.getIndex() + " уже существует");

        CityEntity cityEntity = CityMapper.INSTANCE.cityToCityEntity(city);

        return CityMapper.INSTANCE.cityEntityToCity(cityRepository.save(cityEntity));
    }

    private static boolean containOnlyNumbers(City city) {
        return city.getIndex().matches("\\d+");
    }

    private boolean isCityAlreadyExist(City city) {
        return cityRepository.findByNameIgnoreCase(city.getName()).isPresent();
    }
}
