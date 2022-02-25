package dk.bec.bookanything.model;

import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Data
@Table
@Entity(name = "address")
public class AddressModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column
    @GeneratedValue(generator = "uuid2")
    private UUID uuid;

    @Column(nullable = false)
    String street;

    @Column(nullable = false)
    String streetNumber;

    @Column
    String flatNumber;

    @Column(nullable = false)
    String city;

    @Column(nullable = false)
    String postalCode;

    @Column(nullable = false)
    String country;
}
