package dk.bec.bookanything.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {

    @Size(min = 3, max = 30)
    @NotNull
    private String street;

    @Min(1)
    @NotNull
    private String streetNumber;

    @Min(1)
    private String flatNumber;

    @Size(min = 3, max = 30)
    @NotNull
    private String city;

    @Size(min = 5, max = 5) //TODO think about it
    @NotNull
    private String postalCode;

    @Size(min = 3, max = 30)
    private String country; //TODO maybe verify country
}
