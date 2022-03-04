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
public class ReservationReadDto {

    private Long id;
    private LocalDateTime dateFrom;
    private LocalDateTime dateTo;
    private Long bookableObjectEntityId;
    private Long userId;
    private int peopleNumber;

}
