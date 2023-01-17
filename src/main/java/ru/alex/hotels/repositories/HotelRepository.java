package ru.alex.hotels.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.alex.hotels.entitys.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
}
