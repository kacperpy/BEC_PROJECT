package dk.bec.bookanything.mapper;

import dk.bec.bookanything.dto.DayOpenReadDto;
import dk.bec.bookanything.dto.FacilityCreateDto;
import dk.bec.bookanything.dto.FacilityReadDto;
import dk.bec.bookanything.dto.FeatureReadDto;
import dk.bec.bookanything.model.FacilityEntity;
import dk.bec.bookanything.service.FacilityTypeService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FacilityMapper {

    private final FacilityTypeService facilityTypeService;
    private final AddressMapper addressMapper;
    private final FacilityTypeMapper facilityTypeMapper;
    private final FeatureMapper featureMapper;
    private final DayOpenMapper dayOpenMapper;

    public FacilityMapper(FacilityTypeService facilityTypeService, AddressMapper addressMapper, FacilityTypeMapper facilityTypeMapper, FeatureMapper featureMapper, DayOpenMapper dayOpenMapper) {
        this.facilityTypeService = facilityTypeService;
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
                .facilityTypeEntity(facilityTypeService.getFacilityTypeById(facilityCreateDto.getFacilityTypeId()))
                .build();
    }

    public FacilityReadDto mapFacilityEntityToReadDto(FacilityEntity facilityEntity) {
        List<FeatureReadDto> featureReadDtos = new ArrayList<>();
        if(facilityEntity.getFeatureEntities() != null)
        featureReadDtos = facilityEntity.getFeatureEntities().stream()
                        .map(featureMapper::mapFeatureEntityToDto).collect(Collectors.toList());

        List<DayOpenReadDto>  dayOpenReadDtos = new ArrayList<>();
        if(facilityEntity.getDayOpenList() != null)
        dayOpenReadDtos =
                facilityEntity.getDayOpenList().stream()
                        .map(dayOpenMapper::mapDayOpenEntityToReadDto).collect(Collectors.toList());

        return FacilityReadDto.builder()
                .name(facilityEntity.getName())
                .nip(facilityEntity.getNip())
                .krs(facilityEntity.getKrs())
                .addressDto(addressMapper.mapAddressEntityToDto(facilityEntity.getAddressEntity()))
                .facilityTypeDto(facilityEntity.getFacilityTypeEntity() != null ? facilityTypeMapper.mapFacilityTypeEntityToDto( facilityEntity.getFacilityTypeEntity()) : null)
                .featureReadDtos(featureReadDtos)
                .dayOpenReadDtos(dayOpenReadDtos)
                .build();
    }
}
