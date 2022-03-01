package dk.bec.bookanything.dto;



import dk.bec.bookanything.model.AddressEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class AddressDto {

    @Size(min = 3, max = 30)
    @NotNull
    private String street;

    @Size(min = 1)
    @NotNull
    private String streetNumber;

    @Size(min = 1)
    private String flatNumber;

    @Size(min = 3, max = 30)
    @NotNull
    private String city;

    @Size(min = 5, max = 5)
    @NotNull
    private String postalCode;

    @Size(min = 3, max = 30)
    private String country;
}
