package ru.alex.hotels.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.alex.hotels.entitys.HotelEntity;
import ru.alex.hotels.exceptions.HotelAlreadyExists;
import ru.alex.hotels.mappers.HotelMapper;
import ru.alex.hotels.repositories.HotelRepository;
import ru.alex.hotels.services.HotelService;
import ru.alex.hotels.tdo.Hotel;

import java.util.Optional;

@Service
public class HotelServiceImpl implements HotelService {
    private final HotelRepository hotelRepository;

    @Autowired
    public HotelServiceImpl(final HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    @Override
    public Hotel createHotel(Hotel hotel) throws HotelAlreadyExists {
        Optional<HotelEntity> hotelEntity = hotelRepository.findByName(hotel.getName());

        if (hotelEntity.isPresent())
            throw new HotelAlreadyExists("Отель с именем = " + hotel.getName() + " уже сущствует");

        HotelEntity hotelToEntity = HotelMapper.INSTANCE.HotelToHotelEntity(hotel);

        return HotelMapper.INSTANCE.HotelEntityToHotel(hotelRepository.save(hotelToEntity));
    }
}