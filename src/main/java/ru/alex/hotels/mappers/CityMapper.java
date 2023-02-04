package ru.alex.hotels.mappers;

import org.mapstruct.Mapper;
import ru.alex.hotels.dto.CityDto;
import ru.alex.hotels.entity.City;

@Mapper(componentModel = "spring")
public interface CityMapper {
    City toEntity(CityDto cityDto);
    CityDto toDto(City cityEntity);
}
