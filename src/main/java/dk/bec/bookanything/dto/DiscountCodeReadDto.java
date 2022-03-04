package dk.bec.bookanything.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DiscountCodeReadDto {
    private Long id;

    private String code;

    private Integer amount;

    private Long facilityId;

    private String facilityName;

}
