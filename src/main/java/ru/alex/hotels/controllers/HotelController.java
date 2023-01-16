package ru.alex.hotels.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    private HotelService hotelService;

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @PostMapping
    public ResponseEntity<?> createHotel(Hotel hotel) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(hotelService.createHotel(hotel));
        } catch () {

        }
    }
}
