package ru.alex.hotels.services;

import jakarta.validation.Valid;
import ru.alex.hotels.entitys.HotelEntity;
import ru.alex.hotels.exceptions.CityNotFound;
import ru.alex.hotels.exceptions.DirectorNotFound;
import ru.alex.hotels.exceptions.HotelAlreadyExists;
import ru.alex.hotels.exceptions.HotelNotFoundException;
import ru.alex.hotels.dto.Hotel;

import java.util.List;

public interface HotelService {
    Hotel createHotel(@Valid Hotel hotel, String city, Long directorId) throws HotelAlreadyExists, CityNotFound, DirectorNotFound;

    List<Hotel> getAllHotels();

    Hotel getHotelById(Long id) throws HotelNotFoundException;
    HotelEntity getHotelEntityById(Long id) throws HotelNotFoundException;

    Hotel updateHotel(@Valid Hotel hotel, Long id) throws HotelNotFoundException;

    List<Hotel> getAllHotelsInCity(String cityName) throws CityNotFound;
}
