package ru.alex.hotels.exceptions;

public class DirectorNotFound extends Exception{
    public DirectorNotFound(String fcs) {
        super("Директор с ФИО = " + fcs + " не найден");
    }

    public DirectorNotFound(Long id) {
        super("Директор с id = " + id + " не найден");
    }
}
