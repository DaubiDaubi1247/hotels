package ru.alex.hotels.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import ru.alex.hotels.dataForTests.RoomDataTest;
import ru.alex.hotels.dto.RoomDto;
import ru.alex.hotels.entity.Hotel;
import ru.alex.hotels.entity.Room;
import ru.alex.hotels.exception.EntityNotFoundException;
import ru.alex.hotels.mapper.RoomMapper;
import ru.alex.hotels.repository.RoomRepository;
import ru.alex.hotels.specification.RoomSpecification;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static ru.alex.hotels.dataForTests.RoomDataTest.testRoom;

@ExtendWith(MockitoExtension.class)
class RoomServiceImplTest {
    @Mock
    RoomRepository roomRepository;
    @Mock
    HotelServiceImpl hotelService;

    @Spy
    RoomMapper roomMapper = Mappers.getMapper(RoomMapper.class);

    @InjectMocks
    RoomServiceImpl roomService;

    @Test
    public void testCreateRoom() {

        RoomDto resRoomDto = roomMapper.toDto(RoomDataTest.testRoomEntity());

        when(roomRepository.save(any(Room.class))).thenReturn(RoomDataTest.testRoomEntity());
        when(hotelService.getHotelEntityById(eq(1L))).thenReturn(new Hotel());

        RoomDto roomDto = roomService.addRoom(1L, resRoomDto);

        assertEquals(resRoomDto, roomDto);
        verify(roomRepository, times(1)).save(any(Room.class));
    }

    @Test
    public void testCreateRoomNotFoundHotel() {

        RoomDto resRoomDto = roomMapper.toDto(RoomDataTest.testRoomEntity());

        when(hotelService.getHotelEntityById(eq(1L))).thenThrow(new EntityNotFoundException(""));

        assertThrows(EntityNotFoundException.class,
                () -> roomService.addRoom(1L, resRoomDto));

    }

    @Test
    void getRoomsBySpecification() {
        when(roomRepository.findAll(any(RoomSpecification.class))).thenReturn(new ArrayList<>());

        List<RoomDto> roomDtos = roomService.getRoomsBySpecification(1L, testRoom());

        assertArrayEquals(new RoomDto[]{}, roomDtos.toArray());
    }
}