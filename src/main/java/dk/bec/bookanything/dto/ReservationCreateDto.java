package dk.bec.bookanything.dto;

import dk.bec.bookanything.validator.ForeignKeyExistsConstraint;
import dk.bec.bookanything.validator.FutureDateTimeConstraint;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

@Getter
@Setter
public class ReservationCreateDto {

    @ForeignKeyExistsConstraint
    private Long userId;

    @ForeignKeyExistsConstraint
    private Long bookableObjectId;

    @FutureDateTimeConstraint
    private LocalDateTime dateFrom;

    @FutureDateTimeConstraint
    private LocalDateTime dateTo;

    @Positive
    private int peopleNumber;

}
