package ru.alex.hotels.services.impl;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import ru.alex.hotels.entitys.CityEntity;
import ru.alex.hotels.entitys.DirectorEntity;
import ru.alex.hotels.entitys.HotelEntity;
import ru.alex.hotels.exceptions.CityNotFound;
import ru.alex.hotels.exceptions.DirectorNotFound;
import ru.alex.hotels.exceptions.HotelAlreadyExists;
import ru.alex.hotels.exceptions.HotelNotFoundException;
import ru.alex.hotels.mappers.HotelMapper;
import ru.alex.hotels.repositories.HotelRepository;
import ru.alex.hotels.services.CityService;
import ru.alex.hotels.services.DirectorService;
import ru.alex.hotels.services.HotelService;
import ru.alex.hotels.tdo.Hotel;
import ru.alex.hotels.utils.HotelUtils;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Validated
public class HotelServiceImpl implements HotelService {
    private final HotelRepository hotelRepository;
    private final DirectorService directorService;
    private final CityService cityService;


    @Override
    public Hotel createHotel(@Valid Hotel hotel, String city, Long directorId) throws HotelAlreadyExists, CityNotFound, DirectorNotFound {
        CityEntity cityEntity = cityService.getCityEntityByName(city);

        DirectorEntity directorEntity = directorService.getDirectorEntityById(directorId);

        Optional<HotelEntity> hotelEntity = hotelRepository.findByName(hotel.getName());
        HotelEntity hotelEntityForSave;

        if (hotelEntity.isPresent())
            if (cityEntity.getHotels().contains(hotelEntity.get()))
                throw new HotelAlreadyExists("Отель с именем = " + hotel.getName() + " уже сущствует в городе " + cityEntity.getName());
            else {
                HotelUtils.addHotelInCity(cityEntity, hotelEntity.get());
                hotelEntityForSave = hotelEntity.get();
                hotelEntityForSave.setDirector(directorEntity);
                directorEntity.setHotel(hotelEntityForSave);
            }
        else
            hotelEntityForSave = HotelUtils.createHotelEntityAndSetInCity(hotel, cityEntity);

        return HotelMapper.INSTANCE.hotelEntityToHotel(hotelRepository.save(hotelEntityForSave));
    }

    @Override
    public List<Hotel> getAllHotels() {
        return HotelMapper.INSTANCE.hotelEntityListToHotelList(hotelRepository.findAll());
    }

    @Override
    public Hotel getHotelById(Long id) throws HotelNotFoundException {
        return HotelMapper.INSTANCE.hotelEntityToHotel(getHotelEntityById(id));
    }

    @Override
    public HotelEntity getHotelEntityById(Long id) throws HotelNotFoundException {
        return hotelRepository.findById(id)
                .orElseThrow(() -> new HotelNotFoundException(id));
    }

    @Override
    public Hotel updateHotel(@Valid Hotel hotel, Long id) throws HotelNotFoundException {
        HotelEntity hotelEntity = getHotelEntityById(id);
        hotelEntity.setName(hotel.getName());

        return HotelMapper.INSTANCE.hotelEntityToHotel(hotelRepository.save(hotelEntity));
    }

    @Override
    public List<Hotel> getAllHotelsInCity(String cityName) throws CityNotFound {
        CityEntity desiredCity = cityService.getCityEntityByName(cityName);

        List<HotelEntity> hotelEntities = hotelRepository.findAllHotelInCity(desiredCity.getId());

        return HotelMapper.INSTANCE.hotelEntityListToHotelList(hotelEntities);
    }

}
