package ru.alex.hotels.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.alex.hotels.dto.CityDto;
import ru.alex.hotels.entity.City;
import ru.alex.hotels.exceptions.EntityAlreadyExistException;
import ru.alex.hotels.exceptions.EntityNotFoundException;
import ru.alex.hotels.mappers.CityMapper;
import ru.alex.hotels.repositories.CityRepository;
import ru.alex.hotels.services.CityService;

@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {
    private final CityRepository cityRepository;

    @Override
    @Transactional
    public CityDto createCity(CityDto cityDto) {
        if (isCityAlreadyExist(cityDto) || isIndexAlreadyExist(cityDto)) {
            throw new EntityAlreadyExistException("город с названием = " + cityDto.getName() + " или индексом = " +
                    cityDto.getIndex() + " уже существует");
        }

        City cityEntity = CityMapper.INSTANCE.cityToCityEntity(cityDto);

        return CityMapper.INSTANCE.cityEntityToCity(cityRepository.save(cityEntity));
    }

    @Override
    @Transactional
    public City getCityEntityByName(String cityName) {
        return cityRepository.findByNameIgnoreCase(cityName)
                .orElseThrow(() -> new EntityNotFoundException("город с именем" + cityName + " не найден"));
    }

    private boolean isCityAlreadyExist(CityDto cityDto) {
        return cityRepository.existsByNameIgnoreCase(cityDto.getName());
    }

    private boolean isIndexAlreadyExist(CityDto cityDto) {
        return cityRepository.existByIndex(cityDto.getIndex());
    }
}
