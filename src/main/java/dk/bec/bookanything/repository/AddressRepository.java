package dk.bec.bookanything.repository;

import dk.bec.bookanything.model.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AddressRepository extends JpaRepository<AddressEntity, Long> {

    Optional<AddressEntity> findByUuid(UUID uuid);

    @Transactional
    void deleteByUuid(UUID uuid);

}
