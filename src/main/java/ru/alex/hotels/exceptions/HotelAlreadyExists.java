package ru.alex.hotels.exceptions;

import org.springframework.http.HttpStatus;

public class HotelAlreadyExists extends RootException{

    public HotelAlreadyExists(String message, HttpStatus status) {
        super(message, status);
    }
}
