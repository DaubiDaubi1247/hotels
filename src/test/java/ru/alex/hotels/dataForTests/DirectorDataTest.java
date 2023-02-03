package ru.alex.hotels.dataForTests;

import ru.alex.hotels.dto.DirectorDto;

public class DirectorDataTest {
    public static DirectorDto testDirector() {
        return DirectorDto.builder()
                .fcs("Скляренко Александр")
                .phone("89103203155")
                .build();
    }

    public static DirectorDto testDirectorAfterCreate() {
        DirectorDto directorDto = testDirector();
        directorDto.setId(1L);

        return directorDto;
    }

    public static DirectorDto testDirectorInvalidPhone() {
        return DirectorDto.builder()
                .fcs("Скляренко Александр")
                .phone("+7811111111111")
                .build();
    }
}
