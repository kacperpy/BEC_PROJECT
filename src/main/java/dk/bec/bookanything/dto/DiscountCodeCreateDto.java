package dk.bec.bookanything.dto;

import dk.bec.bookanything.service.FacilityService;
import dk.bec.bookanything.validator.ForeignKeyExistsConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DiscountCodeCreateDto {

    @NotBlank(message = "Code is mandatory field")
    private String code;

    @Min(value = 0)
    @Max(value = 100)
    private Integer amount;

    @ForeignKeyExistsConstraint(serviceClass = FacilityService.class)
    private Long facilityId;
}
