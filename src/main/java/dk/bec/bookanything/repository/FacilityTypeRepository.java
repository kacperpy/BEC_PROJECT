package dk.bec.bookanything.repository;

import dk.bec.bookanything.model.FacilityTypeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacilityTypeRepository  extends CrudRepository<FacilityTypeEntity, Long> {
}
