package dk.bec.bookanything.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressReadDto {

    private Long id;

    private String street;

    private String streetNumber;

    private String propertyNumber;

    private String city;

    private String postalCode;

    private String country;
}
