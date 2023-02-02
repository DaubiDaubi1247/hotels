package ru.alex.hotels.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.alex.hotels.entitys.HotelEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface HotelRepository extends JpaRepository<HotelEntity, Long> {
    Optional<HotelEntity> findByName(String hotelName);

    @Query(" SELECT h " +
            "FROM HotelEntity h " +
            "JOIN h.cities c " +
            "where c.id = ?1")
        List<HotelEntity> findAllHotelInCity(Long cityId);
}
