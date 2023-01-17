package ru.alex.hotels.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.alex.hotels.entitys.HotelEntity;

public interface HotelRepository extends JpaRepository<HotelEntity, Long> {
}
