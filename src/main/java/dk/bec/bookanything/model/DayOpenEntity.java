package dk.bec.bookanything.model;


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

    @Column
    private int day;

    @Column
    private LocalDateTime hourFrom;

    @Column
    private LocalDateTime hourTo;

    @ManyToOne
    private FacilityEntity facility;
}
