package ru.alex.hotels.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.alex.hotels.entity.City;

import java.util.Optional;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
    Optional<City> findByNameIgnoreCase(String name);

    boolean existsByNameIgnoreCase(String name);
    boolean existByIndex(String index);
}
