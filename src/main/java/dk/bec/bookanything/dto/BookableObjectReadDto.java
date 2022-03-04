package dk.bec.bookanything.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookableObjectReadDto {

    private Long id;

    private String name;

    private Integer timePeriod;

    private Integer capacity;

    private String description;

    private Boolean reusable;

    private Double price;

    private LocalDateTime dateTime;

    private Integer reservationCount;

    private Long featureId;
}
