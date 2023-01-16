package ru.alex.hotels.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public abstract class RootException extends Exception{
    private final HttpStatus status;

    public RootException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}
