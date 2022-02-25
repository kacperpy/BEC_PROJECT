package dk.bec.bookanything.model;

import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Data
@Table
@Entity(name = "discount_code")
public class DiscountCodeEntity {

    public DiscountCodeEntity() {
        this.uuid = UUID.randomUUID();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private UUID uuid;

    @Column
    private String code;

    @Column
    private Integer amount;

    @ManyToOne
    private FacilityEntity facility;
}
