package ru.alex.hotels.services.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.alex.hotels.entitys.HotelEntity;
import ru.alex.hotels.entitys.RoomEntity;
import ru.alex.hotels.exceptions.HotelNotFoundException;
import ru.alex.hotels.exceptions.RoomAlreadyExists;
import ru.alex.hotels.mappers.RoomMapper;
import ru.alex.hotels.repositories.HotelRepository;
import ru.alex.hotels.repositories.RoomRepository;
import ru.alex.hotels.tdo.Room;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static ru.alex.hotels.dataForTests.RoomDataTest.testRoom;

@ExtendWith(MockitoExtension.class)
class RoomServiceImplTest {
    @Mock
    RoomRepository roomRepository;
    @Mock
    HotelRepository hotelRepository;

    @InjectMocks
    RoomServiceImpl roomService;

    @Test
    public void testCreateRoom() throws RoomAlreadyExists, HotelNotFoundException {

        Room resRoom = RoomMapper.INSTANCE.roomEntityToRoom(testRoom());

        when(roomRepository.save(any(RoomEntity.class))).thenReturn(testRoom());
        when(hotelRepository.findById(eq(1L))).thenReturn(Optional.of(new HotelEntity()));

        Room room = roomService.addRoom(1L, resRoom);

        assertEquals(resRoom, room);
        verify(roomRepository, times(1)).save(any(RoomEntity.class));
    }

    @Test
    public void testCreateRoomNotFoundHotel() {

        Room resRoom = RoomMapper.INSTANCE.roomEntityToRoom(testRoom());

        when(hotelRepository.findById(eq(1L))).thenReturn(Optional.empty());

        Throwable thrown = assertThrows(HotelNotFoundException.class,
                () -> roomService.addRoom(1L, resRoom));

        Assertions.assertNotNull(thrown.getMessage());
    }

}