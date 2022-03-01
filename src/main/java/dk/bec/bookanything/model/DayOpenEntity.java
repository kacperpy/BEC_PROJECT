package dk.bec.bookanything.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table
@Entity(name = "day_open")
public class DayOpenEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private Integer day;

    @Column(nullable = false)
    private LocalDateTime hourFrom;

    @Column(nullable = false)
    private LocalDateTime hourTo;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "facility_id", referencedColumnName = "id")
    private FacilityEntity facility;
}
