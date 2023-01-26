package ru.alex.hotels.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.alex.hotels.entitys.DirectorEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface DirectorRepository extends JpaRepository<DirectorEntity, Long> {
    Optional<DirectorEntity> findByFcsOrPhoneIgnoreCase(String fcs, String phone);
    Optional<DirectorEntity> findByFcsIgnoreCase(String fcs);

    List<DirectorEntity> findByOrderByFcs();
}
