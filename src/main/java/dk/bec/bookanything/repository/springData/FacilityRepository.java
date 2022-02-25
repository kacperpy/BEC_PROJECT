package dk.bec.bookanything.repository.springData;

import dk.bec.bookanything.model.AddressEntity;
import dk.bec.bookanything.model.FacilityEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FacilityRepository extends CrudRepository<FacilityEntity, Long> {

    Optional<FacilityEntity> findByAddressEntity(AddressEntity addressEntity);

}
