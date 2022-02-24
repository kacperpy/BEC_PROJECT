package dk.bec.bookanything.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Table
@Entity(name="address")
public class AddressModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @Column(nullable = false)
    String street;
    @Column(nullable = false)
    String streetNumber;

    String flatNumber;
    @Column(nullable = false)
    String city;
    @Column(nullable = false)
    String postalCode;
    @Column(nullable = false)
    String country;
}
