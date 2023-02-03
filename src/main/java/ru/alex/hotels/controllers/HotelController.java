package ru.alex.hotels.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ru.alex.hotels.dto.HotelDto;
import ru.alex.hotels.services.HotelService;

import java.util.List;

@RestController
@RequestMapping("/hotels")
@RequiredArgsConstructor
public class HotelController {

    private final HotelService hotelService;

    @PostMapping
    public ResponseEntity<HotelDto> createHotel(@RequestBody HotelDto hotelDto,
                                                @RequestParam String cityName,
                                                @RequestParam Long directorId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(hotelService.createHotel(hotelDto, cityName, directorId));
    }

    @GetMapping("/all")
    public ResponseEntity<List<HotelDto>> getAllHotels() {
        return ResponseEntity.ok(hotelService.getAllHotels());
    }

    @GetMapping
    public ResponseEntity<HotelDto> getHotelById(@RequestParam Long id) {
        return ResponseEntity.ok(hotelService.getHotelById(id));
    }

    @PutMapping("/{hotelId}")
    public ResponseEntity<HotelDto> updateHotel(@PathVariable Long hotelId, @RequestBody HotelDto hotelDto) {

        return ResponseEntity.ok(hotelService.updateHotel(hotelDto, hotelId));
    }

    @GetMapping("/{cityName}")
    public ResponseEntity<List<HotelDto>> getAllHotelsInCity(@PathVariable String cityName) {

        return ResponseEntity.ok(hotelService.getAllHotelsInCity(cityName));
    }

  }
