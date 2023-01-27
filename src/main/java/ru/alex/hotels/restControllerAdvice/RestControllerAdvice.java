package ru.alex.hotels.restControllerAdvice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.alex.hotels.exceptions.*;

@org.springframework.web.bind.annotation.RestControllerAdvice
public class RestControllerAdvice {

    @ExceptionHandler({CityNotFound.class, HotelNotFoundException.class, DirectorNotFound.class})
    public ResponseEntity<ErrorMessage> notFoundException(CityNotFound exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorMessage(exception.getMessage()));
    }

    @ExceptionHandler({CItyAlreadyExist.class, DirectorAlreadyExist.class,
            HotelAlreadyExists.class, RoomAlreadyExists.class})
    public ResponseEntity<ErrorMessage> alreadyExistException(CityNotFound exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorMessage(exception.getMessage()));
    }

}

