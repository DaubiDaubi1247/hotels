package ru.alex.hotels.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.alex.hotels.entitys.CityEntity;
import ru.alex.hotels.entitys.HotelEntity;
import ru.alex.hotels.exceptions.CityNotFound;
import ru.alex.hotels.exceptions.HotelAlreadyExists;
import ru.alex.hotels.exceptions.HotelNotFoundException;
import ru.alex.hotels.mappers.HotelMapper;
import ru.alex.hotels.repositories.CityRepository;
import ru.alex.hotels.repositories.HotelRepository;
import ru.alex.hotels.services.HotelService;
import ru.alex.hotels.tdo.Hotel;

import java.util.List;
import java.util.Optional;

@Service
public class HotelServiceImpl implements HotelService {
    private final HotelRepository hotelRepository;
    private final CityRepository cityRepository;

    @Autowired
    public HotelServiceImpl(final HotelRepository hotelRepository, CityRepository cityRepository) {
        this.hotelRepository = hotelRepository;
        this.cityRepository = cityRepository;
    }

    @Override
    public Hotel createHotel(Hotel hotel) throws HotelAlreadyExists {
        Optional<HotelEntity> hotelEntity = hotelRepository.findByName(hotel.getName());

        if (hotelEntity.isPresent())
            throw new HotelAlreadyExists("Отель с именем = " + hotel.getName() + " уже сущствует");

        HotelEntity hotelToEntity = HotelMapper.INSTANCE.hotelToHotelEntity(hotel);

        return HotelMapper.INSTANCE.hotelEntityToHotel(hotelRepository.save(hotelToEntity));
    }

    @Override
    public List<Hotel> getAllHotels() {
        return HotelMapper.INSTANCE.hotelEntityListToHotelList(hotelRepository.findAll());
    }

    @Override
    public Hotel getHotelById(Long id) throws HotelNotFoundException {
        Optional<HotelEntity> hotelEntity = hotelRepository.findById(id);

        if (hotelEntity.isEmpty()) {
            throw new HotelNotFoundException("отель с id = " + id + " не найден");
        }

        return HotelMapper.INSTANCE.hotelEntityToHotel(hotelEntity.get());
    }

    @Override
    public Hotel updateHotel(Hotel hotel, Long id) throws HotelNotFoundException {
        HotelEntity hotelEntity = hotelRepository.findById(id)
                .orElseThrow(() -> new HotelNotFoundException("отель с id = " + id + " не найден"));

        hotelEntity.setName(hotel.getName());

        return HotelMapper.INSTANCE.hotelEntityToHotel(hotelRepository.save(hotelEntity));
    }

    @Override
    public List<Hotel> getAllHotelsInCity(String cityName) throws CityNotFound {
        CityEntity desiredCity = cityRepository.findByNameIgnoreCase(cityName)
                .orElseThrow(() -> new CityNotFound("город с названием " + cityName + " не найден"));


    }
}
