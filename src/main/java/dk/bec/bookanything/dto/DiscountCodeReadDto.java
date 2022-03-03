package dk.bec.bookanything.dto;

import lombok.Builder;
import lombok.Data;


@Builder
@Data
public class DiscountCodeReadDto {

    private String code;

    private Integer amount;

    private Long facilityId;

    private String facilityName;

}
