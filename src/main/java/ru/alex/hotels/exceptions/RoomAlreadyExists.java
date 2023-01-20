package ru.alex.hotels.exceptions;

public class RoomAlreadyExists extends Exception{
    public RoomAlreadyExists(String message) {
        super(message);
    }
}
