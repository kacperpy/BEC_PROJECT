package dk.bec.bookanything.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Data
@Table
@Entity(name="facility")
public class FacilityModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column
    @GeneratedValue(generator = "uuid2")
    private UUID uuid;

    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    String nip;

    @Column(nullable = false)
    String krs;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private AddressModel addressModel;

    @OneToMany(cascade =  CascadeType.ALL)
    private List<DayOpenModel> dayOpenList;

    @OneToMany(cascade =  CascadeType.ALL)
    private List<DiscountCodeModel> discountCodeList;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "facility_type_id")
    private FacilityTypeModel facilityTypeModel;

    @OneToMany(cascade =  CascadeType.ALL)
    private List<FeatureModel> featureModel;


}
