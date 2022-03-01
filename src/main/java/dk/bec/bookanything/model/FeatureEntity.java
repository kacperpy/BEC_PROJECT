package dk.bec.bookanything.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Data
@Table
@Entity(name = "feature")
public class FeatureEntity {

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
    @JsonBackReference
    private FacilityEntity facility;

    @OneToMany
    private List<BookableObjectEntity> bookableObjects;
}
