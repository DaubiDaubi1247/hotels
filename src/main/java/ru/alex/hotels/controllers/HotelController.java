package ru.alex.hotels.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.alex.hotels.exceptions.CityNotFound;
import ru.alex.hotels.exceptions.HotelAlreadyExists;
import ru.alex.hotels.exceptions.HotelNotFoundException;
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
    public ResponseEntity<?> createHotel(@RequestBody Hotel hotel, @RequestParam String cityName) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(hotelService.createHotel(hotel, cityName));
        } catch (HotelAlreadyExists | CityNotFound e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllHotels() {
        try {
            return ResponseEntity.ok(hotelService.getAllHotels());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getHotelById(@RequestParam Long id) {
        try {
            return ResponseEntity.ok(hotelService.getHotelById(id));
        } catch (HotelNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{hotelId}")
    public ResponseEntity<?> updateHotel(@PathVariable Long hotelId, @RequestBody Hotel hotel) {
        try {
            return ResponseEntity.ok(hotelService.updateHotel(hotel, hotelId));
        } catch (HotelNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{cityName}")
    public ResponseEntity<?> getAllHotelsInCity(@PathVariable String cityName) {
        try {
            return ResponseEntity.ok(hotelService.getAllHotelsInCity(cityName));
        } catch (CityNotFound e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

  }
