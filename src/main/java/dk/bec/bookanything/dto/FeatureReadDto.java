package dk.bec.bookanything.dto;

import dk.bec.bookanything.model.BookableObjectEntity;
import dk.bec.bookanything.model.FacilityEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
