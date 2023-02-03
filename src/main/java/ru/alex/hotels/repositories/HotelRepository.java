package ru.alex.hotels.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.alex.hotels.entitys.Hotel;

import java.util.List;
import java.util.Optional;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {
    Optional<Hotel> findByName(String hotelName);

    @Query(" SELECT h " +
            "FROM Hotel h " +
            "JOIN h.cities c " +
            "where c.id = ?1")
    List<Hotel> findAllHotelInCity(Long cityId);
}
