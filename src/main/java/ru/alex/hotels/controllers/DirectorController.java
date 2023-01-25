package ru.alex.hotels.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
            return ResponseEntity.ok(directorService.addDirector(director));
        } catch (DirectorAlreadyExist | InvalidPhone e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
