package ru.alex.hotels.services.getOrThrow;

import lombok.Getter;
import org.springframework.stereotype.Component;
import ru.alex.hotels.entitys.HotelEntity;
import ru.alex.hotels.exceptions.HotelNotFoundException;
import ru.alex.hotels.repositories.HotelRepository;

@Component
@Getter
public class HotelRepositoryWrapper {

    private final HotelRepository hotelRepository;

    public HotelRepositoryWrapper(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    public HotelEntity getHotelEntityOrThrow(Long hotelId) throws HotelNotFoundException {
        return hotelRepository.findById(hotelId)
                .orElseThrow(() -> new HotelNotFoundException(hotelId));
    }
}
