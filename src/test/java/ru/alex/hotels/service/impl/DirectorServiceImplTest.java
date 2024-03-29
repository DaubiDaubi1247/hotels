package ru.alex.hotels.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.alex.hotels.dto.DirectorDto;
import ru.alex.hotels.entity.Director;
import ru.alex.hotels.exception.EntityAlreadyExistException;
import ru.alex.hotels.mapper.DirectorMapper;
import ru.alex.hotels.repository.DirectorRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static ru.alex.hotels.dataForTests.DirectorDataTest.testDirector;

@ExtendWith(MockitoExtension.class)
class DirectorServiceImplTest {

    @Mock
    private DirectorRepository directorRepository;

    @Spy
    private DirectorMapper directorMapper = Mappers.getMapper(DirectorMapper.class);

    @InjectMocks
    private DirectorServiceImpl directorService;

    @Test
    void testAddDirector() {
        Director directorEntityForSave = directorMapper.toEntity(testDirector());
        directorEntityForSave.setId(1L);

        when(directorRepository.existsByFcsOrPhoneIgnoreCase(eq(testDirector().getFcs()), eq(testDirector().getPhone())))
                .thenReturn(false);

        when(directorRepository.save(any(Director.class)))
                .thenReturn(directorEntityForSave);

        DirectorDto directorDto = directorService.addDirector(testDirector());

        assertNotNull(directorDto.getId());
        assertEquals(testDirector().getFcs(), directorDto.getFcs());
        assertEquals(testDirector().getPhone(), directorDto.getPhone());
    }

    @Test
    void testAddDirectorAlreadyExist() {

        when(directorRepository.existsByFcsOrPhoneIgnoreCase(eq(testDirector().getFcs()), eq(testDirector().getPhone())))
                .thenReturn(true);

        assertThrows(EntityAlreadyExistException.class,
                () -> directorService.addDirector(testDirector()));
    }
}