package ru.alex.hotels.services.impl;

import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import ru.alex.hotels.entitys.CityEntity;
import ru.alex.hotels.exceptions.CItyAlreadyExist;
import ru.alex.hotels.exceptions.CityNotFound;
import ru.alex.hotels.mappers.CityMapper;
import ru.alex.hotels.repositories.CityRepository;
import ru.alex.hotels.services.CityService;
import ru.alex.hotels.dto.City;

@Service
@RequiredArgsConstructor
@Validated
public class CityServiceImpl implements CityService {
    private final CityRepository cityRepository;

    @Override
    public City createCity(City city) throws CItyAlreadyExist, CityNotFound {
        if (isCityAlreadyExist(city))
            throw new CItyAlreadyExist("город с названием = " + city.getName() + " уже существует");

        if (cityRepository.findByIndex(city.getIndex()).isPresent())
            throw new CItyAlreadyExist("город с индексом = " + city.getIndex() + " уже существует");

        CityEntity cityEntity = CityMapper.INSTANCE.cityToCityEntity(city);

        return CityMapper.INSTANCE.cityEntityToCity(cityRepository.save(cityEntity));
    }

    @Override
    public CityEntity getCityEntityByName(@NotBlank String cityName) throws CityNotFound {
        return cityRepository.findByNameIgnoreCase(cityName)
                .orElseThrow(() -> new CityNotFound("город с именем" + cityName + " не найден"));
    }

    private boolean isCityAlreadyExist(City city) throws CityNotFound {
        return getCityEntityByName(city.getName()) != null;
    }
}
