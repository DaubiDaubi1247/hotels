package ru.alex.hotels.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.alex.hotels.dto.RoomDto;
import ru.alex.hotels.entitys.RoomEntity;
import ru.alex.hotels.utils.room.RoomCharacteristic;

import java.util.List;

@Mapper
public interface RoomMapper {

    RoomMapper INSTANCE = Mappers.getMapper(RoomMapper.class);
    RoomDto roomEntityToRoom(RoomEntity roomEntity);
    RoomEntity roomToRoomEntity(RoomDto roomDto);

    List<RoomDto> roomEntityListToRoomList(List<RoomEntity> roomEntityList);

    RoomCharacteristic roomToRoomCharacteristic(RoomDto roomDto);
}
