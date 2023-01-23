package ru.alex.hotels.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.alex.hotels.entitys.HotelEntity;
import ru.alex.hotels.entitys.RoomEntity;
import ru.alex.hotels.exceptions.HotelNotFoundException;
import ru.alex.hotels.exceptions.RoomAlreadyExists;
import ru.alex.hotels.mappers.RoomMapper;
import ru.alex.hotels.repositories.HotelRepository;
import ru.alex.hotels.repositories.RoomRepository;
import ru.alex.hotels.services.RoomService;
import ru.alex.hotels.tdo.Room;

import java.util.List;
import java.util.Optional;

@Service
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private final HotelRepository hotelRepository;

    @Autowired
    public RoomServiceImpl(RoomRepository roomRepository, HotelRepository hotelRepository) {
        this.roomRepository = roomRepository;
        this.hotelRepository = hotelRepository;
    }

    @Override
    public Room addRoom(Long hotelId, Room room) throws RoomAlreadyExists, HotelNotFoundException {

        HotelEntity hotelEntity = getHotelEntityOrThrow(hotelId);

        Optional<RoomEntity> roomEntity = roomRepository.findByRoomNumber(hotelId, room.getRoomNumber());

        if (roomEntity.isPresent())
            throw new RoomAlreadyExists("комната с номером = " + room.getRoomNumber() + " в отеле с id = " + hotelId + " уже существует");

        RoomEntity roomEntityForSave = RoomMapper.INSTANCE.roomToRoomEntity(room);
        roomEntityForSave.setHotel(hotelEntity);

        return RoomMapper.INSTANCE.roomEntityToRoom(roomRepository.save(roomEntityForSave));
    }

    private HotelEntity getHotelEntityOrThrow(Long hotelId) throws HotelNotFoundException {
        return hotelRepository.findById(hotelId)
                .orElseThrow(() -> new HotelNotFoundException(hotelId));
    }

    @Override
    public List<Room> getRoomsByHotel(Long hotelId) throws HotelNotFoundException {
        HotelEntity hotelEntity = getHotelEntityOrThrow(hotelId);

        List<RoomEntity> roomsEntities = roomRepository.findRoomsByHotelId(hotelId);

        return RoomMapper.INSTANCE.roomEntityListToRoomList(roomsEntities);
    }
}
