package ru.alex.hotels.services;

import ru.alex.hotels.exceptions.HotelAlreadyExists;
import ru.alex.hotels.exceptions.HotelNotFoundException;
import ru.alex.hotels.tdo.Hotel;

import java.util.List;

public interface HotelService {
    Hotel createHotel(Hotel hotel) throws HotelAlreadyExists;

    List<Hotel> getAllHotels();

    Hotel getHotelById(Long id) throws HotelNotFoundException;

    Hotel updateHotel(Hotel hotel, Long id) throws HotelNotFoundException;
}
