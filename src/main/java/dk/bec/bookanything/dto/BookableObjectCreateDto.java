package dk.bec.bookanything.dto;

import dk.bec.bookanything.model.FeatureEntity;
import dk.bec.bookanything.model.ReservationEntity;
import dk.bec.bookanything.validator.ForeignKeyExistsConstraint;
import dk.bec.bookanything.validator.FutureDateTimeConstraint;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.*;

@Data
public class BookableObjectCreateDto {

    @NotBlank(message = "Name is mandatory")
    private String name;

    @Positive(message = "Time_period cannot be negative")
    private Integer timePeriod; //TODO units?

    @Positive(message = "Capacity cannot be negative")
    @NotNull(message = "Capacity is mandatory")
    private Integer capacity;

    @NotBlank(message = "Description is mandatory")
    @Length(max = 200)
    private String description;

    @FutureDateTimeConstraint
    private LocalDateTime dateTime;

    @ForeignKeyExistsConstraint
    private Long featureId;

    @NotNull
    private Boolean isReusable;

    @Positive
    private Double price;
}
