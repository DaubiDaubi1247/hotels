package ru.alex.hotels.services.impl;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import ru.alex.hotels.dto.HotelDto;
import ru.alex.hotels.entitys.City;
import ru.alex.hotels.entitys.Director;
import ru.alex.hotels.entitys.Hotel;
import ru.alex.hotels.exceptions.CityNotFound;
import ru.alex.hotels.exceptions.DirectorNotFound;
import ru.alex.hotels.exceptions.HotelAlreadyExists;
import ru.alex.hotels.exceptions.HotelNotFoundException;
import ru.alex.hotels.mappers.HotelMapper;
import ru.alex.hotels.repositories.HotelRepository;
import ru.alex.hotels.services.CityService;
import ru.alex.hotels.services.DirectorService;
import ru.alex.hotels.services.HotelService;
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
    public HotelDto createHotel(@Valid HotelDto hotelDto, String city, Long directorId) throws HotelAlreadyExists, CityNotFound, DirectorNotFound {
        City cityEntity = cityService.getCityEntityByName(city);

        Director directorEntity = directorService.getDirectorEntityById(directorId);

        Optional<Hotel> hotelEntity = hotelRepository.findByName(hotelDto.getName());
        Hotel hotelEntityForSave;

        if (hotelEntity.isPresent())
            if (cityEntity.getHotels().contains(hotelEntity.get()))
                throw new HotelAlreadyExists("Отель с именем = " + hotelDto.getName() + " уже сущствует в городе " + cityEntity.getName());
            else {
                HotelUtils.addHotelInCity(cityEntity, hotelEntity.get());
                hotelEntityForSave = hotelEntity.get();
                hotelEntityForSave.setDirector(directorEntity);
            }
        else
            hotelEntityForSave = HotelUtils.createHotelEntityAndSetInCity(hotelDto, cityEntity);

        return HotelMapper.INSTANCE.hotelEntityToHotel(hotelRepository.save(hotelEntityForSave));
    }

    @Override
    public List<HotelDto> getAllHotels() {
        return HotelMapper.INSTANCE.hotelEntityListToHotelList(hotelRepository.findAll());
    }

    @Override
    public HotelDto getHotelById(Long id) throws HotelNotFoundException {
        return HotelMapper.INSTANCE.hotelEntityToHotel(getHotelEntityById(id));
    }

    @Override
    public Hotel getHotelEntityById(Long id) throws HotelNotFoundException {
        return hotelRepository.findById(id)
                .orElseThrow(() -> new HotelNotFoundException(id));
    }

    @Override
    public HotelDto updateHotel(@Valid HotelDto hotelDto, Long id) throws HotelNotFoundException {
        Hotel hotelEntity = getHotelEntityById(id);
        hotelEntity.setName(hotelDto.getName());

        return HotelMapper.INSTANCE.hotelEntityToHotel(hotelRepository.save(hotelEntity));
    }

    @Override
    public List<HotelDto> getAllHotelsInCity(String cityName) throws CityNotFound {
        City desiredCity = cityService.getCityEntityByName(cityName);

        List<Hotel> hotelEntities = hotelRepository.findAllHotelInCity(desiredCity.getId());

        return HotelMapper.INSTANCE.hotelEntityListToHotelList(hotelEntities);
    }

}
