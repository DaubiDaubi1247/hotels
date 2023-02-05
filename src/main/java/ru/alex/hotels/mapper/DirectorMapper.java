package ru.alex.hotels.mapper;

import org.mapstruct.Mapper;
import ru.alex.hotels.dto.DirectorDto;
import ru.alex.hotels.entity.Director;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DirectorMapper {
    Director toEntity(DirectorDto directorDto);
    DirectorDto toDto(Director directorEntity);

    List<DirectorDto> toDtoList(List<Director> directorEntityList);
}
