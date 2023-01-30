package ru.alex.hotels.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.alex.hotels.entitys.RoomEntity;
import ru.alex.hotels.dto.Room;

import java.util.List;

@Mapper
public interface RoomMapper {

    RoomMapper INSTANCE = Mappers.getMapper(RoomMapper.class);
    Room roomEntityToRoom(RoomEntity roomEntity);
    RoomEntity roomToRoomEntity(Room room);

    List<Room> roomEntityListToRoomList(List<RoomEntity> roomEntityList);
}
