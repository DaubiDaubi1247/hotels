package ru.alex.hotels.mappers;

import org.mapstruct.Mapper;
import ru.alex.hotels.entitys.RoomEntity;
import ru.alex.hotels.tdo.Room;

@Mapper
public interface RoomMapper {
    Room roomEntityToRoom(RoomEntity roomEntity);
    RoomEntity roomToRoomEntity(Room room);
}
