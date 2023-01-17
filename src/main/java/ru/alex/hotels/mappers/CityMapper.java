package ru.alex.hotels.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.alex.hotels.entitys.CityEntity;
import ru.alex.hotels.tdo.City;

@Mapper
public interface CityMapper {
    CityMapper INSTANCE = Mappers.getMapper(CityMapper.class);
    CityEntity cityToCityEntity(City city);
    City cityEntityToCity(CityEntity cityEntity);
}
