package ru.alex.hotels.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.alex.hotels.entitys.HotelEntity;
import ru.alex.hotels.entitys.RoomEntity;
import ru.alex.hotels.exceptions.HotelNotFoundException;
import ru.alex.hotels.exceptions.RoomAlreadyExists;
import ru.alex.hotels.mappers.RoomMapper;
import ru.alex.hotels.repositories.RoomRepository;
import ru.alex.hotels.services.RoomService;
import ru.alex.hotels.services.repositoryWrappers.HotelRepositoryWrapper;
import ru.alex.hotels.tdo.Room;

import java.util.List;
import java.util.Optional;

@Service
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private final HotelRepositoryWrapper hotelRepositoryWrapper;

    @Autowired
    public RoomServiceImpl(RoomRepository roomRepository, HotelRepositoryWrapper hotelRepositoryWrapper) {
        this.roomRepository = roomRepository;
        this.hotelRepositoryWrapper = hotelRepositoryWrapper;
    }

    @Override
    public Room addRoom(Long hotelId, Room room) throws RoomAlreadyExists, HotelNotFoundException {

        HotelEntity hotelEntity = hotelRepositoryWrapper.getHotelEntityOrThrow(hotelId);

        Optional<RoomEntity> roomEntity = roomRepository.findByRoomNumber(hotelId, room.getRoomNumber());

        if (roomEntity.isPresent())
            throw new RoomAlreadyExists("комната с номером = " + room.getRoomNumber() + " в отеле с id = " + hotelId + " уже существует");

        RoomEntity roomEntityForSave = RoomMapper.INSTANCE.roomToRoomEntity(room);
        roomEntityForSave.setHotel(hotelEntity);

        return RoomMapper.INSTANCE.roomEntityToRoom(roomRepository.save(roomEntityForSave));
    }


    @Override
    public List<Room> getRoomsByHotelId(Long hotelId) throws HotelNotFoundException {
        hotelRepositoryWrapper.getHotelEntityOrThrow(hotelId);

        List<RoomEntity> roomsEntities = roomRepository.findRoomsByHotelId(hotelId);

        return RoomMapper.INSTANCE.roomEntityListToRoomList(roomsEntities);
    }
}
