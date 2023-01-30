package ru.alex.hotels.services;

import jakarta.validation.Valid;
import ru.alex.hotels.exceptions.HotelNotFoundException;
import ru.alex.hotels.exceptions.RoomAlreadyExists;
import ru.alex.hotels.dto.Room;

import java.util.List;

public interface RoomService {
    Room addRoom(Long hotelId, @Valid Room room) throws RoomAlreadyExists, HotelNotFoundException;

    List<Room> getRoomsByHotelId(Long hotelId) throws HotelNotFoundException;
}
