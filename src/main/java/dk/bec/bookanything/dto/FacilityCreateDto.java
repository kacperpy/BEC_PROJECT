package dk.bec.bookanything.dto;

import dk.bec.bookanything.validator.ForeignKeyExistsConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FacilityCreateDto {

    @Size(min = 3, max = 20)
    String name;

    @Size(min = 10, max = 10)
    String nip;

    @Size(min = 10, max = 10)
    String krs;

    @NotNull(message = "Provide address information")
    private AddressDto addressDto;

    @ForeignKeyExistsConstraint
    private Long facilityTypeId;

}
