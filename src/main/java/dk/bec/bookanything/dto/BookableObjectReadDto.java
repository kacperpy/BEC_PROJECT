package dk.bec.bookanything.dto;

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

    Boolean is_reusable;

    Integer price;

    LocalDateTime date_time;

    Integer reservation_count;

    Long feature_id;
}
