package ru.alex.hotels.service.impl;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import ru.alex.hotels.dto.RoomDto;
import ru.alex.hotels.entity.Hotel;
import ru.alex.hotels.entity.Room;
import ru.alex.hotels.exception.EntityNotFoundException;
import ru.alex.hotels.mapper.RoomMapper;
import ru.alex.hotels.repository.RoomRepository;
import ru.alex.hotels.service.HotelService;
import ru.alex.hotels.service.RoomService;
import ru.alex.hotels.utils.room.RoomCharacteristic;
import ru.alex.hotels.utils.room.RoomSpecifications;

import java.util.List;

@Service
@RequiredArgsConstructor
@Validated
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private final RoomMapper roomMapper;
    private final HotelService hotelService;

    @Override
    @Transactional
    public RoomDto addRoom(Long hotelId, @Valid RoomDto roomDto) {

        Hotel hotelEntity = hotelService.getHotelEntityById(hotelId);

        Room roomEntityForSave = roomMapper.toEntity(roomDto);
        roomEntityForSave.setHotel(hotelEntity);

        return roomMapper.toDto(roomRepository.save(roomEntityForSave));
    }

    @Override
    @Transactional
    public List<RoomDto> getRoomsByHotelId(Long hotelId) {
        if (hotelService.isHotelExist(hotelId)) {
            throw new EntityNotFoundException("отель с id = " + hotelId + " не найден");
        }

        List<Room> roomsEntities = roomRepository.findRoomsByHotelId(hotelId);

        return roomMapper.toDtoList(roomsEntities);
    }

    @Override
    @Transactional
    public List<RoomDto> getRoomsBySpecification(Long hotelId, @Valid RoomDto roomDto) {
        RoomCharacteristic roomCharacteristic = roomMapper.toRoomCharacteristic(roomDto);
        roomCharacteristic.setHotel(hotelService.getHotelEntityById(hotelId));

        return roomMapper.toDtoList(roomRepository
                .findAll(RoomSpecifications.getRoomsByFilters(roomCharacteristic)));
    }

}
