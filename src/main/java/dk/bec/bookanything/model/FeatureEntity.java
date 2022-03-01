package dk.bec.bookanything.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Builder
@Data
@Table
@Entity(name = "feature")
@NoArgsConstructor
@AllArgsConstructor
public class FeatureEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

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
