package ru.alex.hotels.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.alex.hotels.exceptions.HotelAlreadyExists;
import ru.alex.hotels.services.HotelService;
import ru.alex.hotels.tdo.Hotel;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    private final HotelService hotelService;

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @PostMapping
    public ResponseEntity<?> createHotel(@RequestBody Hotel hotel) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(hotelService.createHotel(hotel));
        } catch (HotelAlreadyExists e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
