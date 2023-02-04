package ru.alex.hotels.services.impl;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import ru.alex.hotels.dto.DirectorDto;
import ru.alex.hotels.entity.Director;
import ru.alex.hotels.exceptions.EntityAlreadyExistException;
import ru.alex.hotels.exceptions.EntityNotFoundException;
import ru.alex.hotels.mappers.DirectorMapper;
import ru.alex.hotels.repositories.DirectorRepository;
import ru.alex.hotels.services.DirectorService;

import java.util.List;

@Service
@RequiredArgsConstructor
@Validated
public class DirectorServiceImpl implements DirectorService {
    private final DirectorRepository directorRepository;
    private final DirectorMapper directorMapper;
    @Override
    @Transactional
    public DirectorDto addDirector(@Valid DirectorDto directorDto) {

        if (directorRepository.existsByFcsOrPhoneIgnoreCase(directorDto.getFcs(), directorDto.getPhone()))
            throw new EntityAlreadyExistException("директор с ФИО = " + directorDto.getFcs() + " или" +
                    " номером телефона = " + directorDto.getPhone() + " уже сущесвует в бд");

        Director directorEntityForCreate = directorMapper.toEntity(directorDto);

        return directorMapper.toDto(directorRepository.save(directorEntityForCreate));
    }

    @Override
    @Transactional
    public Director getDirectorEntityById(Long id) {
        return directorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("директор с id = " + id + " не найден"));
    }

    @Override
    @Transactional
    public List<DirectorDto> getDirectorList() {
        return directorMapper.toDtoList(directorRepository.findByOrderByFcs());
    }
}
