package ru.alex.hotels.services;

import ru.alex.hotels.exceptions.HotelNotFoundException;
import ru.alex.hotels.exceptions.RoomAlreadyExists;
import ru.alex.hotels.tdo.Room;

public interface RoomService {
    Room addRoom(Long hotelId, Room room) throws RoomAlreadyExists, HotelNotFoundException;
}
