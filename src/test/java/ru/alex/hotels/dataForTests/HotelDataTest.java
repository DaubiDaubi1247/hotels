package ru.alex.hotels.dataForTests;

import ru.alex.hotels.tdo.Hotel;

import java.util.List;

public class HotelDataTest {
    public static Hotel testHotel() {

        return Hotel.builder()
                .name("У Саши2")
                .build();
    }

    public static List<Hotel> testListHotels() {
        return List.of(testHotel(), testHotel());
    }
}
