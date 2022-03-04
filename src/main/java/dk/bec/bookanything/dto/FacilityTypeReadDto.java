package dk.bec.bookanything.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FacilityTypeReadDto {

    private Long id;

    private String name;
}
