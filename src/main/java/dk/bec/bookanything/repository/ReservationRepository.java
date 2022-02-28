package dk.bec.bookanything.repository;

import dk.bec.bookanything.model.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ReservationRepository extends JpaRepository<ReservationEntity, Long> {
    Optional<ReservationEntity> findByUuid(UUID uuid);
    void deleteByUuid(UUID fromString);
}
