package ru.alex.hotels.services.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.alex.hotels.dataForTests.RoomDataTest;
import ru.alex.hotels.dto.RoomDto;
import ru.alex.hotels.entitys.Hotel;
import ru.alex.hotels.entitys.Room;
import ru.alex.hotels.exceptions.HotelNotFoundException;
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
    public void testCreateRoom() throws HotelNotFoundException {

        RoomDto resRoomDto = RoomMapper.INSTANCE.roomEntityToRoom(RoomDataTest.testRoomEntity());

        when(roomRepository.save(any(Room.class))).thenReturn(RoomDataTest.testRoomEntity());
        when(hotelService.getHotelEntityById(eq(1L))).thenReturn(new Hotel());

        RoomDto roomDto = roomService.addRoom(1L, resRoomDto);

        assertEquals(resRoomDto, roomDto);
        verify(roomRepository, times(1)).save(any(Room.class));
    }

    @Test
    public void testCreateRoomNotFoundHotel() throws HotelNotFoundException {

        RoomDto resRoomDto = RoomMapper.INSTANCE.roomEntityToRoom(RoomDataTest.testRoomEntity());

        when(hotelService.getHotelEntityById(eq(1L))).thenThrow(new HotelNotFoundException(1L));

        assertThrows(HotelNotFoundException.class,
                () -> roomService.addRoom(1L, resRoomDto));

    }

    @Test
    void getRoomsBySpecification() throws HotelNotFoundException {
        when(roomRepository.findAll(any(RoomSpecification.class))).thenReturn(new ArrayList<>());

        List<RoomDto> roomDtos = roomService.getRoomsBySpecification(1L, testRoom());

        assertArrayEquals(new RoomDto[]{}, roomDtos.toArray());
    }
}