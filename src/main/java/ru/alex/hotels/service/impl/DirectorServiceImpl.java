package ru.alex.hotels.service.impl;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import ru.alex.hotels.dto.DirectorDto;
import ru.alex.hotels.entity.Director;
import ru.alex.hotels.exception.EntityAlreadyExistException;
import ru.alex.hotels.exception.EntityNotFoundException;
import ru.alex.hotels.mapper.DirectorMapper;
import ru.alex.hotels.repository.DirectorRepository;
import ru.alex.hotels.service.DirectorService;

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
