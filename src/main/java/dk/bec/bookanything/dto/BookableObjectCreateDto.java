package dk.bec.bookanything.dto;

import dk.bec.bookanything.validator.ForeignKeyExistsConstraint;
import dk.bec.bookanything.validator.FutureDateTimeConstraint;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

@Data
public class BookableObjectCreateDto {

    @NotBlank(message = "Name is mandatory")
    private String name;

    @Positive(message = "Time period cannot be negative")
    private Integer timePeriod;

    @Positive(message = "Capacity cannot be negative")
    @NotNull(message = "Capacity is mandatory")
    private Integer capacity;

    @NotBlank(message = "Description is mandatory")
    private String description;

    @FutureDateTimeConstraint
    private LocalDateTime dateTime;

    @ForeignKeyExistsConstraint
    private Long featureId;

    @NotNull
    private Boolean reusable;

    @Positive
    private Double price;
}
