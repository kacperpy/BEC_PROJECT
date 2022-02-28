package dk.bec.bookanything.service;

import dk.bec.bookanything.model.FacilityEntity;
import dk.bec.bookanything.repository.FacilityRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FacilityService {

    private FacilityRepository facilityRepository;

    public FacilityService(FacilityRepository facilityRepository) {
        this.facilityRepository = facilityRepository;
    }

    public Optional<FacilityEntity> getFacilityById(Long id)
    {
      return facilityRepository.findById(id);
    }

    public Optional<FacilityEntity> addFacility(FacilityEntity facilityEntity)
    {
        return Optional.of( facilityRepository.save(facilityEntity));
    }

    public Optional<FacilityEntity> modifyFacility(FacilityEntity facilityEntity)
    {
        return Optional.of(facilityRepository.save(facilityEntity));
    }
}
