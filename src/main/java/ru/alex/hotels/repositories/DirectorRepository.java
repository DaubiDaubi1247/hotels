package ru.alex.hotels.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.alex.hotels.entity.Director;

import java.util.List;

@Repository
public interface DirectorRepository extends JpaRepository<Director, Long> {
    boolean existsByFcsOrPhoneIgnoreCase(String fcs, String phone);

    List<Director> findByOrderByFcs();
}
