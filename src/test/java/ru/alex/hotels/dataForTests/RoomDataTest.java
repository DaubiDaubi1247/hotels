package ru.alex.hotels.dataForTests;

import org.mapstruct.factory.Mappers;
import ru.alex.hotels.dto.RoomDto;
import ru.alex.hotels.entity.Hotel;
import ru.alex.hotels.entity.Room;
import ru.alex.hotels.mapper.RoomMapper;
import ru.alex.hotels.specification.RoomSpecification;
import ru.alex.hotels.utils.room.RoomCharacteristic;

public class RoomDataTest {

    private static final RoomMapper roomMapper = Mappers.getMapper(RoomMapper.class);
    public static Room testRoomEntity() {
        return Room.builder()
                .roomNumber(1)
                .hasTv(true)
                .countBeds(2)
                .hotel(new Hotel())
                .build();
    }

    public static RoomDto testRoom() {
        return roomMapper.toDto(testRoomEntity());
    }

    public static RoomSpecification testRoomSpec() {
        RoomCharacteristic roomCharacteristic = roomMapper.toRoomCharacteristic(testRoom());
        roomCharacteristic.setHotel(new Hotel());

        return new RoomSpecification(roomCharacteristic);
    }
}
