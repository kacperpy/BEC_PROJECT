package dk.bec.bookanything.mapper;

import dk.bec.bookanything.dto.BookableObjectCreateDto;
import dk.bec.bookanything.dto.BookableObjectReadDto;
import dk.bec.bookanything.model.BookableObjectEntity;
import dk.bec.bookanything.repository.FeatureRepository;
import org.springframework.stereotype.Component;

@Component
public class BookableObjectMapper {

    private final FeatureRepository featureRepository;

    public BookableObjectMapper(FeatureRepository featureRepository) {
        this.featureRepository = featureRepository;
    }

    public BookableObjectEntity mapDtoToEntity(BookableObjectCreateDto bookableObjectCreateDto, Long id) {
        return BookableObjectEntity.builder()
                .id(id)
                .name(bookableObjectCreateDto.getName())
                .timePeriod(bookableObjectCreateDto.getTimePeriod())
                .capacity(bookableObjectCreateDto.getCapacity())
                .description(bookableObjectCreateDto.getDescription())
                .dateTime(bookableObjectCreateDto.getDateTime())
                .reservations(null)
                .feature(featureRepository.getById(bookableObjectCreateDto.getFeatureId()))
                .reusable(bookableObjectCreateDto.getReusable())
                .price(bookableObjectCreateDto.getPrice())
                .build();
    }

    public BookableObjectReadDto mapEntityToDto(BookableObjectEntity bookableObjectEntity) {
        return BookableObjectReadDto.builder()
                .id(bookableObjectEntity.getId())
                .name(bookableObjectEntity.getName())
                .timePeriod(bookableObjectEntity.getTimePeriod())
                .capacity(bookableObjectEntity.getCapacity())
                .description(bookableObjectEntity.getDescription())
                .dateTime(bookableObjectEntity.getDateTime())
                .reservationCount(bookableObjectEntity.getReservations() != null ? bookableObjectEntity.getReservations().size() : 0)
                .featureId(bookableObjectEntity.getFeature() == null ? 0 : bookableObjectEntity.getFeature().getId())
                .reusable(bookableObjectEntity.getReusable())
                .price(bookableObjectEntity.getPrice())
                .build();
    }
}
