package dk.bec.bookanything.dto;

import dk.bec.bookanything.model.FeatureEntity;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class BookableObjectReadDto {

    Long id;

    String name;

    Integer time_period;

    Integer capacity;

    String description;

    LocalDateTime date_time;

    Integer reservation_count;

    Long feature_id;
}
