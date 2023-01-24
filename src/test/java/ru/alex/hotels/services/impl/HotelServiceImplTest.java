package ru.alex.hotels.services.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import ru.alex.hotels.entitys.CityEntity;
import ru.alex.hotels.entitys.HotelEntity;
import ru.alex.hotels.exceptions.CityNotFound;
import ru.alex.hotels.exceptions.HotelAlreadyExists;
import ru.alex.hotels.exceptions.HotelNotFoundException;
import ru.alex.hotels.mappers.HotelMapper;
import ru.alex.hotels.repositories.CityRepository;
import ru.alex.hotels.repositories.HotelRepository;
import ru.alex.hotels.services.getOrThrow.HotelRepositoryWrapper;
import ru.alex.hotels.tdo.Hotel;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static ru.alex.hotels.dataForTests.HotelDataTest.testHotel;
import static ru.alex.hotels.dataForTests.HotelDataTest.testListHotels;


@ExtendWith(MockitoExtension.class)
class HotelServiceImplTest {
    @Mock
    HotelRepository hotelRepository;

    @Mock
    HotelRepositoryWrapper hotelRepositoryWrapper;
    @Mock
    CityRepository cityRepository;

    @InjectMocks
    private HotelServiceImpl hotelService;

    @Test
    void testCreateHotel() throws HotelAlreadyExists, CityNotFound {
        Hotel hotel = testHotel();

        HotelEntity entityAfterSave = HotelMapper.INSTANCE.hotelToHotelEntity(hotel);

        when(hotelRepositoryWrapper.getHotelRepository()).thenReturn(hotelRepository);
        when(hotelRepositoryWrapper.getHotelRepository().save(any(HotelEntity.class))).thenReturn(entityAfterSave);
        when(cityRepository.findByNameIgnoreCase("any")).thenReturn(Optional.of(new CityEntity()));

        Hotel createdHotel = hotelService.createHotel(hotel, "any");

        Assertions.assertEquals(hotel.getName(), createdHotel.getName());
        verify(hotelRepository, times(1)).save(any(HotelEntity.class));
    }

    @Test
    void testCreateHotelShouldNotFoundCity(){
        Hotel hotel = testHotel();

        when(cityRepository.findByNameIgnoreCase("any")).thenReturn(Optional.empty());

        Throwable thrown = assertThrows(CityNotFound.class,
                () -> hotelService.createHotel(hotel, "any"));

        Assertions.assertNotNull(thrown.getMessage());
    }

    @Test
    void getTestGetAllHotel() {
        List<Hotel> hotels = testListHotels();
        List<HotelEntity> entitiesAfterGet = HotelMapper.INSTANCE.hotelsToHotelEntities(hotels);

        when(hotelRepositoryWrapper.getHotelRepository()).thenReturn(hotelRepository);
        when(hotelRepositoryWrapper.getHotelRepository().findAll()).thenReturn(entitiesAfterGet);

        List<Hotel> getHotels = hotelService.getAllHotels();

        Assertions.assertEquals(2, getHotels.size());
        verify(hotelRepository, times(1)).findAll();
    }

    @Test
    void testGetHotelById() throws HotelNotFoundException {
        HotelEntity entitiesAfterFind = HotelMapper.INSTANCE.hotelToHotelEntity(testHotel());

        when(hotelRepositoryWrapper.getHotelEntityOrThrow(1L)).thenReturn(entitiesAfterFind);

        Hotel hotel = hotelService.getHotelById(1L);

        Assertions.assertEquals(testHotel(), hotel);
        verify(hotelRepositoryWrapper, times(1)).getHotelEntityOrThrow(1L);
    }

    @Test
    void testUpdateHotel() throws HotelNotFoundException {
        HotelEntity entityForUpdate = HotelMapper.INSTANCE.hotelToHotelEntity(testHotel());

        when(hotelRepositoryWrapper.getHotelEntityOrThrow(1L)).thenReturn(entityForUpdate);
        when(hotelRepositoryWrapper.getHotelRepository()).thenReturn(hotelRepository);
        when(hotelRepositoryWrapper.getHotelRepository().save(any(HotelEntity.class))).thenReturn(entityForUpdate);

        Hotel hotel = hotelService.updateHotel(testHotel(), 1L);

        Assertions.assertEquals(testHotel().getName(), hotel.getName());
        verify(hotelRepositoryWrapper, times(1)).getHotelEntityOrThrow(1L);
    }
}