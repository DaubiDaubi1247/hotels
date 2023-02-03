package ru.alex.hotels.services.impl;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import ru.alex.hotels.dto.RoomDto;
import ru.alex.hotels.entity.Hotel;
import ru.alex.hotels.entity.Room;
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
    public RoomDto addRoom(Long hotelId, @Valid RoomDto roomDto) throws  HotelNotFoundException {

        Hotel hotelEntity = hotelService.getHotelEntityById(hotelId);

        Room roomEntityForSave = RoomMapper.INSTANCE.roomToRoomEntity(roomDto);
        roomEntityForSave.setHotel(hotelEntity);

        return RoomMapper.INSTANCE.roomEntityToRoom(roomRepository.save(roomEntityForSave));
    }


    @Override
    public List<RoomDto> getRoomsByHotelId(Long hotelId) throws HotelNotFoundException {
        hotelService.getHotelEntityById(hotelId);

        List<Room> roomsEntities = roomRepository.findRoomsByHotelId(hotelId);

        return RoomMapper.INSTANCE.roomEntityListToRoomList(roomsEntities);
    }

    @Override
    public List<RoomDto> getRoomsBySpecification(Long hotelId, RoomDto roomDto) throws HotelNotFoundException {
        RoomCharacteristic roomCharacteristic = RoomMapper.INSTANCE.roomToRoomCharacteristic(roomDto);
        roomCharacteristic.setHotel(hotelService.getHotelEntityById(hotelId));

        return RoomMapper.INSTANCE.roomEntityListToRoomList(roomRepository.findAll(new RoomSpecification(roomCharacteristic)));
    }

}
