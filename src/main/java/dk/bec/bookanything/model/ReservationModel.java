package dk.bec.bookanything.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Table
@Entity(name = "reservation_model")
@Data
public class ReservationModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "date_from", nullable = false)
    private LocalDateTime dateFrom;

    @Column(name = "date_to", nullable = false)
    private LocalDateTime dateTo;
}
