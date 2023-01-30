package ru.alex.hotels.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.alex.hotels.entitys.DirectorEntity;
import ru.alex.hotels.dto.Director;

import java.util.List;

@Mapper
public interface DirectorMapper {
    DirectorMapper INSTANSE = Mappers.getMapper(DirectorMapper.class);
    DirectorEntity directorToDirectorEntity(Director director);
    Director directorEntityToDirector(DirectorEntity directorEntity);

    List<Director> directorEntityListToDirectorList(List<DirectorEntity> directorEntityList);
}
