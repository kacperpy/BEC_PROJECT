package dk.bec.bookanything.dto;

import dk.bec.bookanything.model.FeatureEntity;
import dk.bec.bookanything.model.ReservationEntity;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Data
public class BookableObjectCreateDto {

    @NotBlank(message = "Name is mandatory")
    String name;

    @Positive(message = "Time_period cannot be negative")
    Integer time_period;

    @Positive(message = "Capacity cannot be negative")
    @NotBlank(message = "Capacity is mandatory")
    Integer capacity;

    @NotBlank(message = "Description is mandatory")
    String description;

    LocalDateTime date_time;

    Long feature_id;
}
