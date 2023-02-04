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

    @ExceptionHandler({EntityNotFoundException.class, ConstraintViolationException.class})
    public ResponseEntity<ErrorMessage> notFoundException(Exception exception) {
        return getResponseEntityWIthStatus(HttpStatus.NOT_FOUND, exception);
    }

    @ExceptionHandler(EntityAlreadyExistException.class)
    public ResponseEntity<ErrorMessage> alreadyExistException(Exception exception) {
        return getResponseEntityWIthStatus(HttpStatus.BAD_REQUEST, exception);
    }
}

