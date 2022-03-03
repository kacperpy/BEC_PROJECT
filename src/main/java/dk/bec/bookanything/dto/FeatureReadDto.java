package dk.bec.bookanything.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class FeatureReadDto {

    private String name;

    private String description;

    private Long facilityId;

    private Integer bookableObjectsCount;
}
