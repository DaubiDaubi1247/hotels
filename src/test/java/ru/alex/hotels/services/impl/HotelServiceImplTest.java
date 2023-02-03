package ru.alex.hotels.services.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import ru.alex.hotels.entitys.City;
import ru.alex.hotels.entitys.DirectorEntity;
import ru.alex.hotels.entitys.Hotel;
import ru.alex.hotels.exceptions.CityNotFound;
import ru.alex.hotels.exceptions.DirectorNotFound;
import ru.alex.hotels.exceptions.HotelAlreadyExists;
import ru.alex.hotels.exceptions.HotelNotFoundException;
import ru.alex.hotels.mappers.HotelMapper;
import ru.alex.hotels.repositories.HotelRepository;
import ru.alex.hotels.dto.HotelDto;

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
        HotelDto hotelDto = testHotel();

        Hotel entityAfterSave = HotelMapper.INSTANCE.hotelToHotelEntity(hotelDto);

        when(hotelRepository.save(any(Hotel.class))).thenReturn(entityAfterSave);
        when(cityService.getCityEntityByName("any")).thenReturn(new City());

        when(directorService.getDirectorEntityById(eq(1L))).thenReturn(new DirectorEntity());

        HotelDto createdHotelDto = hotelService.createHotel(hotelDto, "any", 1L);

        Assertions.assertEquals(hotelDto.getName(), createdHotelDto.getName());
        verify(hotelRepository, times(1)).save(any(Hotel.class));
    }

    @Test
    void getTestGetAllHotel() {
        List<HotelDto> hotelDtos = testListHotels();
        List<Hotel> entitiesAfterGet = HotelMapper.INSTANCE.hotelsToHotelEntities(hotelDtos);

        when(hotelRepository.findAll()).thenReturn(entitiesAfterGet);

        List<HotelDto> getHotelDtos = hotelService.getAllHotels();

        Assertions.assertEquals(2, getHotelDtos.size());
        verify(hotelRepository, times(1)).findAll();
    }

    @Test
    void testGetHotelById() throws HotelNotFoundException {
        Hotel entitiesAfterFind = HotelMapper.INSTANCE.hotelToHotelEntity(testHotel());

        when(hotelRepository.findById(1L)).thenReturn(Optional.ofNullable(entitiesAfterFind));

        HotelDto hotelDto = hotelService.getHotelById(1L);

        Assertions.assertEquals(testHotel(), hotelDto);
        verify(hotelRepository, times(1)).findById(1L);
    }

    @Test
    void testUpdateHotel() throws HotelNotFoundException {
        Hotel entityForUpdate = HotelMapper.INSTANCE.hotelToHotelEntity(testHotel());

        when(hotelRepository.findById(1L)).thenReturn(Optional.ofNullable(entityForUpdate));
        when(hotelRepository.save(any(Hotel.class))).thenReturn(entityForUpdate);

        HotelDto hotelDto = hotelService.updateHotel(testHotel(), 1L);

        Assertions.assertEquals(testHotel().getName(), hotelDto.getName());
        verify(hotelRepository, times(1)).findById(1L);
    }
}