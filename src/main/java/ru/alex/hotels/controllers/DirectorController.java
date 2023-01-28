package ru.alex.hotels.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.alex.hotels.exceptions.DirectorAlreadyExist;
import ru.alex.hotels.services.DirectorService;
import ru.alex.hotels.tdo.Director;

import java.util.List;

@RestController
@RequestMapping("/director")
@RequiredArgsConstructor
public class DirectorController {
    private final DirectorService directorService;

    @PostMapping
    public ResponseEntity<Director> addDirector(@RequestBody Director director) throws DirectorAlreadyExist {
        return ResponseEntity.status(HttpStatus.CREATED).body(directorService.addDirector(director));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Director>> getDirectorList() {
        return ResponseEntity.ok(directorService.getDirectorList());
    }
}
