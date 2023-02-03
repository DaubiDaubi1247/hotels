package ru.alex.hotels.services.impl;

import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import ru.alex.hotels.dto.CityDto;
import ru.alex.hotels.entitys.CityEntity;
import ru.alex.hotels.exceptions.CItyAlreadyExist;
import ru.alex.hotels.exceptions.CityNotFound;
import ru.alex.hotels.mappers.CityMapper;
import ru.alex.hotels.repositories.CityRepository;
import ru.alex.hotels.services.CityService;

@Service
@RequiredArgsConstructor
@Validated
public class CityServiceImpl implements CityService {
    private final CityRepository cityRepository;

    @Override
    public CityDto createCity(CityDto cityDto) throws CItyAlreadyExist, CityNotFound {
        if (isCityAlreadyExist(cityDto))
            throw new CItyAlreadyExist("город с названием = " + cityDto.getName() + " уже существует");

        if (cityRepository.findByIndex(cityDto.getIndex()).isPresent())
            throw new CItyAlreadyExist("город с индексом = " + cityDto.getIndex() + " уже существует");

        CityEntity cityEntity = CityMapper.INSTANCE.cityToCityEntity(cityDto);

        return CityMapper.INSTANCE.cityEntityToCity(cityRepository.save(cityEntity));
    }

    @Override
    public CityEntity getCityEntityByName(@NotBlank String cityName) throws CityNotFound {
        return cityRepository.findByNameIgnoreCase(cityName)
                .orElseThrow(() -> new CityNotFound("город с именем" + cityName + " не найден"));
    }

    private boolean isCityAlreadyExist(CityDto cityDto) throws CityNotFound {
        return getCityEntityByName(cityDto.getName()) != null;
    }
}
