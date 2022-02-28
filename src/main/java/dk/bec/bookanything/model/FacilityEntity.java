package dk.bec.bookanything.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Data
@Table
@Entity(name="facility")
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
    private List<DiscountCodeEntity> discountCodes;

    @OneToOne(cascade = CascadeType.ALL)
    private FacilityTypeEntity facilityTypeEntity;

    @OneToMany(cascade =  CascadeType.ALL)
    private List<FeatureEntity> featureEntities;


}
