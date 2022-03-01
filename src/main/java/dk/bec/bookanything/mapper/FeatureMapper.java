package dk.bec.bookanything.mapper;

import dk.bec.bookanything.dto.FeatureCreateDto;
import dk.bec.bookanything.dto.FeatureReadDto;
import dk.bec.bookanything.model.FeatureEntity;
import dk.bec.bookanything.service.FacilityService;
import org.springframework.stereotype.Component;

@Component
public class FeatureMapper {

    private final FacilityService facilityService;

    public FeatureMapper(FacilityService facilityService) {
        this.facilityService = facilityService;
    }

    public FeatureEntity mapFeatureDtoToEntity(FeatureCreateDto featureCreateDto, Long id){
        return FeatureEntity.builder()
//                .id(id)
                .description(featureCreateDto.getDescription())
                .name(featureCreateDto.getName())
                .facility(facilityService.getFacilityById(featureCreateDto.getFacilityId()).get()) //verify
                .build();
    }

    public FeatureReadDto mapFeatureEntityToDto(FeatureEntity featureEntity){
        return FeatureReadDto.builder()
                .description(featureEntity.getDescription())
                .name(featureEntity.getName())
                .facility(featureEntity.getFacility())
                .build();
    }
}
