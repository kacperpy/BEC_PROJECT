package dk.bec.bookanything.dto;

import lombok.Data;

import java.time.LocalDateTime;

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

    @NotBlank(message = "Is_reusable is mandatory")
    Boolean is_reusable;

    @Positive(message = "Price cannot be negative")
    @NotBlank(message = "Price is mandatory")
    Integer price;

    LocalDateTime date_time;

    Long feature_id;
}
