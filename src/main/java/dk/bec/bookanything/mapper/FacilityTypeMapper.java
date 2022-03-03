package dk.bec.bookanything.mapper;

import dk.bec.bookanything.dto.FacilityTypeDto;
import dk.bec.bookanything.model.FacilityTypeEntity;
import org.springframework.stereotype.Component;

@Component
public class FacilityTypeMapper {

    public FacilityTypeDto mapFacilityTypeEntityToDto(FacilityTypeEntity facilityTypeEntity) {
        return FacilityTypeDto.builder()
                .name(facilityTypeEntity.getName())
                .build();
    }

    public FacilityTypeEntity mapFacilityTypeDtoToEntity(FacilityTypeDto facilityTypeDto, Long id) {
        return FacilityTypeEntity.builder()
                .id(id)
                .name(facilityTypeDto.getName())
                .build();
    }
}
