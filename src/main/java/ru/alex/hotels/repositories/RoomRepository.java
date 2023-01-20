package ru.alex.hotels.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.alex.hotels.entitys.RoomEntity;

import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<RoomEntity, Long> {
    @Query(" SELECT r " +
            "FROM RoomEntity r " +
            "WHERE r.hotel = ?1 and r.roomNumber = ?2")
    Optional<RoomEntity> findByRoomNumber(Long hotelId, Integer roomNumber);
}