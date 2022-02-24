package dk.bec.bookanything.model;


import lombok.Data;

import javax.persistence.*;

@Data
@Table
@Entity(name = "facility_case")
public class FacilityTypeModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 200)
    private String name;
}
