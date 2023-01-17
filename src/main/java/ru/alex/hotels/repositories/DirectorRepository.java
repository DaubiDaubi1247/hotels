package ru.alex.hotels.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.alex.hotels.entitys.Director;

public interface DirectorRepository extends JpaRepository<Director, Long> {
}
