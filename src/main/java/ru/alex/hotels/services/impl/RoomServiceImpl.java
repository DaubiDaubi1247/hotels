package ru.alex.hotels.services.impl;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import ru.alex.hotels.entitys.HotelEntity;
import ru.alex.hotels.entitys.RoomEntity;
import ru.alex.hotels.exceptions.HotelNotFoundException;
import ru.alex.hotels.exceptions.RoomAlreadyExists;
import ru.alex.hotels.mappers.RoomMapper;
import ru.alex.hotels.repositories.RoomRepository;
import ru.alex.hotels.services.HotelService;
import ru.alex.hotels.services.RoomService;
import ru.alex.hotels.dto.Room;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Validated
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private final HotelService hotelService;

    @Override
    public Room addRoom(Long hotelId, @Valid Room room) throws RoomAlreadyExists, HotelNotFoundException {

        HotelEntity hotelEntity = hotelService.getHotelEntityById(hotelId);

        Optional<RoomEntity> roomEntity = roomRepository.findByRoomNumber(hotelId, room.getRoomNumber());

        if (roomEntity.isPresent())
            throw new RoomAlreadyExists("комната с номером = " + room.getRoomNumber() + " в отеле с id = " + hotelId + " уже существует");

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
}
