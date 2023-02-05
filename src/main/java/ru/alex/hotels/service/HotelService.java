package ru.alex.hotels.service;

import jakarta.validation.Valid;
import ru.alex.hotels.dto.HotelDto;
import ru.alex.hotels.entity.Hotel;

import java.util.List;

public interface HotelService {
    HotelDto createHotel(@Valid HotelDto hotelDto, String city, Long directorId);

    List<HotelDto> getAllHotels();

    HotelDto getHotelById(Long id) ;
    Hotel getHotelEntityById(Long id) ;

    HotelDto updateHotel(@Valid HotelDto hotelDto, Long id) ;

    List<HotelDto> getAllHotelsInCity(String cityName) ;

    boolean isHotelExist(Long hotelId);
}
