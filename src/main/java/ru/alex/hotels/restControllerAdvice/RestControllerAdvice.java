package ru.alex.hotels.restControllerAdvice;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.alex.hotels.exceptions.*;

@org.springframework.web.bind.annotation.RestControllerAdvice
public class RestControllerAdvice {

    private ResponseEntity<ErrorMessage> getResponseEntityWIthStatus(HttpStatus status, Exception exception) {
        return ResponseEntity
                .status(status)
                .body(new ErrorMessage(exception.getMessage()));
    }

    @ExceptionHandler({CityNotFound.class, HotelNotFoundException.class, DirectorNotFound.class})
    public ResponseEntity<ErrorMessage> notFoundException(Exception exception) {
        return getResponseEntityWIthStatus(HttpStatus.NOT_FOUND, exception);
    }

    @ExceptionHandler({CItyAlreadyExist.class, DirectorAlreadyExist.class,
            HotelAlreadyExists.class, RoomAlreadyExists.class})
    public ResponseEntity<ErrorMessage> alreadyExistException(Exception exception) {
        return getResponseEntityWIthStatus(HttpStatus.BAD_REQUEST, exception);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorMessage> constraintViolationException(Exception exception) {
        return getResponseEntityWIthStatus(HttpStatus.BAD_REQUEST, exception);
    }

}

