package ru.alex.hotels.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.alex.hotels.exceptions.HotelNotFoundException;
import ru.alex.hotels.exceptions.RoomAlreadyExists;
import ru.alex.hotels.services.RoomService;
import ru.alex.hotels.tdo.Room;

@RestController
@RequestMapping("/rooms")
public class RoomController {
    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @PostMapping("/{hotelId}")
    public ResponseEntity<?> createRoom(@PathVariable Long hotelId, Room room) {
        try {
            return ResponseEntity.ok(roomService.addRoom(hotelId, room));
        } catch (RoomAlreadyExists | HotelNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
