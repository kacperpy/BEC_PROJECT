package dk.bec.bookanything.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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

    private List<FeatureReadDto> featureReadDtos;

    private List<DayOpenReadDto> dayOpenReadDtos;
}
