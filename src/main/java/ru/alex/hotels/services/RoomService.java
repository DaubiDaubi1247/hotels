package ru.alex.hotels.services;

import jakarta.validation.Valid;
import ru.alex.hotels.dto.RoomDto;

import java.util.List;

public interface RoomService {
    RoomDto addRoom(Long hotelId, @Valid RoomDto roomDto);

    List<RoomDto> getRoomsByHotelId(Long hotelId);

    List<RoomDto> getRoomsBySpecification(Long hotelId, @Valid RoomDto roomDto);
}
