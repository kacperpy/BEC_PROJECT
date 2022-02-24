package dk.bec.bookanything.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Table(name = "bookable_object")
public class BookableObjectModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    @GeneratedValue(generator = "uuid2")
    private UUID uuid;

    @Column(name = "name", length = 64, nullable = false)
    private String name;

    @Column(name = "time_period", nullable = true)
    private Integer time_period;

    @Column(name = "capacity", nullable = false)
    private Integer capacity;

    @Column(name = "description", length = 256, nullable = false)
    private String description;

    @Column(name = "date_time", nullable = true)
    private LocalDateTime date_time;
}
