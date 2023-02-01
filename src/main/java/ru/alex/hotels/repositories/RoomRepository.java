package ru.alex.hotels.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.alex.hotels.entitys.RoomEntity;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<RoomEntity, Long>, JpaSpecificationExecutor<RoomEntity> {

    @Query(" SELECT r " +
            "FROM RoomEntity r " +
            "WHERE r.hotel.id = ?1")
    List<RoomEntity> findRoomsByHotelId(Long hotelId);
}
