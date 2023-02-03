package ru.alex.hotels.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.alex.hotels.entity.Director;

import java.util.List;
import java.util.Optional;

@Repository
public interface DirectorRepository extends JpaRepository<Director, Long> {
    Optional<Director> findByFcsOrPhoneIgnoreCase(String fcs, String phone);
    Optional<Director> findByFcsIgnoreCase(String fcs);

    List<Director> findByOrderByFcs();
}
