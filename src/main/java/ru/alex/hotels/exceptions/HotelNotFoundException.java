package ru.alex.hotels.exceptions;


public class HotelNotFoundException extends Exception{
    public HotelNotFoundException(String hotelName) {
        super("отель с названием = " + hotelName + " не найден");
    }

    public HotelNotFoundException(Long hotelId) {
        super("отель с id = " + hotelId + " не найден");
    }
}
