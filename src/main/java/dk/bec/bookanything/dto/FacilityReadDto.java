package dk.bec.bookanything.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FacilityReadDto {

    private String name;

    private String nip;

    private String krs;

    private AddressDto addressDto;

    private FacilityTypeDto facilityTypeDto;

    private Integer featureCount;

    private Integer dayOpenCount;
}
