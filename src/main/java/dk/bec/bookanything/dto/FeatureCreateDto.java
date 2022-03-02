package dk.bec.bookanything.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FeatureCreateDto {

    @NotBlank(message = "Name is mandatory")
    String name;

    private String description;

    private Long facilityId;
}
