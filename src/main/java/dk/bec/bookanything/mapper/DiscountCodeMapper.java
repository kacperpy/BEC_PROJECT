package dk.bec.bookanything.mapper;

import dk.bec.bookanything.dto.DiscountCodeCreateDto;
import dk.bec.bookanything.dto.DiscountCodeReadDto;
import dk.bec.bookanything.model.DiscountCodeEntity;
import dk.bec.bookanything.model.FacilityEntity;
import dk.bec.bookanything.repository.FacilityRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@AllArgsConstructor
@Component
public class DiscountCodeMapper {

    @Autowired
    private static FacilityRepository facilityRepository;

    public static DiscountCodeEntity discountCodeDtoToEntity(DiscountCodeCreateDto discountCodeCreateDto){
       Optional<FacilityEntity> facilityEntity = facilityRepository.findById(discountCodeCreateDto.getFacilityId());
        return DiscountCodeEntity.builder()
                .code(discountCodeCreateDto.getCode())
                .amount(discountCodeCreateDto.getAmount())
                .facility(facilityEntity.get()).build();

    }

    public static DiscountCodeReadDto discountCodeEntityToDto(DiscountCodeEntity discountCodeEntity){
        return DiscountCodeReadDto.builder()
                .id(discountCodeEntity.getId())
                .code(discountCodeEntity.getCode())
                .amount(discountCodeEntity.getAmount())
                .facilityId(discountCodeEntity.getFacility().getId())
                .facilityName(discountCodeEntity.getFacility().getName()).build();
    }
}
