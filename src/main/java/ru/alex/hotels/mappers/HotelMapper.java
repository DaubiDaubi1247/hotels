package ru.alex.hotels.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.alex.hotels.entitys.HotelEntity;
import ru.alex.hotels.tdo.Hotel;

@Mapper
public interface HotelMapper {
    HotelMapper INSTANCE = Mappers.getMapper(HotelMapper.class);
    HotelEntity HotelToHotelEntity(Hotel hotel);
    Hotel HotelEntityToHotel(HotelEntity hotelEntity);
}