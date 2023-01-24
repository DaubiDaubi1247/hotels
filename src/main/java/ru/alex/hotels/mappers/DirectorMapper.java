package ru.alex.hotels.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.alex.hotels.entitys.DirectorEntity;
import ru.alex.hotels.tdo.Director;

@Mapper
public interface DirectorMapper {
    DirectorMapper INSTANSE = Mappers.getMapper(DirectorMapper.class);
    DirectorEntity directorToDirectorEntity(Director director);
    Director directorEntityToDirector(DirectorEntity directorEntity);
}
