package ru.alex.hotels.exceptions;

public class EntityAlreadyExistException extends RuntimeException{
    public EntityAlreadyExistException(String message) {
        super(message);
    }
}
