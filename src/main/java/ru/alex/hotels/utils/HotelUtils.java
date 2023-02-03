package ru.alex.hotels.utils;

import ru.alex.hotels.entitys.City;
import ru.alex.hotels.entitys.HotelEntity;
import ru.alex.hotels.mappers.HotelMapper;
import ru.alex.hotels.dto.HotelDto;

import java.util.ArrayList;

public class HotelUtils {
    public static HotelEntity createHotelEntityAndSetInCity(HotelDto hotelDto, City cityEntity) {
        HotelEntity hotelToEntity = HotelMapper.INSTANCE.hotelToHotelEntity(hotelDto);
        hotelToEntity.setCity(new ArrayList<>());
        hotelToEntity.getCity().add(cityEntity);

        cityEntity.getHotels().add(hotelToEntity);

        return hotelToEntity;
    }

    public static void addHotelInCity(City cityEntity, HotelEntity hotelEntity) {
        cityEntity.getHotels().add(hotelEntity);
        hotelEntity.getCity().add(cityEntity);
    }
}
