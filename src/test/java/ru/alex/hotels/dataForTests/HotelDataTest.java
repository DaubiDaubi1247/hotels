package ru.alex.hotels.dataForTests;

import ru.alex.hotels.dto.Hotel;

import java.util.List;

public class HotelDataTest {
    public static Hotel testHotel() {

        return Hotel.builder()
                .name("У Саши")
                .build();
    }

    public static Hotel testHotelForCreate() {

        return Hotel.builder()
                .id(1L)
                .name("У Саши")
                .build();
    }

    public static List<Hotel> testListHotels() {
        return List.of(testHotel(), testHotel());
    }
}
