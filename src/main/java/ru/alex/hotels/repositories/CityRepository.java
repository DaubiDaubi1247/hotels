package ru.alex.hotels.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.alex.hotels.entitys.CityEntity;

@Repository
public interface CityRepository extends JpaRepository<CityEntity, Long> {
}
