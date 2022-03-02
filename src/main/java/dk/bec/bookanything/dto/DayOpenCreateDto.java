package dk.bec.bookanything.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DayOpenCreateDto {

    private Integer day;

    private LocalTime hourFrom;

    private LocalTime hourTo;

    private Long facilityId;
}
