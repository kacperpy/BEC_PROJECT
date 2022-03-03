package dk.bec.bookanything.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Builder
@Data
@Table
@Entity(name = "feature")
@NoArgsConstructor
@AllArgsConstructor
public class FeatureEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 64)
    private String name;

    @Column(length = 200)
    private String description;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "facility_id", referencedColumnName = "id")
    private FacilityEntity facility;

    @OneToMany(mappedBy = "feature", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<BookableObjectEntity> bookableObjects;
}
