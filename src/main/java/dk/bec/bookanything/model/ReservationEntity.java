package dk.bec.bookanything.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Table
@Entity(name = "reservation")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "date_from", nullable = false)
    private LocalDateTime dateFrom;

    @Column(name = "date_to", nullable = false)
    private LocalDateTime dateTo;

    @Column(name = "people_number", nullable = false)
    private int peopleNumber;

    @ManyToOne
    @JoinColumn(name = "bookable_object_id", referencedColumnName = "id")
    @JsonBackReference
    private BookableObjectEntity bookableObjectEntity;

    @ManyToOne
    private UserEntity userEntity;

    public ReservationEntity(LocalDateTime dateFrom, LocalDateTime dateTo, BookableObjectEntity bookableObjectEntity){
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.bookableObjectEntity = bookableObjectEntity;
    }
}
