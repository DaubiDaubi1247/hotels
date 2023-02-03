package ru.alex.hotels.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.alex.hotels.entitys.DirectorEntity;
import ru.alex.hotels.dto.DirectorDto;

import java.util.List;

@Mapper
public interface DirectorMapper {
    DirectorMapper INSTANSE = Mappers.getMapper(DirectorMapper.class);
    DirectorEntity directorToDirectorEntity(DirectorDto directorDto);
    DirectorDto directorEntityToDirector(DirectorEntity directorEntity);

    List<DirectorDto> directorEntityListToDirectorList(List<DirectorEntity> directorEntityList);
}
