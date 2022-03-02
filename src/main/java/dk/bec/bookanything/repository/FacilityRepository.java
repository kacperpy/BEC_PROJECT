package dk.bec.bookanything.repository;

import dk.bec.bookanything.model.AddressEntity;
import dk.bec.bookanything.model.FacilityEntity;
import dk.bec.bookanything.model.FacilityTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FacilityRepository extends JpaRepository<FacilityEntity, Long> {

    Optional<List<FacilityEntity>> findByAddressEntityIn(List<AddressEntity> addressEntities);

    Optional<List<FacilityEntity>> findByAddressEntityInAndFacilityTypeEntity(List<AddressEntity> addressEntities, FacilityTypeEntity facilityTypeEntity);

    Optional<List<FacilityEntity>> findByFacilityTypeEntity(FacilityTypeEntity facilityTypeEntity);
}
