package dk.bec.bookanything.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Table
@Entity(name="facility")
public class FacilityModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @Column(nullable = false)
    String name;
    @Column(nullable = false)
    String nip;
    @Column(nullable = false)
    String krs;
}
