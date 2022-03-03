package dk.bec.bookanything.mapper;

import dk.bec.bookanything.dto.DiscountCodeCreateDto;
import dk.bec.bookanything.dto.DiscountCodeReadDto;
import dk.bec.bookanything.model.DiscountCodeEntity;
import dk.bec.bookanything.model.FacilityEntity;
import dk.bec.bookanything.service.FacilityService;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
public class DiscountCodeMapper {


    private final FacilityService facilityService;

    public DiscountCodeMapper(FacilityService facilityService) {
        this.facilityService = facilityService;
    }

    public DiscountCodeEntity discountCodeDtoToEntity(DiscountCodeCreateDto discountCodeCreateDto) {
        Optional<FacilityEntity> facilityEntity = facilityService.getFacilityById(discountCodeCreateDto.getFacilityId());
        System.out.println(facilityEntity.isPresent());
        return DiscountCodeEntity.builder()
                .code(discountCodeCreateDto.getCode())
                .amount(discountCodeCreateDto.getAmount())
                .facility(facilityEntity.orElse(null)).build();

    }

    public DiscountCodeReadDto discountCodeEntityToDto(DiscountCodeEntity discountCodeEntity) {
        return DiscountCodeReadDto.builder()
                .code(discountCodeEntity.getCode())
                .amount(discountCodeEntity.getAmount())
                .facilityId(discountCodeEntity.getFacility().getId())
                .facilityName(discountCodeEntity.getFacility().getName()).build();
    }

    public DiscountCodeEntity discountCodeDtoToEntityWhenModified(DiscountCodeCreateDto discountCodeCreateDto, Long id) {
        Optional<FacilityEntity> facilityEntity = facilityService.getFacilityById(discountCodeCreateDto.getFacilityId());
        System.out.println(facilityEntity.isPresent());
        return DiscountCodeEntity.builder()
                .id(id)
                .code(discountCodeCreateDto.getCode())
                .amount(discountCodeCreateDto.getAmount())
                .facility(facilityEntity.orElse(null)).build();
    }
}
