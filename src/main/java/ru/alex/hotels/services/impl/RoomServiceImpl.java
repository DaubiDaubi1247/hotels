package ru.alex.hotels.services.impl;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import ru.alex.hotels.dto.RoomDto;
import ru.alex.hotels.entity.Hotel;
import ru.alex.hotels.entity.Room;
import ru.alex.hotels.exceptions.EntityNotFoundException;
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
    @Transactional
    public RoomDto addRoom(Long hotelId, @Valid RoomDto roomDto) {

        Hotel hotelEntity = hotelService.getHotelEntityById(hotelId);

        Room roomEntityForSave = RoomMapper.INSTANCE.roomToRoomEntity(roomDto);
        roomEntityForSave.setHotel(hotelEntity);

        return RoomMapper.INSTANCE.roomEntityToRoom(roomRepository.save(roomEntityForSave));
    }

    @Override
    @Transactional
    public List<RoomDto> getRoomsByHotelId(Long hotelId) {
        if (hotelService.isHotelExist(hotelId)) {
            throw new EntityNotFoundException("отель с id = " + hotelId + " не найден");
        }

        List<Room> roomsEntities = roomRepository.findRoomsByHotelId(hotelId);

        return RoomMapper.INSTANCE.roomEntityListToRoomList(roomsEntities);
    }

    @Override
    @Transactional
    public List<RoomDto> getRoomsBySpecification(Long hotelId, @Valid RoomDto roomDto) {
        RoomCharacteristic roomCharacteristic = RoomMapper.INSTANCE.roomToRoomCharacteristic(roomDto);
        roomCharacteristic.setHotel(hotelService.getHotelEntityById(hotelId));

        return RoomMapper.INSTANCE.roomEntityListToRoomList(roomRepository
                .findAll(new RoomSpecification(roomCharacteristic)));
    }

}
