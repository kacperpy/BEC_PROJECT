package dk.bec.bookanything.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = "bookable_object")
public class BookableObjectEntity {

    public BookableObjectEntity() {
        this.uuid = UUID.randomUUID();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private UUID uuid;

    @Column(length = 64, nullable = false)
    private String name;

    @Column(nullable = true)
    private Integer time_period;

    @Column(nullable = false)
    private Integer capacity;

    @Column(length = 256, nullable = false)
    private String description;

    @Column(nullable = true)
    private LocalDateTime date_time;

    @OneToMany
    private List<ReservationEntity> reservations;

    @ManyToOne
    private FeatureEntity feature;
}
