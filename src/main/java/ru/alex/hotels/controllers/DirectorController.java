package ru.alex.hotels.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.alex.hotels.exceptions.DirectorAlreadyExist;
import ru.alex.hotels.exceptions.InvalidPhone;
import ru.alex.hotels.services.DirectorService;
import ru.alex.hotels.tdo.Director;

@RestController
@RequestMapping("/director")
@AllArgsConstructor
public class DirectorController {
    private DirectorService directorService;

    @PostMapping
    public ResponseEntity<?> addDirector(@RequestBody Director director) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(directorService.addDirector(director));
        } catch (DirectorAlreadyExist | InvalidPhone e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getDirectorList() {
        try {
            return ResponseEntity.ok(directorService.getDirectorList());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
