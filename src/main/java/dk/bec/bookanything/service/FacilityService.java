package dk.bec.bookanything.service;

import dk.bec.bookanything.model.FacilityEntity;
import dk.bec.bookanything.repository.FacilityRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FacilityService {

    private final FacilityRepository facilityRepository;

    public FacilityService(FacilityRepository facilityRepository) {
        this.facilityRepository = facilityRepository;
    }

    public Optional<FacilityEntity> getFacilityById(Long id)
    {
      return facilityRepository.findById(id);
    }

    public Optional<FacilityEntity> createFacility(FacilityEntity facilityEntity) {
        return Optional.of( facilityRepository.save(facilityEntity));
    }

    public Optional<FacilityEntity> updateFacility(Long id, FacilityEntity newFacility) {
        return facilityRepository.findById(id).map(facility -> {
            facility.setAddressEntity(newFacility.getAddressEntity());
            facility.setFacilityTypeEntity(newFacility.getFacilityTypeEntity());
            facility.setName(newFacility.getName());
            facility.setKrs(newFacility.getKrs());
            facility.setNip(newFacility.getNip());
            return facilityRepository.save(facility);
        });
    }

    public void deleteFacilityById(Long id){facilityRepository.deleteById(id);}
}
