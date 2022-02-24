package dk.bec.bookanything.model;


import javax.persistence.*;

@Entity(name = "facility_case")
public class FacilityCaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 200)
    private String name;
}
