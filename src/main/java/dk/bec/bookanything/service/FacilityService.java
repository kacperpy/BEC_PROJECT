package dk.bec.bookanything.service;

import dk.bec.bookanything.dto.DayOpenReadDto;
import dk.bec.bookanything.dto.FeatureReadDto;
import dk.bec.bookanything.mapper.DayOpenMapper;
import dk.bec.bookanything.mapper.FeatureMapper;
import dk.bec.bookanything.model.AddressEntity;
import dk.bec.bookanything.model.FacilityEntity;
import dk.bec.bookanything.model.FacilityTypeEntity;
import dk.bec.bookanything.repository.FacilityRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FacilityService {

    private final FacilityRepository facilityRepository;
    private final FeatureMapper featureMapper;
    private final DayOpenMapper dayOpenMapper;

    public FacilityService(FacilityRepository facilityRepository, FeatureMapper featureMapper, DayOpenMapper dayOpenMapper) {
        this.facilityRepository = facilityRepository;
        this.featureMapper = featureMapper;
        this.dayOpenMapper = dayOpenMapper;
    }

    public Optional<FacilityEntity> getFacilityById(Long id)
    {
      return facilityRepository.findById(id);
    }

    public List<FeatureReadDto> getFeaturesForFacility(Long id)
    {
        return facilityRepository.findById(id).get().getFeatureEntities().stream().map(
                featureMapper::mapFeatureEntityToDto
        ).collect(Collectors.toList());
    }
    public List<DayOpenReadDto> getDaysOpenForFacility(Long id)
    {
        return facilityRepository.findById(id).get().getDayOpenList().stream()
                .map(dayOpenMapper::mapDayOpenEntityToReadDto).collect(Collectors.toList());
    }


    public Optional<FacilityEntity> createFacility(FacilityEntity facilityEntity) {
        return Optional.of( facilityRepository.save(facilityEntity));
    }

    public Optional<FacilityEntity> updateFacility(Long id, FacilityEntity newFacility) {
        if(getFacilityById(id).isPresent())
            return Optional.of(facilityRepository.save(newFacility));
        else
            return Optional.empty();
    }

    public Optional<List<FacilityEntity>> getFacilitiesByType(FacilityTypeEntity facilityTypeEntity) {
        return facilityRepository.findByFacilityTypeEntity(facilityTypeEntity);
    }

    public Optional<List<FacilityEntity>> getFacilitiesByAddressIn(List<AddressEntity> addressEntities) {
        return facilityRepository.findByAddressEntityIn(addressEntities);
    }

    public Optional<List<FacilityEntity>> getFacilitiesByAddressInAndType(List<AddressEntity> addressEntities, FacilityTypeEntity facilityTypeEntity) {
        return facilityRepository.findByAddressEntityInAndFacilityTypeEntity(addressEntities, facilityTypeEntity);
    }

    public void deleteFacilityById(Long id){facilityRepository.deleteById(id);}
}
