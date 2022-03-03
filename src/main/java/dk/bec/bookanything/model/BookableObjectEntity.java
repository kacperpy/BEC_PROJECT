package dk.bec.bookanything.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "bookable_object")
@Data
@Table
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookableObjectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 64, nullable = false)
    private String name;

    @Column
    private Integer timePeriod;

    @Column(nullable = false)
    private Integer capacity;

    @Column(length = 256, nullable = false)
    private String description;

    @Column
    private LocalDateTime dateTime;

    @Column(nullable = false)
    private Boolean reusable;

    @Column(nullable = false)
    private Double price;

    @OneToMany(mappedBy = "bookableObjectEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<ReservationEntity> reservations;

    @ManyToOne
    private FeatureEntity feature;
}
