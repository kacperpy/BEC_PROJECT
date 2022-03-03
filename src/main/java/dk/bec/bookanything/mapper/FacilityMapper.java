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
    private final FeatureMapper featureMapper;
    private final DayOpenMapper dayOpenMapper;

    public FacilityMapper(FacilityTypeRepository facilityTypeRepository, AddressMapper addressMapper, FacilityTypeMapper facilityTypeMapper, FeatureMapper featureMapper, DayOpenMapper dayOpenMapper) {
        this.facilityTypeRepository = facilityTypeRepository;
        this.addressMapper = addressMapper;
        this.facilityTypeMapper = facilityTypeMapper;
        this.featureMapper = featureMapper;
        this.dayOpenMapper = dayOpenMapper;
    }

    public  FacilityEntity mapFacilityCreateDtoToEntity(FacilityCreateDto facilityCreateDto, Long id) {
      return  FacilityEntity.builder()
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
                .name(facilityEntity.getName())
                .nip(facilityEntity.getNip())
                .krs(facilityEntity.getKrs())
                .addressDto(addressMapper.mapAddressEntityToDto(facilityEntity.getAddressEntity()))
                .facilityTypeDto(facilityEntity.getFacilityTypeEntity() != null ? facilityTypeMapper.mapFacilityTypeEntityToDto( facilityEntity.getFacilityTypeEntity()) : null)
                .featureCount(facilityEntity.getFeatureEntities() != null ? facilityEntity.getFeatureEntities().size() : 0)
                .dayOpenCount(facilityEntity.getDayOpenList() != null ? facilityEntity.getDayOpenList().size() : 0)
                .build();
    }
}
