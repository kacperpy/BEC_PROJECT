package dk.bec.bookanything.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table
@Entity(name = "day_open")
public class DayOpenEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
