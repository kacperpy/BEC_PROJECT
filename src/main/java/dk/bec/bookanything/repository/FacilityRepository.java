package dk.bec.bookanything.repository;

import dk.bec.bookanything.model.AddressEntity;
import dk.bec.bookanything.model.FacilityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FacilityRepository extends JpaRepository<FacilityEntity, Long> {

    Optional<FacilityEntity> findByAddressEntity(AddressEntity addressEntity);


}
