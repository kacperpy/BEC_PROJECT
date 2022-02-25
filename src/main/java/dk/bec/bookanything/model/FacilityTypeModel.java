package dk.bec.bookanything.model;


import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Data
@Table
@Entity(name = "facility_type")
public class FacilityTypeModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    @GeneratedValue(generator = "uuid2")
    private UUID uuid;

    @Column(length = 200)
    private String name;
}
