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

    private Long facility_id;

    private Integer bookableObjects_count;
}
