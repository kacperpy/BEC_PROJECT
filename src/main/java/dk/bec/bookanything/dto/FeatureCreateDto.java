package dk.bec.bookanything.dto;

import dk.bec.bookanything.validator.ForeignKeyExistsConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FeatureCreateDto {

    @NotBlank(message = "Name is mandatory")
    String name;

    @NotBlank(message = "Description can't be empty")
    @Size(max = 200)
    private String description;

    @ForeignKeyExistsConstraint
    private Long facilityId;
}
