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

    private Long id;

    private String name;

    private String nip;

    private String krs;

    private AddressReadDto addressReadDto;

    private FacilityTypeReadDto facilityTypeReadDto;

    private Integer featureCount;

    private Integer dayOpenCount;
}
