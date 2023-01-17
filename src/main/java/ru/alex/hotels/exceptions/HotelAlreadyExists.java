package ru.alex.hotels.exceptions;


public class HotelAlreadyExists extends Exception{

    public HotelAlreadyExists(String message) {
        super(message);
    }
}
