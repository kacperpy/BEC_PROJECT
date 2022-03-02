package dk.bec.bookanything.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;

@Data
@Table
@Entity(name = "discount_code")
public class DiscountCodeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String code;

    @Column
    private Integer amount;

    @ManyToOne
    @JsonBackReference
    private FacilityEntity facility;
}
