package ru.alex.hotels.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.alex.hotels.dto.CityDto;
import ru.alex.hotels.entity.City;

@Mapper
public interface CityMapper {
    CityMapper INSTANCE = Mappers.getMapper(CityMapper.class);
    City cityToCityEntity(CityDto cityDto);
    CityDto cityEntityToCity(City cityEntity);
}
