package ru.alex.hotels.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import ru.alex.hotels.dto.HotelDto;
import ru.alex.hotels.entity.City;
import ru.alex.hotels.entity.Director;
import ru.alex.hotels.entity.Hotel;
import ru.alex.hotels.mapper.HotelMapper;
import ru.alex.hotels.repository.HotelRepository;

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

    @Spy
    HotelMapper hotelMapper = Mappers.getMapper(HotelMapper.class);
    @Mock
    DirectorServiceImpl directorService;

    @InjectMocks
    private HotelServiceImpl hotelService;

    @Test
    void testCreateHotel() {
        HotelDto hotelDto = testHotel();

        Hotel entityAfterSave = hotelMapper.toEntity(hotelDto);

        when(hotelRepository.save(any(Hotel.class))).thenReturn(entityAfterSave);
        when(cityService.getCityEntityByName("any")).thenReturn(new City());

        when(directorService.getDirectorEntityById(eq(1L))).thenReturn(new Director());

        HotelDto createdHotelDto = hotelService.createHotel(hotelDto, "any", 1L);

        Assertions.assertEquals(hotelDto.getName(), createdHotelDto.getName());
        verify(hotelRepository, times(1)).save(any(Hotel.class));
    }

    @Test
    void getTestGetAllHotel() {
        List<HotelDto> hotelDtos = testListHotels();
        List<Hotel> entitiesAfterGet = hotelMapper.toEntityList(hotelDtos);

        when(hotelRepository.findAll()).thenReturn(entitiesAfterGet);

        List<HotelDto> getHotelDtoList = hotelService.getAllHotels();

        Assertions.assertEquals(2, getHotelDtoList.size());
        verify(hotelRepository, times(1)).findAll();
    }

    @Test
    void testGetHotelById() {
        Hotel entitiesAfterFind = hotelMapper.toEntity(testHotel());

        when(hotelRepository.findById(1L)).thenReturn(Optional.ofNullable(entitiesAfterFind));

        HotelDto hotelDto = hotelService.getHotelById(1L);

        Assertions.assertEquals(testHotel(), hotelDto);
        verify(hotelRepository, times(1)).findById(1L);
    }

    @Test
    void testUpdateHotel() {
        Hotel entityForUpdate = hotelMapper.toEntity(testHotel());

        when(hotelRepository.findById(1L)).thenReturn(Optional.ofNullable(entityForUpdate));
        when(hotelRepository.save(any(Hotel.class))).thenReturn(entityForUpdate);

        HotelDto hotelDto = hotelService.updateHotel(testHotel(), 1L);

        Assertions.assertEquals(testHotel().getName(), hotelDto.getName());
        verify(hotelRepository, times(1)).findById(1L);
    }
}