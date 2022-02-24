package dk.bec.bookanything.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Table
@Entity(name = "discount_code")
public class DiscountCodeModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String code;

    @Column
    private Integer amount;
}
