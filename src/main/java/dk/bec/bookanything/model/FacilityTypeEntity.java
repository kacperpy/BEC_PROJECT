package dk.bec.bookanything.model;


import lombok.Data;

import javax.persistence.*;

@Data
@Table
@Entity(name = "facility_type")
public class FacilityTypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 200)
    private String name;
}
