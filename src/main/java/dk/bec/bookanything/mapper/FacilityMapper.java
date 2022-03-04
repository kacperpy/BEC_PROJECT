package dk.bec.bookanything.mapper;

import dk.bec.bookanything.dto.FacilityCreateDto;
import dk.bec.bookanything.dto.FacilityReadDto;
import dk.bec.bookanything.model.FacilityEntity;
import dk.bec.bookanything.repository.FacilityTypeRepository;
import org.springframework.stereotype.Component;

@Component
public class FacilityMapper {

    private final FacilityTypeRepository facilityTypeRepository;
    private final AddressMapper addressMapper;
    private final FacilityTypeMapper facilityTypeMapper;

    public FacilityMapper(FacilityTypeRepository facilityTypeRepository, AddressMapper addressMapper, FacilityTypeMapper facilityTypeMapper) {
        this.facilityTypeRepository = facilityTypeRepository;
        this.addressMapper = addressMapper;
        this.facilityTypeMapper = facilityTypeMapper;
    }

    public FacilityEntity mapFacilityCreateDtoToEntity(FacilityCreateDto facilityCreateDto, Long id) {
        return FacilityEntity.builder()
                .id(id)
                .name(facilityCreateDto.getName())
                .nip(facilityCreateDto.getNip())
                .krs(facilityCreateDto.getKrs())
                .addressEntity(addressMapper.mapAddressDtoToEntity(facilityCreateDto.getAddressDto(), null))
                .facilityTypeEntity(facilityTypeRepository.findFacilityTypeById(facilityCreateDto.getFacilityTypeId()))
                .build();
    }

    public FacilityReadDto mapFacilityEntityToReadDto(FacilityEntity facilityEntity) {

        return FacilityReadDto.builder()
                .id(facilityEntity.getId())
                .name(facilityEntity.getName())
                .nip(facilityEntity.getNip())
                .krs(facilityEntity.getKrs())
                .addressReadDto(addressMapper.mapAddressEntityToDto(facilityEntity.getAddressEntity()))
                .facilityTypeReadDto(facilityEntity.getFacilityTypeEntity() != null ? facilityTypeMapper.mapFacilityTypeEntityToDto(facilityEntity.getFacilityTypeEntity()) : null)
                .featureCount(facilityEntity.getFeatureEntities() != null ? facilityEntity.getFeatureEntities().size() : 0)
                .dayOpenCount(facilityEntity.getDayOpenList() != null ? facilityEntity.getDayOpenList().size() : 0)
                .build();
    }
}
