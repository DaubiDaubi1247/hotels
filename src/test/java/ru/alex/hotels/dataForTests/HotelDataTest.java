package ru.alex.hotels.dataForTests;

import ru.alex.hotels.dto.HotelDto;

import java.util.List;

public class HotelDataTest {
    public static HotelDto testHotel() {

        return HotelDto.builder()
                .name("У Саши")
                .build();
    }

    public static HotelDto testHotelForCreate() {

        return HotelDto.builder()
                .id(1L)
                .name("У Саши")
                .build();
    }

    public static List<HotelDto> testListHotels() {
        return List.of(testHotel(), testHotel());
    }
}
