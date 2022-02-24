package dk.bec.bookanything.model;


import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Table
@Entity(name = "day_open")
public class DayOpenModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    @GeneratedValue(generator = "uuid2")
    private UUID uuid;

    @Column
    private String day;

    @Column
    private LocalDateTime hour_from;

    @Column
    private LocalDateTime hour_to;

}
