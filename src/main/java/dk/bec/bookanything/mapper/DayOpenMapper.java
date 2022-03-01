package dk.bec.bookanything.mapper;


import dk.bec.bookanything.dto.DayOpenCreateDto;
import dk.bec.bookanything.dto.DayOpenReadDto;
import dk.bec.bookanything.model.DayOpenEntity;
import dk.bec.bookanything.model.FacilityEntity;
import dk.bec.bookanything.service.FacilityService;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DayOpenMapper {

    FacilityService facilityService;

    public DayOpenMapper(FacilityService facilityService) {
        this.facilityService = facilityService;
    }

    public DayOpenEntity mapDayOpenDtoToEntity(DayOpenCreateDto dayOpenCreateDto, Long id)
    {
        Optional<FacilityEntity> facilityEntityOptional = facilityService.getFacilityById(dayOpenCreateDto.getFacilityId());
        return DayOpenEntity.builder()
                .id(id)
                .day(dayOpenCreateDto.getDay())
                .hourFrom(dayOpenCreateDto.getHourFrom())
                .hourTo(dayOpenCreateDto.getHourTo())
                .facility(facilityEntityOptional.orElse(null))
                .build();
    }

    public DayOpenReadDto mapDayOpenEntityToReadDto(DayOpenEntity dayOpenEntity){
        return DayOpenReadDto.builder()
                .day(dayOpenEntity.getDay())
                .hourFrom(dayOpenEntity.getHourFrom())
                .hourTo(dayOpenEntity.getHourTo()).build();
    }
}
