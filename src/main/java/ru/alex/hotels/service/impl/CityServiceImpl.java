package ru.alex.hotels.service.impl;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import ru.alex.hotels.dto.CityDto;
import ru.alex.hotels.entity.City;
import ru.alex.hotels.exception.EntityAlreadyExistException;
import ru.alex.hotels.exception.EntityNotFoundException;
import ru.alex.hotels.mapper.CityMapper;
import ru.alex.hotels.repository.CityRepository;
import ru.alex.hotels.service.CityService;

@Service
@RequiredArgsConstructor
@Validated
public class CityServiceImpl implements CityService {
    private final CityRepository cityRepository;
    private final CityMapper cityMapper;

    @Override
    @Transactional
    public CityDto createCity(@Valid CityDto cityDto) {
        if (isCityAlreadyExist(cityDto) || isIndexAlreadyExist(cityDto)) {
            throw new EntityAlreadyExistException("город с названием = " + cityDto.getName() + " или индексом = " +
                    cityDto.getIndex() + " уже существует");
        }

        City cityEntity = cityMapper.toEntity(cityDto);

        return cityMapper.toDto(cityRepository.save(cityEntity));
    }

    @Override
    @Transactional
    public City getCityEntityByName(String cityName) {
        return cityRepository.findByNameIgnoreCase(cityName)
                .orElseThrow(() -> new EntityNotFoundException("город с именем" + cityName + " не найден"));
    }

    @Override
    @Transactional
    public City saveCity(@Valid City city) {
        return cityRepository.save(city);
    }

    private boolean isCityAlreadyExist(CityDto cityDto) {
        return cityRepository.existsByNameIgnoreCase(cityDto.getName());
    }

    private boolean isIndexAlreadyExist(CityDto cityDto) {
        return cityRepository.existsByIndex(cityDto.getIndex());
    }
}
