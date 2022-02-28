package dk.bec.bookanything.dto;



import dk.bec.bookanything.model.AddressEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.persistence.Column;

@Data
@NoArgsConstructor
@Validated
public class AddressDto {

    @Column(nullable = false)
    private String street;

    @Column(nullable = false)
    private String streetNumber;

    @Column
    private String flatNumber;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String postalCode;

    @Column(nullable = false)
    private String country;

    public AddressDto(AddressEntity addressEntity) {
        this.street = addressEntity.getStreet();
        this.streetNumber = addressEntity.getStreetNumber();
        this.flatNumber = addressEntity.getFlatNumber();
        this.city = addressEntity.getCity();
        this.postalCode = addressEntity.getPostalCode();
        this.country = addressEntity.getCountry();
    }

}
