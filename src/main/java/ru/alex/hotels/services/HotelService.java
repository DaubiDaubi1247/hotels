package ru.alex.hotels.services;

import jakarta.validation.Valid;
import ru.alex.hotels.dto.HotelDto;
import ru.alex.hotels.entity.Hotel;
import ru.alex.hotels.exceptions.CityNotFound;
import ru.alex.hotels.exceptions.DirectorNotFound;
import ru.alex.hotels.exceptions.HotelAlreadyExists;
import ru.alex.hotels.exceptions.HotelNotFoundException;

import java.util.List;

public interface HotelService {
    HotelDto createHotel(@Valid HotelDto hotelDto, String city, Long directorId) throws HotelAlreadyExists, CityNotFound, DirectorNotFound;

    List<HotelDto> getAllHotels();

    HotelDto getHotelById(Long id) throws HotelNotFoundException;
    Hotel getHotelEntityById(Long id) throws HotelNotFoundException;

    HotelDto updateHotel(@Valid HotelDto hotelDto, Long id) throws HotelNotFoundException;

    List<HotelDto> getAllHotelsInCity(String cityName) throws CityNotFound;
}
