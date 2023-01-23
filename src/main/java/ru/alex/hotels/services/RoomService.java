package ru.alex.hotels.services;

import ru.alex.hotels.exceptions.HotelNotFoundException;
import ru.alex.hotels.exceptions.RoomAlreadyExists;
import ru.alex.hotels.tdo.Room;

import java.util.List;

public interface RoomService {
    Room addRoom(Long hotelId, Room room) throws RoomAlreadyExists, HotelNotFoundException;

    List<Room> getRoomsByHotel(Long hotelId) throws HotelNotFoundException;
}
