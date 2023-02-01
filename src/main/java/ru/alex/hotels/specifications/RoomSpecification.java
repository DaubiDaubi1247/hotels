package ru.alex.hotels.specifications;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import ru.alex.hotels.entitys.RoomEntity;
import ru.alex.hotels.utils.room.RoomCharacteristic;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class RoomSpecification implements Specification<RoomEntity> {

    private final RoomCharacteristic roomCharacteristic;

    @Override
    public Predicate toPredicate(Root<RoomEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        if(roomCharacteristic.getHotel() != null) {
            predicates.add(criteriaBuilder.equal(root.get("hotel"), roomCharacteristic.getHotel()));
        }

        if(roomCharacteristic.getHasTV() != null) {
            predicates.add(criteriaBuilder.equal(root.get("hasTV"), roomCharacteristic.getHasTV()));
        }

        if(roomCharacteristic.getCountBeds() != null) {
            predicates.add(criteriaBuilder.equal(root.get("countBeds"), roomCharacteristic.getCountBeds()));
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

}
