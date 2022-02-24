package dk.bec.bookanything.model;


import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Table
@Entity(name = "day_open")
public class DayOpenModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String day;

    @Column
    private LocalDateTime hour_from;

    @Column
    private LocalDateTime hour_to;

}
