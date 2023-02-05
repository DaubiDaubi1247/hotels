package ru.alex.hotels.utils.room;

import org.springframework.data.jpa.domain.Specification;
import ru.alex.hotels.entity.Hotel;
import ru.alex.hotels.entity.Room;
import ru.alex.hotels.entity.Room_;

public class RoomSpecifications {
    private static Specification<Room> equalsHasTv(Boolean hasTv) {

        if (hasTv == null) {
            return null;
        }

        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(Room_.hasTv), hasTv);
    }

    private static Specification<Room> equalsCountBeds(Integer countBeds) {
        if (countBeds == null) {
            return null;
        }

        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(Room_.countBeds), countBeds);
    }

    private static Specification<Room> equalsHotel(Hotel hotel) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(Room_.hotel), hotel);
    }

    public static Specification<Room> getRoomsByFilters(RoomCharacteristic roomCharacteristic) {
        return Specification.where(equalsHotel(roomCharacteristic.getHotel()))
                .and(equalsCountBeds(roomCharacteristic.getCountBeds()))
                .and(equalsHasTv(roomCharacteristic.getHasTv()));
    }
}
