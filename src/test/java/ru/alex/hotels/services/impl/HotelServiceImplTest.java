package ru.alex.hotels.services.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import ru.alex.hotels.entitys.CityEntity;
import ru.alex.hotels.entitys.DirectorEntity;
import ru.alex.hotels.entitys.HotelEntity;
import ru.alex.hotels.exceptions.CityNotFound;
import ru.alex.hotels.exceptions.DirectorNotFound;
import ru.alex.hotels.exceptions.HotelAlreadyExists;
import ru.alex.hotels.exceptions.HotelNotFoundException;
import ru.alex.hotels.mappers.HotelMapper;
import ru.alex.hotels.repositories.HotelRepository;
import ru.alex.hotels.tdo.Hotel;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static ru.alex.hotels.dataForTests.HotelDataTest.testHotel;
import static ru.alex.hotels.dataForTests.HotelDataTest.testListHotels;


@ExtendWith(MockitoExtension.class)
class HotelServiceImplTest {
    @Mock
    HotelRepository hotelRepository;
    @Mock
    CityServiceImpl cityService;
    @Mock
    DirectorServiceImpl directorService;

    @InjectMocks
    private HotelServiceImpl hotelService;

    @Test
    void testCreateHotel() throws HotelAlreadyExists, CityNotFound, DirectorNotFound {
        Hotel hotel = testHotel();

        HotelEntity entityAfterSave = HotelMapper.INSTANCE.hotelToHotelEntity(hotel);

        when(hotelRepository.save(any(HotelEntity.class))).thenReturn(entityAfterSave);
        when(cityService.getCityEntityByName("any")).thenReturn(new CityEntity());

        when(directorService.getDirectorEntityById(eq(1L))).thenReturn(new DirectorEntity());

        Hotel createdHotel = hotelService.createHotel(hotel, "any", 1L);

        Assertions.assertEquals(hotel.getName(), createdHotel.getName());
        verify(hotelRepository, times(1)).save(any(HotelEntity.class));
    }

    @Test
    void getTestGetAllHotel() {
        List<Hotel> hotels = testListHotels();
        List<HotelEntity> entitiesAfterGet = HotelMapper.INSTANCE.hotelsToHotelEntities(hotels);

        when(hotelRepository.findAll()).thenReturn(entitiesAfterGet);

        List<Hotel> getHotels = hotelService.getAllHotels();

        Assertions.assertEquals(2, getHotels.size());
        verify(hotelRepository, times(1)).findAll();
    }

    @Test
    void testGetHotelById() throws HotelNotFoundException {
        HotelEntity entitiesAfterFind = HotelMapper.INSTANCE.hotelToHotelEntity(testHotel());

        when(hotelRepository.findById(1L)).thenReturn(Optional.ofNullable(entitiesAfterFind));

        Hotel hotel = hotelService.getHotelById(1L);

        Assertions.assertEquals(testHotel(), hotel);
        verify(hotelRepository, times(1)).findById(1L);
    }

    @Test
    void testUpdateHotel() throws HotelNotFoundException {
        HotelEntity entityForUpdate = HotelMapper.INSTANCE.hotelToHotelEntity(testHotel());

        when(hotelRepository.findById(1L)).thenReturn(Optional.ofNullable(entityForUpdate));
        when(hotelRepository.save(any(HotelEntity.class))).thenReturn(entityForUpdate);

        Hotel hotel = hotelService.updateHotel(testHotel(), 1L);

        Assertions.assertEquals(testHotel().getName(), hotel.getName());
        verify(hotelRepository, times(1)).findById(1L);
    }
}