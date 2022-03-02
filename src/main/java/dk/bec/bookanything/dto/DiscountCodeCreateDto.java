package dk.bec.bookanything.dto;

import lombok.Builder;
import lombok.Data;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;



@Builder
@Data
public class DiscountCodeCreateDto {

    @NotBlank(message = "Code is mandatory field!")
    private String code;

    @Min(value = 0)
    @Max(value = 100)
    private Integer amount;

    @NotNull(message = "Facility id is mandatory!")
    private Long facilityId;
}
