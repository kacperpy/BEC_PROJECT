package dk.bec.bookanything.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Table
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "facility_type")
public class FacilityTypeEntity {

    public FacilityTypeEntity(String name){
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 200)
    private String name;

}
