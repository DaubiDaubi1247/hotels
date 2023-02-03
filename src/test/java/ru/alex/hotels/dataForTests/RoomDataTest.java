package ru.alex.hotels.dataForTests;

import ru.alex.hotels.dto.RoomDto;
import ru.alex.hotels.entity.Hotel;
import ru.alex.hotels.entity.Room;
import ru.alex.hotels.mappers.RoomMapper;
import ru.alex.hotels.specifications.RoomSpecification;
import ru.alex.hotels.utils.room.RoomCharacteristic;

public class RoomDataTest {
    public static Room testRoomEntity() {
        return Room.builder()
                .roomNumber(1)
                .hasTv(true)
                .countBeds(2)
                .hotel(new Hotel())
                .build();
    }

    public static RoomDto testRoom() {
        return RoomMapper.INSTANCE.roomEntityToRoom(testRoomEntity());
    }

    public static RoomSpecification testRoomSpec() {
        RoomCharacteristic roomCharacteristic = RoomMapper.INSTANCE.roomToRoomCharacteristic(testRoom());
        roomCharacteristic.setHotel(new Hotel());

        return new RoomSpecification(roomCharacteristic);
    }
}
