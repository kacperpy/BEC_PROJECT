package dk.bec.bookanything.dto;

import dk.bec.bookanything.model.DayOpenEntity;
import dk.bec.bookanything.model.FacilityTypeEntity;
import dk.bec.bookanything.model.FeatureEntity;
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

    private FacilityTypeEntity facilityTypeEntity;

    private List<FeatureEntity> featureEntities;

    private List<DayOpenEntity> dayOpenEntities;
}
