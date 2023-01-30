package ru.alex.hotels.utils;

import ru.alex.hotels.entitys.CityEntity;
import ru.alex.hotels.entitys.HotelEntity;
import ru.alex.hotels.mappers.HotelMapper;
import ru.alex.hotels.dto.Hotel;

import java.util.ArrayList;

public class HotelUtils {
    public static HotelEntity createHotelEntityAndSetInCity(Hotel hotel, CityEntity cityEntity) {
        HotelEntity hotelToEntity = HotelMapper.INSTANCE.hotelToHotelEntity(hotel);
        hotelToEntity.setCities(new ArrayList<>());
        hotelToEntity.getCities().add(cityEntity);

        cityEntity.getHotels().add(hotelToEntity);

        return hotelToEntity;
    }

    public static void addHotelInCity(CityEntity cityEntity, HotelEntity hotelEntity) {
        cityEntity.getHotels().add(hotelEntity);
        hotelEntity.getCities().add(cityEntity);
    }
}
