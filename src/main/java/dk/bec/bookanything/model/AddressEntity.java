package dk.bec.bookanything.model;

import dk.bec.bookanything.dto.AddressDto;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Data
@Table
@NoArgsConstructor
@Entity(name = "address")
public class AddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

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

    public AddressEntity(AddressDto dto) {
        this.street = dto.getStreet();
        this.streetNumber = dto.getStreetNumber();
        this.flatNumber = dto.getFlatNumber();
        this.city = dto.getCity();
        this.postalCode = dto.getPostalCode();
        this.country = dto.getCountry();
    }
}
