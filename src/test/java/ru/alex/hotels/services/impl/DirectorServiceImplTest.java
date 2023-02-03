package ru.alex.hotels.services.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.alex.hotels.entitys.Director;
import ru.alex.hotels.exceptions.DirectorAlreadyExist;
import ru.alex.hotels.mappers.DirectorMapper;
import ru.alex.hotels.repositories.DirectorRepository;
import ru.alex.hotels.dto.DirectorDto;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static ru.alex.hotels.dataForTests.DirectorDataTest.testDirector;

@ExtendWith(MockitoExtension.class)
class DirectorServiceImplTest {

    @Mock
    private DirectorRepository directorRepository;

    @InjectMocks
    private DirectorServiceImpl directorService;

    @Test
    void testAddDirector() throws DirectorAlreadyExist {
        Director directorEntityForSave = DirectorMapper.INSTANSE.directorToDirectorEntity(testDirector());
        directorEntityForSave.setId(1L);

        when(directorRepository.findByFcsOrPhoneIgnoreCase(eq(testDirector().getFcs()), eq(testDirector().getPhone())))
                .thenReturn(Optional.empty());

        when(directorRepository.save(any(Director.class)))
                .thenReturn(directorEntityForSave);

        DirectorDto directorDto = directorService.addDirector(testDirector());

        assertNotNull(directorDto.getId());
        assertEquals(testDirector().getFcs(), directorDto.getFcs());
        assertEquals(testDirector().getPhone(), directorDto.getPhone());
    }

    @Test
    void testAddDirectorAlreadyExist() {

        when(directorRepository.findByFcsOrPhoneIgnoreCase(eq(testDirector().getFcs()), eq(testDirector().getPhone())))
                .thenReturn(Optional.of(new Director()));

        Throwable thrown = assertThrows(DirectorAlreadyExist.class,
                () -> directorService.addDirector(testDirector()));

        assertNotNull(thrown.getMessage());
    }
}