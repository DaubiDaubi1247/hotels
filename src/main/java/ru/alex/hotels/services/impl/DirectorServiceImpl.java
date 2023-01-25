package ru.alex.hotels.services.impl;

import lombok.AllArgsConstructor;
import ru.alex.hotels.entitys.DirectorEntity;
import ru.alex.hotels.exceptions.DirectorAlreadyExist;
import ru.alex.hotels.mappers.DirectorMapper;
import ru.alex.hotels.repositories.DirectorRepository;
import ru.alex.hotels.services.DirectorService;
import ru.alex.hotels.tdo.Director;

import java.util.Optional;

@AllArgsConstructor
public class DirectorServiceImpl implements DirectorService {
    private final DirectorRepository directorRepository;
    @Override
    public Director addDirector(Long hotelId, Director director) throws DirectorAlreadyExist {


        Optional<DirectorEntity> directorEntity = directorRepository.findByFcsAndPhoneIgnoreCase(director.getFcs(), director.getPhone());
        if (directorEntity.isPresent())
            throw new DirectorAlreadyExist("директор с ФИО = " + director.getFcs() + " или" +
                    " номером телефона = " + director.getPhone() + " уже сущесвует в бд");

        DirectorEntity directorEntityForCreate = DirectorMapper.INSTANSE.directorToDirectorEntity(director);

        return DirectorMapper.INSTANSE.directorEntityToDirector(directorRepository.save(directorEntityForCreate));
    }
}
