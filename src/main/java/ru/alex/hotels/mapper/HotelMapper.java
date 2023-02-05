package ru.alex.hotels.mapper;

import org.mapstruct.Mapper;
import ru.alex.hotels.dto.HotelDto;
import ru.alex.hotels.entity.Hotel;

import java.util.List;

@Mapper(componentModel = "spring")
public interface HotelMapper {
    Hotel toEntity(HotelDto hotelDto);
    HotelDto toDto(Hotel hotelEntity);

    List<HotelDto> toDtoList(List<Hotel> hotelEntityList);
    List<Hotel> toEntityList(List<HotelDto> hotelDtos);
}
