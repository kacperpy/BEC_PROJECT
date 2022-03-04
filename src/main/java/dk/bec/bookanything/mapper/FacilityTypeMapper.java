package dk.bec.bookanything.mapper;

import dk.bec.bookanything.dto.FacilityTypeCreateDto;
import dk.bec.bookanything.dto.FacilityTypeReadDto;
import dk.bec.bookanything.model.FacilityTypeEntity;
import org.springframework.stereotype.Component;

@Component
public class FacilityTypeMapper {

    public FacilityTypeReadDto mapFacilityTypeEntityToDto(FacilityTypeEntity facilityTypeEntity) {
        return FacilityTypeReadDto.builder()
                .id(facilityTypeEntity.getId())
                .name(facilityTypeEntity.getName())
                .build();
    }

    public FacilityTypeEntity mapFacilityTypeDtoToEntity(FacilityTypeCreateDto facilityTypeCreateDto, Long id) {
        return FacilityTypeEntity.builder()
                .id(id)
                .name(facilityTypeCreateDto.getName())
                .build();
    }
}
