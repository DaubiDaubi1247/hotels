package ru.alex.hotels.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.alex.hotels.entitys.RoomEntity;
import ru.alex.hotels.exceptions.RoomAlreadyExists;
import ru.alex.hotels.mappers.RoomMapper;
import ru.alex.hotels.repositories.RoomRepository;
import ru.alex.hotels.services.RoomService;
import ru.alex.hotels.tdo.Room;

import java.util.Optional;

@Service
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    @Autowired
    public RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public Room addRoom(Long hotelId, Room room) throws RoomAlreadyExists {
        Optional<RoomEntity> roomEntity = roomRepository.findByRoomNumber(hotelId, room.getRoomNumber());

        if (roomEntity.isPresent())
            throw new RoomAlreadyExists("комната с номером = " + room.getRoomNumber() + " в отеле с id = " + hotelId + " уже существует");

        RoomEntity roomEntityForSave = RoomMapper.INSTANCE.roomToRoomEntity(room);

        return RoomMapper.INSTANCE.roomEntityToRoom(roomRepository.save(roomEntityForSave));
    }
}
