package dk.bec.bookanything.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Data
@Table
@Entity(name="facility")
public class FacilityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    String nip;

    @Column(nullable = false)
    String krs;

    @OneToOne(cascade = CascadeType.ALL)
    private AddressEntity addressEntity;

    @OneToMany(cascade =  CascadeType.ALL)
    private List<DayOpenEntity> dayOpenList;

    @OneToMany(cascade =  CascadeType.ALL)
    private List<DiscountCodeEntity> discountCodeList;

    @OneToOne(cascade = CascadeType.ALL)
    private FacilityTypeEntity facilityTypeEntity;

    @OneToMany(cascade =  CascadeType.ALL)
    private List<FeatureEntity> featureEntity;


}
