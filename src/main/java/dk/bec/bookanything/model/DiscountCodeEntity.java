package dk.bec.bookanything.model;

import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Data
@Table
@Entity(name = "discount_code")
public class DiscountCodeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    @GeneratedValue(generator = "uuid2")
    private UUID uuid;

    @Column
    private String code;

    @Column
    private Integer amount;

    @ManyToOne
    private FacilityEntity facility;
}
