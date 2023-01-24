package ru.alex.hotels.mappers;

import org.mapstruct.Mapper;
import ru.alex.hotels.entitys.DirectorEntity;
import ru.alex.hotels.tdo.Director;

@Mapper
public interface DirectorMapper {
    DirectorEntity directorToDirectorEntity(Director director);
    Director directorEntityToDirector(DirectorEntity directorEntity);
}
