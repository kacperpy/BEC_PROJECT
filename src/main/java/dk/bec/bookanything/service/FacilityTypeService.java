package dk.bec.bookanything.service;


import dk.bec.bookanything.model.FacilityTypeEntity;
import dk.bec.bookanything.repository.FacilityTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@ApplicationScope
public class FacilityTypeService {

    private FacilityTypeRepository facilityTypeRepository;

    @Autowired
    public FacilityTypeService(FacilityTypeRepository facilityTypeRepository){
        this.facilityTypeRepository = facilityTypeRepository;
    }

    public FacilityTypeEntity addFacilityType(FacilityTypeEntity facilityType){
        return facilityTypeRepository.save(facilityType);
    }

    public List<FacilityTypeEntity> getFacilityTypes(){
        return facilityTypeRepository.findAll();
    }

    public FacilityTypeEntity getFacilityTypeByUUID(UUID uuid){
        return facilityTypeRepository.findFacilityTypeByUuid(uuid);
    }

    public void deleteFacilityType(FacilityTypeEntity facilityType){
        facilityTypeRepository.delete(facilityType);
    }

    public void deleteFacilityTypeByUuid(UUID uuid){
        Optional<FacilityTypeEntity> facilityType = Optional.ofNullable(getFacilityTypeByUUID(uuid));
        facilityType.ifPresent(facilityType1 -> facilityTypeRepository.delete(facilityType1));
    }

    public void updateFacilityType(UUID uuid, FacilityTypeEntity newFacilityType){
        Optional<FacilityTypeEntity> facilityTypeOptional = Optional.ofNullable(getFacilityTypeByUUID(uuid));
        facilityTypeOptional.ifPresent(facilityType -> facilityTypeRepository.save(facilityType));
    }



}
