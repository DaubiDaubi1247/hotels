package ru.alex.hotels.service.impl;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import ru.alex.hotels.dto.HotelDto;
import ru.alex.hotels.entity.City;
import ru.alex.hotels.entity.Director;
import ru.alex.hotels.entity.Hotel;
import ru.alex.hotels.exception.EntityAlreadyExistException;
import ru.alex.hotels.exception.EntityNotFoundException;
import ru.alex.hotels.mapper.HotelMapper;
import ru.alex.hotels.repository.HotelRepository;
import ru.alex.hotels.service.CityService;
import ru.alex.hotels.service.DirectorService;
import ru.alex.hotels.service.HotelService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Validated
public class HotelServiceImpl implements HotelService {
    private final HotelRepository hotelRepository;
    private final DirectorService directorService;
    private final CityService cityService;
    private final HotelMapper hotelMapper;

    @Override
    @Transactional
    public HotelDto createHotel(@Valid HotelDto hotelDto, String city, Long directorId) {
        City cityEntity = cityService.getCityEntityByName(city);

        Director directorEntity = directorService.getDirectorEntityById(directorId);

        Optional<Hotel> hotelEntity = hotelRepository.findByName(hotelDto.getName());
        Hotel hotelEntityForSave;

        if (hotelEntity.isPresent()) {
            if (cityEntity.getHotels().contains(hotelEntity.get())) {
                throw new EntityAlreadyExistException("Отель с именем = " + hotelDto.getName() +
                        " уже сущствует в городе " + cityEntity.getName());
            }
            else {
                hotelEntityForSave = addHotelInCity(cityEntity, hotelEntity.get());
            }
        }
        else {
            hotelEntityForSave = createHotelEntityAndSetInCity(hotelDto, cityEntity);
        }

        hotelEntityForSave.setDirector(directorEntity);

        return hotelMapper.toDto(hotelRepository.save(hotelEntityForSave));
    }

    @Override
    @Transactional
    public List<HotelDto> getAllHotels() {
        return hotelMapper.toDtoList(hotelRepository.findAll());
    }

    @Override
    public HotelDto getHotelById(Long id) {
        return hotelMapper.toDto(getHotelEntityById(id));
    }

    @Override
    public Hotel getHotelEntityById(Long id) {
        return hotelRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("отель с id = " + id + " не найден"));
    }

    @Override
    @Transactional
    public HotelDto updateHotel(@Valid HotelDto hotelDto, Long id) {
        Hotel hotelEntity = getHotelEntityById(id);
        hotelEntity.setName(hotelDto.getName());

        return hotelMapper.toDto(hotelRepository.save(hotelEntity));
    }

    @Override
    @Transactional
    public List<HotelDto> getAllHotelsInCity(String cityName) {
        City desiredCity = cityService.getCityEntityByName(cityName);

        List<Hotel> hotelEntities = hotelRepository.findAllHotelInCity(desiredCity.getId());

        return hotelMapper.toDtoList(hotelEntities);
    }

    @Override
    @Transactional
    public boolean isHotelExist(Long hotelId) {
        return hotelRepository.existsById(hotelId);
    }

    private Hotel createHotelEntityAndSetInCity(HotelDto hotelDto, City cityEntity) {
        Hotel hotelToEntity = hotelMapper.toEntity(hotelDto);
        hotelToEntity.setCities(new ArrayList<>());

        addHotelInCity(cityEntity, hotelToEntity);

        return hotelToEntity;
    }

    private Hotel addHotelInCity(City cityEntity, Hotel hotelEntity) {
        hotelEntity.getCities().add(cityEntity);

        return hotelEntity;
    }

}
