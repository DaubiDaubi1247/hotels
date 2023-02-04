package ru.alex.hotels.mappers;

import org.mapstruct.Mapper;
import ru.alex.hotels.dto.RoomDto;
import ru.alex.hotels.entity.Room;
import ru.alex.hotels.utils.room.RoomCharacteristic;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoomMapper {
    RoomDto toDto(Room roomEntity);
    Room toEntity(RoomDto roomDto);

    List<RoomDto> toDtoList(List<Room> roomEntityList);

    RoomCharacteristic toRoomCharacteristic(RoomDto roomDto);
}
