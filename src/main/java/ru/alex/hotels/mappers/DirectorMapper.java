package ru.alex.hotels.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.alex.hotels.entitys.Director;
import ru.alex.hotels.dto.DirectorDto;

import java.util.List;

@Mapper
public interface DirectorMapper {
    DirectorMapper INSTANSE = Mappers.getMapper(DirectorMapper.class);
    Director directorToDirectorEntity(DirectorDto directorDto);
    DirectorDto directorEntityToDirector(Director directorEntity);

    List<DirectorDto> directorEntityListToDirectorList(List<Director> directorEntityList);
}
