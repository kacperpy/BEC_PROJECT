package dk.bec.bookanything.model;

import javax.persistence.*;

@Entity(name = "feature")
public class FeatureModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 64)
    private String name;

    @Column(length = 200)
    private String description;




}
