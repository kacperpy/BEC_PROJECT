package dk.bec.bookanything.mapper;


import dk.bec.bookanything.dto.DayOpenCreateDto;
import dk.bec.bookanything.dto.DayOpenReadDto;
import dk.bec.bookanything.model.DayOpenEntity;
import dk.bec.bookanything.model.FacilityEntity;
import dk.bec.bookanything.repository.FacilityRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DayOpenMapper {

    private final FacilityRepository facilityRepository;

    public DayOpenMapper(FacilityRepository facilityRepository) {
        this.facilityRepository = facilityRepository;
    }

    public DayOpenEntity mapDayOpenDtoToEntity(DayOpenCreateDto dayOpenCreateDto, Long id) {
        Optional<FacilityEntity> facilityEntityOptional = facilityRepository.findById(dayOpenCreateDto.getFacilityId());
        return DayOpenEntity.builder()
                .id(id)
                .day(dayOpenCreateDto.getDay())
                .hourFrom(dayOpenCreateDto.getHourFrom())
                .hourTo(dayOpenCreateDto.getHourTo())
                .facility(facilityEntityOptional.orElse(null))
                .build();
    }

    public DayOpenReadDto mapDayOpenEntityToReadDto(DayOpenEntity dayOpenEntity) {
        return DayOpenReadDto.builder()
                .day(WeekDay.getDayNameFromDayNumber(dayOpenEntity.getDay()))
                .hourFrom(dayOpenEntity.getHourFrom())
                .hourTo(dayOpenEntity.getHourTo()).build();
    }
}
