package dk.bec.bookanything.mapper;

import dk.bec.bookanything.dto.FeatureCreateDto;
import dk.bec.bookanything.dto.FeatureReadDto;
import dk.bec.bookanything.model.FeatureEntity;
import dk.bec.bookanything.repository.FacilityRepository;
import org.springframework.stereotype.Component;

@Component
public class FeatureMapper {

    private final FacilityRepository facilityRepository;

    public FeatureMapper(FacilityRepository facilityRepository) {
        this.facilityRepository = facilityRepository;
    }

    public FeatureEntity mapFeatureDtoToEntity(FeatureCreateDto featureCreateDto, Long id) {
        return FeatureEntity.builder()
                .id(id)
                .description(featureCreateDto.getDescription())
                .name(featureCreateDto.getName())
                .facility(facilityRepository.getById(featureCreateDto.getFacilityId())) //verify
                .build();
    }

    public FeatureReadDto mapFeatureEntityToDto(FeatureEntity featureEntity) {
        return FeatureReadDto.builder()
                .description(featureEntity.getDescription())
                .name(featureEntity.getName())
                .facilityId(featureEntity.getFacility().getId())
                .bookableObjectsCount(featureEntity.getBookableObjects().size())
                .build();
    }
}
