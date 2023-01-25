package ru.alex.hotels.services.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.alex.hotels.entitys.DirectorEntity;
import ru.alex.hotels.exceptions.DirectorAlreadyExist;
import ru.alex.hotels.exceptions.InvalidPhone;
import ru.alex.hotels.mappers.DirectorMapper;
import ru.alex.hotels.repositories.DirectorRepository;
import ru.alex.hotels.tdo.Director;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static ru.alex.hotels.dataForTests.DirectorDataTest.testDirector;
import static ru.alex.hotels.dataForTests.DirectorDataTest.testDirectorInvalidPhone;

@ExtendWith(MockitoExtension.class)
class DirectorServiceImplTest {

    @Mock
    private DirectorRepository directorRepository;

    @InjectMocks
    private DirectorServiceImpl directorService;

    @Test
    void testAddDirector() throws DirectorAlreadyExist, InvalidPhone {
        DirectorEntity directorEntityForSave = DirectorMapper.INSTANSE.directorToDirectorEntity(testDirector());
        directorEntityForSave.setId(1L);

        when(directorRepository.findByFcsAndPhoneIgnoreCase(eq(testDirector().getFcs()), eq(testDirector().getPhone())))
                .thenReturn(Optional.empty());

        when(directorRepository.save(any(DirectorEntity.class)))
                .thenReturn(directorEntityForSave);

        Director director = directorService.addDirector(testDirector());

        assertNotNull(director.getId());
        assertEquals(testDirector().getFcs(), director.getFcs());
        assertEquals(testDirector().getPhone(), director.getPhone());
    }

    @Test
    void testAddDirectorInvalidPhone() {

        Throwable thrown = assertThrows(InvalidPhone.class,
                () -> directorService.addDirector(testDirectorInvalidPhone()));

        assertNotNull(thrown.getMessage());
    }

    @Test
    void testAddDirectorAlreadyExist() {

        when(directorRepository.findByFcsAndPhoneIgnoreCase(eq(testDirector().getFcs()), eq(testDirector().getPhone())))
                .thenReturn(Optional.of(new DirectorEntity()));

        Throwable thrown = assertThrows(DirectorAlreadyExist.class,
                () -> directorService.addDirector(testDirector()));

        assertNotNull(thrown.getMessage());
    }
}