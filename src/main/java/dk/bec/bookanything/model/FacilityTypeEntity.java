package dk.bec.bookanything.model;


import dk.bec.bookanything.service.FacilityTypeService;
import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Data
@Table
@Entity(name = "facility_type")
public class FacilityTypeEntity {

    public FacilityTypeEntity(){
        this.uuid = UUID.randomUUID();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private UUID uuid;

    @Column(length = 200)
    private String name;
}
