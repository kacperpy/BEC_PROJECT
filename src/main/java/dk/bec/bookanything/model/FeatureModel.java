package dk.bec.bookanything.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Data
@Table
@Entity(name = "feature")
public class FeatureModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    @GeneratedValue(generator = "uuid2")
    private UUID uuid;

    @Column(length = 64)
    private String name;

    @Column(length = 200)
    private String description;

    @ManyToOne
    private FacilityModel facility;

    @OneToMany
    private List<BookableObjectModel> bookableObjects;
}
