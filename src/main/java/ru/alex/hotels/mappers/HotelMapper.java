package ru.alex.hotels.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.alex.hotels.dto.HotelDto;
import ru.alex.hotels.entity.Hotel;

import java.util.List;

@Mapper
public interface HotelMapper {
    HotelMapper INSTANCE = Mappers.getMapper(HotelMapper.class);
    Hotel hotelToHotelEntity(HotelDto hotelDto);
    HotelDto hotelEntityToHotel(Hotel hotelEntity);

    List<HotelDto> hotelEntityListToHotelList(List<Hotel> hotelEntityList);
    List<Hotel> hotelsToHotelEntities(List<HotelDto> hotelDtos);
}
