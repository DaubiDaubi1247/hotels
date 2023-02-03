package ru.alex.hotels.services.impl;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import ru.alex.hotels.dto.DirectorDto;
import ru.alex.hotels.entity.Director;
import ru.alex.hotels.exceptions.DirectorAlreadyExist;
import ru.alex.hotels.exceptions.DirectorNotFound;
import ru.alex.hotels.mappers.DirectorMapper;
import ru.alex.hotels.repositories.DirectorRepository;
import ru.alex.hotels.services.DirectorService;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Validated
public class DirectorServiceImpl implements DirectorService {
    private final DirectorRepository directorRepository;
    @Override
    public DirectorDto addDirector(@Valid DirectorDto directorDto) throws DirectorAlreadyExist {

        Optional<Director> directorEntity = directorRepository.findByFcsOrPhoneIgnoreCase(directorDto.getFcs(), directorDto.getPhone());
        if (directorEntity.isPresent())
            throw new DirectorAlreadyExist("директор с ФИО = " + directorDto.getFcs() + " или" +
                    " номером телефона = " + directorDto.getPhone() + " уже сущесвует в бд");

        Director directorEntityForCreate = DirectorMapper.INSTANSE.directorToDirectorEntity(directorDto);

        return DirectorMapper.INSTANSE.directorEntityToDirector(directorRepository.save(directorEntityForCreate));
    }

    @Override
    public Director getDirectorEntityById(@Min(1) Long id) throws DirectorNotFound {
        return directorRepository.findById(id)
                .orElseThrow(() -> new DirectorNotFound(id));
    }

    @Override
    public List<DirectorDto> getDirectorList() {
        return DirectorMapper.INSTANSE.directorEntityListToDirectorList(directorRepository.findByOrderByFcs());
    }
}
