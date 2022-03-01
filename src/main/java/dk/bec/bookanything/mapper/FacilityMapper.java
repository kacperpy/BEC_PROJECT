package dk.bec.bookanything.mapper;

import dk.bec.bookanything.dto.FacilityCreateDto;
import dk.bec.bookanything.dto.FacilityReadDto;
import dk.bec.bookanything.model.FacilityEntity;
import dk.bec.bookanything.service.FacilityTypeService;
import org.springframework.stereotype.Component;

@Component
public class FacilityMapper {

    FacilityTypeService facilityTypeService;
    AddressMapper addressMapper;

    public FacilityMapper(FacilityTypeService facilityTypeService, AddressMapper addressMapper) {
        this.facilityTypeService = facilityTypeService;
        this.addressMapper = addressMapper;
    }

    public  FacilityEntity mapFacilityCreateDtoToEntity(FacilityCreateDto facilityCreateDto, Long id) {
      return  FacilityEntity.builder()
                .id(id)
                .name(facilityCreateDto.getName())
                .nip(facilityCreateDto.getNip())
                .krs(facilityCreateDto.getKrs())
                .addressEntity(addressMapper.mapAddressDtoToEntity(facilityCreateDto.getAddressDto(), null))
                .facilityTypeEntity(facilityTypeService.getFacilityTypeById(facilityCreateDto.getFacilityTypeId()))
                .build();
    }

    public FacilityReadDto mapFacilityEntityToReadDto(FacilityEntity facilityEntity) {
       return FacilityReadDto.builder()
                .name(facilityEntity.getName())
                .nip(facilityEntity.getNip())
                .krs(facilityEntity.getKrs())
                .addressDto(addressMapper.mapAddressEntityToDto(facilityEntity.getAddressEntity()))
                .facilityTypeEntity(facilityEntity.getFacilityTypeEntity())
                .featureEntities(facilityEntity.getFeatureEntities())
                .dayOpenEntities(facilityEntity.getDayOpenList())
                .build();
    }
}
