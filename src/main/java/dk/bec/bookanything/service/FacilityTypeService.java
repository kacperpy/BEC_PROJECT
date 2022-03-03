package dk.bec.bookanything.service;


import dk.bec.bookanything.model.FacilityTypeEntity;
import dk.bec.bookanything.repository.FacilityTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;

import java.util.List;
import java.util.Optional;

@Service
@ApplicationScope
public class FacilityTypeService {

    private final FacilityTypeRepository facilityTypeRepository;

    @Autowired
    public FacilityTypeService(FacilityTypeRepository facilityTypeRepository) {
        this.facilityTypeRepository = facilityTypeRepository;
    }

    public FacilityTypeEntity addFacilityType(FacilityTypeEntity facilityType) {
        return facilityTypeRepository.save(facilityType);
    }

    public List<FacilityTypeEntity> getFacilityTypes() {
        return facilityTypeRepository.findAll();
    }

    public FacilityTypeEntity getFacilityTypeById(Long id) {
        return facilityTypeRepository.findFacilityTypeById(id);
    }

    public void deleteFacilityType(FacilityTypeEntity facilityType) {
        facilityTypeRepository.delete(facilityType);
    }

    public void deleteFacilityTypeById(Long id) {
        Optional<FacilityTypeEntity> facilityType = Optional.ofNullable(getFacilityTypeById(id));
        facilityType.ifPresent(facilityTypeRepository::delete);
    }

    public void updateFacilityType(Long id, FacilityTypeEntity newFacilityType) {
        Optional<FacilityTypeEntity> facilityTypeOptional = Optional.ofNullable(getFacilityTypeById(id));
        facilityTypeOptional.ifPresent(facilityType -> facilityTypeRepository.save(newFacilityType));
    }


}
