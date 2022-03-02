package dk.bec.bookanything.dto;


import dk.bec.bookanything.validator.ForeignKeyExistsConstraint;
import dk.bec.bookanything.validator.FutureDateTimeConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Future;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DayOpenCreateDto {

    @Min(1)
    @Max(7)
    private Integer day;

    @FutureDateTimeConstraint
    private LocalDateTime hourFrom;

    @FutureDateTimeConstraint
    private LocalDateTime hourTo;

    @ForeignKeyExistsConstraint
    private Long facilityId;
}
