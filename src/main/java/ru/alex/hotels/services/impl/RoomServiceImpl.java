package ru.alex.hotels.services.impl;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import ru.alex.hotels.dto.Room;
import ru.alex.hotels.entitys.HotelEntity;
import ru.alex.hotels.entitys.RoomEntity;
import ru.alex.hotels.exceptions.HotelNotFoundException;
import ru.alex.hotels.mappers.RoomMapper;
import ru.alex.hotels.repositories.RoomRepository;
import ru.alex.hotels.services.HotelService;
import ru.alex.hotels.services.RoomService;
import ru.alex.hotels.specifications.RoomSpecification;
import ru.alex.hotels.utils.room.RoomCharacteristic;

import java.util.List;

@Service
@RequiredArgsConstructor
@Validated
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private final HotelService hotelService;

    @Override
    public Room addRoom(Long hotelId, @Valid Room room) throws  HotelNotFoundException {

        HotelEntity hotelEntity = hotelService.getHotelEntityById(hotelId);

        RoomEntity roomEntityForSave = RoomMapper.INSTANCE.roomToRoomEntity(room);
        roomEntityForSave.setHotel(hotelEntity);

        return RoomMapper.INSTANCE.roomEntityToRoom(roomRepository.save(roomEntityForSave));
    }


    @Override
    public List<Room> getRoomsByHotelId(Long hotelId) throws HotelNotFoundException {
        hotelService.getHotelEntityById(hotelId);

        List<RoomEntity> roomsEntities = roomRepository.findRoomsByHotelId(hotelId);

        return RoomMapper.INSTANCE.roomEntityListToRoomList(roomsEntities);
    }

    @Override
    public List<Room> getRoomsBySpecification(Long hotelId, Room room) throws HotelNotFoundException {
        RoomCharacteristic roomCharacteristic = RoomMapper.INSTANCE.roomToRoomCharacteristic(room);
        roomCharacteristic.setHotel(hotelService.getHotelEntityById(hotelId));

        return RoomMapper.INSTANCE.roomEntityListToRoomList(roomRepository.findAll(new RoomSpecification(roomCharacteristic)));
    }

}
