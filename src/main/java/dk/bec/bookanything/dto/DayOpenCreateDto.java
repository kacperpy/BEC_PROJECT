package dk.bec.bookanything.dto;


import dk.bec.bookanything.service.FacilityService;
import dk.bec.bookanything.validator.ForeignKeyExistsConstraint;
import dk.bec.bookanything.validator.FutureDateTimeConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
public class DayOpenCreateDto {

    @Min(1)
    @Max(7)
    private Integer day;

    @FutureDateTimeConstraint
    @NotNull
    private LocalDateTime hourFrom;

    @FutureDateTimeConstraint
    @NotNull
    private LocalDateTime hourTo;

    @ForeignKeyExistsConstraint(serviceClass = FacilityService.class)
    private Long facilityId;
}
