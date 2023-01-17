package ru.alex.hotels.services;

import ru.alex.hotels.exceptions.HotelAlreadyExists;
import ru.alex.hotels.tdo.Hotel;

public interface HotelService {
    Hotel createHotel(Hotel hotel) throws HotelAlreadyExists;
}
