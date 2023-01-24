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
import ru.alex.hotels.services.HotelService;
import ru.alex.hotels.services.getOrThrow.HotelRepositoryWrapper;
import ru.alex.hotels.tdo.Hotel;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HotelServiceImpl implements HotelService {
    private final HotelRepositoryWrapper hotelRepositoryWrapper;
    private final CityRepository cityRepository;

    @Autowired
    public HotelServiceImpl(final HotelRepositoryWrapper hotelRepositoryWrapper, CityRepository cityRepository) {
        this.hotelRepositoryWrapper = hotelRepositoryWrapper;
        this.cityRepository = cityRepository;
    }

    @Override
    public Hotel createHotel(Hotel hotel, String city) throws HotelAlreadyExists, CityNotFound {
        CityEntity cityEntity = cityRepository.findByNameIgnoreCase(city)
                .orElseThrow(() -> new CityNotFound("город с названием " + city + " не найден"));

        Optional<HotelEntity> hotelEntity = hotelRepositoryWrapper.getHotelRepository().findByName(hotel.getName());
        HotelEntity hotelEntityForSave;

        if (hotelEntity.isPresent())
            if (cityEntity.getHotels().contains(hotelEntity.get()))
                throw new HotelAlreadyExists("Отель с именем = " + hotel.getName() + " уже сущствует в городе " + cityEntity.getName());
            else {
                addHotelInCity(cityEntity, hotelEntity.get());
                hotelEntityForSave = hotelEntity.get();
            }
        else
            hotelEntityForSave = createHotelEntityAndSetInCity(hotel, cityEntity);

        return HotelMapper.INSTANCE.hotelEntityToHotel(hotelRepositoryWrapper.getHotelRepository().save(hotelEntityForSave));
    }

    private void addHotelInCity(CityEntity cityEntity, HotelEntity hotelEntity) {
        cityEntity.getHotels().add(hotelEntity);
        hotelEntity.getCities().add(cityEntity);
    }

    private HotelEntity createHotelEntityAndSetInCity(Hotel hotel, CityEntity cityEntity) {
        HotelEntity hotelToEntity = HotelMapper.INSTANCE.hotelToHotelEntity(hotel);
        hotelToEntity.setCities(new ArrayList<>());
        hotelToEntity.getCities().add(cityEntity);

        cityEntity.getHotels().add(hotelToEntity);

        return hotelToEntity;
    }

    @Override
    public List<Hotel> getAllHotels() {
        return HotelMapper.INSTANCE.hotelEntityListToHotelList(hotelRepositoryWrapper.getHotelRepository().findAll());
    }

    @Override
    public Hotel getHotelById(Long id) throws HotelNotFoundException {
        HotelEntity hotelEntity = hotelRepositoryWrapper.getHotelEntityOrThrow(id);

        return HotelMapper.INSTANCE.hotelEntityToHotel(hotelEntity);
    }

    @Override
    public Hotel updateHotel(Hotel hotel, Long id) throws HotelNotFoundException {
        HotelEntity hotelEntity = hotelRepositoryWrapper.getHotelEntityOrThrow(id);
        hotelEntity.setName(hotel.getName());

        return HotelMapper.INSTANCE.hotelEntityToHotel(hotelRepositoryWrapper.getHotelRepository().save(hotelEntity));
    }

    @Override
    public List<Hotel> getAllHotelsInCity(String cityName) throws CityNotFound {
        CityEntity desiredCity = cityRepository.findByNameIgnoreCase(cityName)
                .orElseThrow(() -> new CityNotFound("город с названием " + cityName + " не найден"));

        List<HotelEntity> hotelEntities = hotelRepositoryWrapper.getHotelRepository().findAllHotelInCity(desiredCity.getId());

        return HotelMapper.INSTANCE.hotelEntityListToHotelList(hotelEntities);
    }

}
