package ru.alex.hotels.services;

import ru.alex.hotels.exceptions.CityNotFound;
import ru.alex.hotels.exceptions.DirectorNotFound;
import ru.alex.hotels.exceptions.HotelAlreadyExists;
import ru.alex.hotels.exceptions.HotelNotFoundException;
import ru.alex.hotels.tdo.Hotel;

import java.util.List;

public interface HotelService {
    Hotel createHotel(Hotel hotel, String city, String directorFcs) throws HotelAlreadyExists, CityNotFound, DirectorNotFound;

    List<Hotel> getAllHotels();

    Hotel getHotelById(Long id) throws HotelNotFoundException;

    Hotel updateHotel(Hotel hotel, Long id) throws HotelNotFoundException;

    List<Hotel> getAllHotelsInCity(String cityName) throws CityNotFound;
}
