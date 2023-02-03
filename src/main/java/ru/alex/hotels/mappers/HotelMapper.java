package ru.alex.hotels.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.alex.hotels.dto.HotelDto;
import ru.alex.hotels.entitys.HotelEntity;

import java.util.List;

@Mapper
public interface HotelMapper {
    HotelMapper INSTANCE = Mappers.getMapper(HotelMapper.class);
    HotelEntity hotelToHotelEntity(HotelDto hotelDto);
    HotelDto hotelEntityToHotel(HotelEntity hotelEntity);

    List<HotelDto> hotelEntityListToHotelList(List<HotelEntity> hotelEntityList);
    List<HotelEntity> hotelsToHotelEntities(List<HotelDto> hotelDtos);
}
