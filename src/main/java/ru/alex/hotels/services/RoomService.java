package ru.alex.hotels.services;

import jakarta.validation.Valid;
import ru.alex.hotels.dto.RoomDto;
import ru.alex.hotels.exceptions.HotelNotFoundException;
import ru.alex.hotels.exceptions.RoomAlreadyExists;

import java.util.List;

public interface RoomService {
    RoomDto addRoom(Long hotelId, @Valid RoomDto roomDto) throws RoomAlreadyExists, HotelNotFoundException;

    List<RoomDto> getRoomsByHotelId(Long hotelId) throws HotelNotFoundException;

    List<RoomDto> getRoomsBySpecification(Long hotelId, RoomDto roomDto) throws HotelNotFoundException;
}
