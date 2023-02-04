package ru.alex.hotels.mappers;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import ru.alex.hotels.dto.CityDto;
import ru.alex.hotels.entity.City;

@Mapper
@Component
public interface CityMapper {
    City toEntity(CityDto cityDto);
    CityDto toDto(City cityEntity);
}
