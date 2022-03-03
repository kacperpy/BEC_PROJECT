package dk.bec.bookanything.dto;

import dk.bec.bookanything.service.BookableObjectService;
import dk.bec.bookanything.service.UserService;
import dk.bec.bookanything.validator.ForeignKeyExistsConstraint;
import dk.bec.bookanything.validator.FutureDateTimeConstraint;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

@Getter
@Setter
public class ReservationCreateDto {

    @ForeignKeyExistsConstraint(serviceClass = UserService.class)
    private Long userId;

    @ForeignKeyExistsConstraint(serviceClass = BookableObjectService.class)
    private Long bookableObjectId;

    @FutureDateTimeConstraint
    private LocalDateTime dateFrom;

    @FutureDateTimeConstraint
    private LocalDateTime dateTo;

    @Positive
    private int peopleNumber;

}
