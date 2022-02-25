package dk.bec.bookanything.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Table
@Entity(name = "reservation")
@Data
public class ReservationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    @GeneratedValue(generator = "uuid2")
    private UUID uuid;

    @Column(name = "date_from", nullable = false)
    private LocalDateTime dateFrom;

    @Column(name = "date_to", nullable = false)
    private LocalDateTime dateTo;

    @ManyToOne
    private BookableObjectEntity bookableObjectEntity;

    @ManyToOne
    private UserEntity userEntity;
}