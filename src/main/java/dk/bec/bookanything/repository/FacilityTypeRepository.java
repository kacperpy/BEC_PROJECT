package dk.bec.bookanything.repository;

import dk.bec.bookanything.model.FacilityTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface FacilityTypeRepository  extends JpaRepository<FacilityTypeEntity, Long> {

    FacilityTypeEntity findFacilityTypeByUuid(UUID uuid);


}
