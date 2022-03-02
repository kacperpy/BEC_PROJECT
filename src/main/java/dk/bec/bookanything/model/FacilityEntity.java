package dk.bec.bookanything.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

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

    @OneToMany(mappedBy = "facility", cascade =  CascadeType.ALL)
    @JsonManagedReference
    private List<DayOpenEntity> dayOpenList;

    @OneToMany(cascade =  CascadeType.ALL)
    @JsonManagedReference
    private List<DiscountCodeEntity> discountCodes;

    @OneToOne(cascade = CascadeType.ALL)
    private FacilityTypeEntity facilityTypeEntity;

    @OneToMany(mappedBy = "facility", cascade =  CascadeType.ALL)
    @JsonManagedReference
    private List<FeatureEntity> featureEntities;


}
