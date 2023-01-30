package ru.alex.hotels.services.impl;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import ru.alex.hotels.entitys.DirectorEntity;
import ru.alex.hotels.exceptions.DirectorAlreadyExist;
import ru.alex.hotels.exceptions.DirectorNotFound;
import ru.alex.hotels.mappers.DirectorMapper;
import ru.alex.hotels.repositories.DirectorRepository;
import ru.alex.hotels.services.DirectorService;
import ru.alex.hotels.dto.Director;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Validated
public class DirectorServiceImpl implements DirectorService {
    private final DirectorRepository directorRepository;
    @Override
    public Director addDirector(@Valid Director director) throws DirectorAlreadyExist {

        Optional<DirectorEntity> directorEntity = directorRepository.findByFcsOrPhoneIgnoreCase(director.getFcs(), director.getPhone());
        if (directorEntity.isPresent())
            throw new DirectorAlreadyExist("директор с ФИО = " + director.getFcs() + " или" +
                    " номером телефона = " + director.getPhone() + " уже сущесвует в бд");

        DirectorEntity directorEntityForCreate = DirectorMapper.INSTANSE.directorToDirectorEntity(director);

        return DirectorMapper.INSTANSE.directorEntityToDirector(directorRepository.save(directorEntityForCreate));
    }

    @Override
    public DirectorEntity getDirectorEntityById(@Min(1) Long id) throws DirectorNotFound {
        return directorRepository.findById(id)
                .orElseThrow(() -> new DirectorNotFound(id));
    }

    @Override
    public List<Director> getDirectorList() {
        return DirectorMapper.INSTANSE.directorEntityListToDirectorList(directorRepository.findByOrderByFcs());
    }
}
