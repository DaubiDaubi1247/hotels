package ru.alex.hotels.services.impl;

import lombok.AllArgsConstructor;
import ru.alex.hotels.entitys.HotelEntity;
import ru.alex.hotels.exceptions.HotelNotFoundException;
import ru.alex.hotels.repositories.DirectorRepository;
import ru.alex.hotels.services.DirectorService;
import ru.alex.hotels.services.getOrThrow.HotelRepositoryWrapper;
import ru.alex.hotels.tdo.Director;

@AllArgsConstructor
public class DirectorServiceImpl implements DirectorService {
    private final DirectorRepository directorRepository;
    private final HotelRepositoryWrapper hotelRepositoryWrapper;
    @Override
    public Director addDirector(Long hotelId, Director director) throws HotelNotFoundException {
        HotelEntity hotelEntity = hotelRepositoryWrapper.getHotelEntityOrThrow(hotelId);

        return null;
    }
}
