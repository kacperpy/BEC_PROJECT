package dk.bec.bookanything.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Table
@Builder
@Entity(name = "discount_code")
@AllArgsConstructor
@NoArgsConstructor
public class DiscountCodeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String code;

    @Column
    private Integer amount;

    @ManyToOne
    private FacilityEntity facility;
}
