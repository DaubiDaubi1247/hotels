package ru.alex.hotels.mappers;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import ru.alex.hotels.entity.Director;
import ru.alex.hotels.dto.DirectorDto;

import java.util.List;

@Mapper
@Component
public interface DirectorMapper {
    Director toEntity(DirectorDto directorDto);
    DirectorDto toDto(Director directorEntity);

    List<DirectorDto> toDtoList(List<Director> directorEntityList);
}
