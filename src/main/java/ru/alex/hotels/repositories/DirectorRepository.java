package ru.alex.hotels.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.alex.hotels.entitys.DirectorEntity;

public interface DirectorRepository extends JpaRepository<DirectorEntity, Long> {
}
