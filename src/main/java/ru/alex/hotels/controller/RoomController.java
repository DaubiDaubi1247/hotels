package ru.alex.hotels.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ru.alex.hotels.service.RoomService;
import ru.alex.hotels.dto.RoomDto;

import java.util.List;

@RestController
@RequestMapping("/rooms")
@RequiredArgsConstructor
public class RoomController {
    private final RoomService roomService;

    @PostMapping("/{hotelId}")
    public ResponseEntity<RoomDto> createRoom(@PathVariable Long hotelId, @RequestBody RoomDto roomDto) {
        return ResponseEntity.ok(roomService.addRoom(hotelId, roomDto));
    }

    @GetMapping("/all/{hotelId}")
    public ResponseEntity<List<RoomDto>> getRoomsByHotelId(@PathVariable Long hotelId) {
        return ResponseEntity.ok(roomService.getRoomsByHotelId(hotelId));
    }

    @PostMapping("/{hotelId}/param")
    public ResponseEntity<List<RoomDto>> getRoomsByParam(@RequestBody RoomDto roomDto, @PathVariable Long hotelId) {
        return ResponseEntity.ok(roomService.getRoomsBySpecification(hotelId, roomDto));
    }
}
