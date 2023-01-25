package ru.alex.hotels.dataForTests;

import ru.alex.hotels.tdo.Director;

public class DirectorDataTest {
    public static Director testDirector() {
        return Director.builder()
                .fcs("Скляренко Александр")
                .phone("89103203155")
                .build();
    }

    public static Director testDirectorInvalidPhone() {
        return Director.builder()
                .fcs("Скляренко Александр")
                .phone("+7811111111111")
                .build();
    }
}
