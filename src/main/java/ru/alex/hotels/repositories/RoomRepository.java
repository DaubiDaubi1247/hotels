package ru.alex.hotels.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.alex.hotels.entity.Room;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long>, JpaSpecificationExecutor<Room> {

    @Query(" SELECT r " +
            "FROM Room r " +
            "WHERE r.hotel.id = ?1")
    List<Room> findRoomsByHotelId(Long hotelId);
}
