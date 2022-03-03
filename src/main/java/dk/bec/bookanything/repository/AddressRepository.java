package dk.bec.bookanything.repository;

import dk.bec.bookanything.model.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<AddressEntity, Long> {
    Optional<List<AddressEntity>> findByCity(String city);
}
