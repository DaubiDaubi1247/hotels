package ru.alex.hotels.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.alex.hotels.exceptions.HotelNotFoundException;
import ru.alex.hotels.exceptions.RoomAlreadyExists;
import ru.alex.hotels.services.RoomService;
import ru.alex.hotels.dto.Room;

import java.util.List;

@RestController
@RequestMapping("/rooms")
@RequiredArgsConstructor
public class RoomController {
    private final RoomService roomService;

    @PostMapping("/{hotelId}")
    public ResponseEntity<Room> createRoom(@PathVariable Long hotelId, @RequestBody Room room) throws RoomAlreadyExists, HotelNotFoundException {
        return ResponseEntity.ok(roomService.addRoom(hotelId, room));
    }

    @GetMapping("/all/{hotelId}")
    public ResponseEntity<List<Room>> getRoomsByHotelId(@PathVariable Long hotelId) throws HotelNotFoundException {
        return ResponseEntity.ok(roomService.getRoomsByHotelId(hotelId));
    }

    @PostMapping("/{hotelId}/param")
    public ResponseEntity<List<Room>> getRoomsByParam(@RequestBody Room room, @PathVariable Long hotelId) throws HotelNotFoundException {
        return ResponseEntity.ok(roomService.getRoomsBySpecification(hotelId, room));
    }
}
