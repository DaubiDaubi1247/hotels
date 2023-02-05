package ru.alex.hotels.utils.room;

import org.springframework.data.jpa.domain.Specification;
import ru.alex.hotels.entity.Room;

public class RoomSpecifications {
    public Specification<Room> equalsHasTv(Boolean hasTv) {

        if (hasTv == null) {
            return null;
        }

        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get())
    }
}
