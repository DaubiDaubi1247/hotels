package ru.alex.hotels.utils;

import ru.alex.hotels.entitys.City;
import ru.alex.hotels.entitys.Hotel;
import ru.alex.hotels.mappers.HotelMapper;
import ru.alex.hotels.dto.HotelDto;

import java.util.ArrayList;

public class HotelUtils {
    public static Hotel createHotelEntityAndSetInCity(HotelDto hotelDto, City cityEntity) {
        Hotel hotelToEntity = HotelMapper.INSTANCE.hotelToHotelEntity(hotelDto);
        hotelToEntity.setCities(new ArrayList<>());
        hotelToEntity.getCities().add(cityEntity);

        cityEntity.getHotels().add(hotelToEntity);

        return hotelToEntity;
    }

    public static void addHotelInCity(City cityEntity, Hotel hotelEntity) {
        cityEntity.getHotels().add(hotelEntity);
        hotelEntity.getCities().add(cityEntity);
    }
}
