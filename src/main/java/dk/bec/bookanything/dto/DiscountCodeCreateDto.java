package dk.bec.bookanything.dto;

import dk.bec.bookanything.validator.ForeignKeyExistsConstraint;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;


@Builder
@Data
public class DiscountCodeCreateDto {

    @NotBlank(message = "Code is mandatory field")
    private String code;

    @Min(value = 0)
    @Max(value = 100)
    private Integer amount;

    @ForeignKeyExistsConstraint
    private Long facilityId;
}
