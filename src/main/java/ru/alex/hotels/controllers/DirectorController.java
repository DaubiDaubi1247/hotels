package ru.alex.hotels.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.alex.hotels.services.DirectorService;
import ru.alex.hotels.dto.DirectorDto;

import java.util.List;

@RestController
@RequestMapping("/director")
@RequiredArgsConstructor
public class DirectorController {
    private final DirectorService directorService;

    @PostMapping
    public ResponseEntity<DirectorDto> addDirector(@RequestBody DirectorDto directorDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(directorService.addDirector(directorDto));
    }

    @GetMapping("/all")
    public ResponseEntity<List<DirectorDto>> getDirectorList() {
        return ResponseEntity.ok(directorService.getDirectorList());
    }
}
