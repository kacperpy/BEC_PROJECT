package dk.bec.bookanything.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
public class ReservationCreateDto {
    @NotNull(message = "User id is required")
    private Long userId;
    @NotNull(message = "Bookable object id is required")
    private Long bookableObjectId;
    @NotNull(message = "Date from is required")
    private LocalDateTime dateFrom;
    @NotNull(message = "Date to is required")
    private LocalDateTime dateTo;
}
