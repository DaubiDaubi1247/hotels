package ru.alex.hotels.dataForTests;

import ru.alex.hotels.entitys.HotelEntity;
import ru.alex.hotels.entitys.RoomEntity;

public class RoomDataTest {
    public static RoomEntity testRoom() {
        return RoomEntity.builder()
                .roomNumber(1)
                .hasTV(true)
                .countBeds(2)
                .hotel(new HotelEntity())
                .build();
    }
}
