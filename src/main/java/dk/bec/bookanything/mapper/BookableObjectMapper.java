package dk.bec.bookanything.mapper;

import dk.bec.bookanything.dto.BookableObjectCreateDto;
import dk.bec.bookanything.dto.BookableObjectReadDto;
import dk.bec.bookanything.model.BookableObjectEntity;
import dk.bec.bookanything.service.FeatureService;
import org.springframework.stereotype.Component;

@Component
public class BookableObjectMapper {

    private final FeatureService featureService;

    public BookableObjectMapper(FeatureService featureService) {
        this.featureService = featureService;
    }

    public BookableObjectEntity mapDtoToEntity(BookableObjectCreateDto bookableObjectCreateDto, Long id) {
        return BookableObjectEntity.builder()
                .id(id)
                .name(bookableObjectCreateDto.getName())
                .time_period(bookableObjectCreateDto.getTime_period())
                .capacity(bookableObjectCreateDto.getCapacity())
                .description(bookableObjectCreateDto.getDescription())
                .date_time(bookableObjectCreateDto.getDate_time())
                .reservations(null)
                .feature(featureService.getFeatureById(bookableObjectCreateDto.getFeature_id()).get())
                .build();
    }

    public BookableObjectReadDto mapEntityToDto(BookableObjectEntity bookableObjectEntity) {
        return BookableObjectReadDto.builder()
                .id(bookableObjectEntity.getId())
                .name(bookableObjectEntity.getName())
                .time_period(bookableObjectEntity.getTime_period())
                .capacity(bookableObjectEntity.getCapacity())
                .description(bookableObjectEntity.getDescription())
                .date_time(bookableObjectEntity.getDate_time())
                .reservation_count(bookableObjectEntity.getReservations() != null ? bookableObjectEntity.getReservations().size() : 0)
                .feature_id(bookableObjectEntity.getFeature() == null ? 0 : bookableObjectEntity.getFeature().getId())
                .build();
    }
}
