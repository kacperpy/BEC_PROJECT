package dk.bec.bookanything.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ReservationReadDto {

    private LocalDateTime dateFrom;
    private LocalDateTime dateTo;
    private Long bookableObjectEntityId;
    private Long userId;
    private int peopleNumber;

}
