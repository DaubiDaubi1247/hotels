package ru.alex.hotels.services.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.alex.hotels.dataForTests.RoomDataTest;
import ru.alex.hotels.dto.Room;
import ru.alex.hotels.entitys.HotelEntity;
import ru.alex.hotels.entitys.RoomEntity;
import ru.alex.hotels.exceptions.HotelNotFoundException;
import ru.alex.hotels.exceptions.RoomAlreadyExists;
import ru.alex.hotels.mappers.RoomMapper;
import ru.alex.hotels.repositories.RoomRepository;
import ru.alex.hotels.specifications.RoomSpecification;

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

    @InjectMocks
    RoomServiceImpl roomService;

    @Test
    public void testCreateRoom() throws RoomAlreadyExists, HotelNotFoundException {

        Room resRoom = RoomMapper.INSTANCE.roomEntityToRoom(RoomDataTest.testRoomEntity());

        when(roomRepository.save(any(RoomEntity.class))).thenReturn(RoomDataTest.testRoomEntity());
        when(hotelService.getHotelEntityById(eq(1L))).thenReturn(new HotelEntity());

        Room room = roomService.addRoom(1L, resRoom);

        assertEquals(resRoom, room);
        verify(roomRepository, times(1)).save(any(RoomEntity.class));
    }

    @Test
    public void testCreateRoomNotFoundHotel() throws HotelNotFoundException {

        Room resRoom = RoomMapper.INSTANCE.roomEntityToRoom(RoomDataTest.testRoomEntity());

        when(hotelService.getHotelEntityById(eq(1L))).thenThrow(new HotelNotFoundException(1L));

        assertThrows(HotelNotFoundException.class,
                () -> roomService.addRoom(1L, resRoom));

    }

    @Test
    void getRoomsBySpecification() throws HotelNotFoundException {
        when(roomRepository.findAll(any(RoomSpecification.class))).thenReturn(new ArrayList<>());

        List<Room> rooms = roomService.getRoomsBySpecification(1L, testRoom());

        assertArrayEquals(new Room[]{}, rooms.toArray());
    }
}