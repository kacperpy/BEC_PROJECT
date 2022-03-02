package dk.bec.bookanything.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class BookableObjectReadDto {

    private Long id;

    private String name;

    private Integer timePeriod;

    private Integer capacity;

    private String description;

    private Boolean is_reusable;

    private Double price;

    private LocalDateTime dateTime;

    private Integer reservationCount;

    private Long featureId;
}
