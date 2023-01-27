package ru.alex.hotels.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ru.alex.hotels.exceptions.CityNotFound;
import ru.alex.hotels.exceptions.DirectorNotFound;
import ru.alex.hotels.exceptions.HotelAlreadyExists;
import ru.alex.hotels.exceptions.HotelNotFoundException;
import ru.alex.hotels.services.HotelService;
import ru.alex.hotels.tdo.Hotel;

import java.util.List;

@RestController
@RequestMapping("/hotels")
@RequiredArgsConstructor
public class HotelController {

    private final HotelService hotelService;

    @PostMapping
    public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel,
                                             @RequestParam String cityName,
                                             @RequestParam Long directorId)
            throws HotelAlreadyExists, CityNotFound, DirectorNotFound {
        return ResponseEntity.status(HttpStatus.CREATED).body(hotelService.createHotel(hotel, cityName, directorId));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Hotel>> getAllHotels() {
        return ResponseEntity.ok(hotelService.getAllHotels());
    }

    @GetMapping
    public ResponseEntity<?> getHotelById(@RequestParam Long id) throws HotelNotFoundException {
        return ResponseEntity.ok(hotelService.getHotelById(id));
    }

    @PutMapping("/{hotelId}")
    public ResponseEntity<Hotel> updateHotel(@PathVariable Long hotelId, @RequestBody Hotel hotel) throws HotelNotFoundException {
        return ResponseEntity.ok(hotelService.updateHotel(hotel, hotelId));
    }

    @GetMapping("/{cityName}")
    public ResponseEntity<List<Hotel>> getAllHotelsInCity(@PathVariable String cityName) throws CityNotFound {
        return ResponseEntity.ok(hotelService.getAllHotelsInCity(cityName));
    }

  }
