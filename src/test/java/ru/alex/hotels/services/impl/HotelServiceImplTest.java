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

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static ru.alex.hotels.dataForTests.HotelDataTest.testHotel;
import static ru.alex.hotels.dataForTests.HotelDataTest.testListHotels;


@ExtendWith(MockitoExtension.class)
class HotelServiceImplTest {
    @Mock
    HotelRepository hotelRepository;

    @InjectMocks
    private HotelServiceImpl hotelService;

    @Test
    void testCreateHotel() throws HotelAlreadyExists {
        Hotel hotel = testHotel();

        HotelEntity entityAfterSave = HotelMapper.INSTANCE.HotelToHotelEntity(hotel);

        when(hotelRepository.save(any(HotelEntity.class))).thenReturn(entityAfterSave);

        Hotel createdHotel = hotelService.createHotel(hotel);

        Assertions.assertEquals(hotel.getName(), createdHotel.getName());
        verify(hotelRepository, times(1)).save(any(HotelEntity.class));
    }

    @Test
    void getAllHotels() {
        List<Hotel> hotels = testListHotels();
        List<HotelEntity> entitiesAfterGet = HotelMapper.INSTANCE.hotelsToHotelEntities(hotels);

        when(hotelRepository.findAll()).thenReturn(entitiesAfterGet);

        List<Hotel> getHotels = hotelService.getAllHotels();

        Assertions.assertEquals(2, getHotels.size());
        verify(hotelRepository, times(1)).findAll();
    }
}