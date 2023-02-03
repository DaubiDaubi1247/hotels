package ru.alex.hotels.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ru.alex.hotels.dto.HotelDto;
import ru.alex.hotels.exceptions.CityNotFound;
import ru.alex.hotels.exceptions.DirectorNotFound;
import ru.alex.hotels.exceptions.HotelAlreadyExists;
import ru.alex.hotels.exceptions.HotelNotFoundException;
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
                                                @RequestParam Long directorId)
            throws HotelAlreadyExists, CityNotFound, DirectorNotFound {

        return ResponseEntity.status(HttpStatus.CREATED).body(hotelService.createHotel(hotelDto, cityName, directorId));
    }

    @GetMapping("/all")
    public ResponseEntity<List<HotelDto>> getAllHotels() {
        return ResponseEntity.ok(hotelService.getAllHotels());
    }

    @GetMapping
    public ResponseEntity<HotelDto> getHotelById(@RequestParam Long id) throws HotelNotFoundException {
        return ResponseEntity.ok(hotelService.getHotelById(id));
    }

    @PutMapping("/{hotelId}")
    public ResponseEntity<HotelDto> updateHotel(@PathVariable Long hotelId, @RequestBody HotelDto hotelDto)
            throws HotelNotFoundException {

        return ResponseEntity.ok(hotelService.updateHotel(hotelDto, hotelId));
    }

    @GetMapping("/{cityName}")
    public ResponseEntity<List<HotelDto>> getAllHotelsInCity(@PathVariable String cityName) throws CityNotFound {

        return ResponseEntity.ok(hotelService.getAllHotelsInCity(cityName));
    }

  }
