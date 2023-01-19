package ru.alex.hotels.services.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import ru.alex.hotels.entitys.HotelEntity;
import ru.alex.hotels.exceptions.HotelAlreadyExists;
import ru.alex.hotels.mappers.HotelMapper;
import ru.alex.hotels.repositories.HotelRepository;
import ru.alex.hotels.tdo.Hotel;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class HotelServiceImplTest {
    @Mock
    HotelRepository hotelRepository;

    @InjectMocks
    private HotelServiceImpl hotelService;

    @Test
    void testCreateHotel() throws HotelAlreadyExists {
        Hotel hotel = Hotel.builder()
                .name("У Саши2")
                .build();

        HotelEntity entityAfterSave = HotelMapper.INSTANCE.HotelToHotelEntity(hotel);


        when(hotelRepository.save(any(HotelEntity.class))).thenReturn(entityAfterSave);

        Hotel createdHotel = hotelService.createHotel(hotel);

        Assertions.assertEquals(hotel.getName(), createdHotel.getName());
        verify(hotelRepository, times(1)).save(any(HotelEntity.class));
    }

    @Test
    void getAllHotels() {
    }
}