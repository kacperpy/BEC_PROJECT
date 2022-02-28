package dk.bec.bookanything.dto;

import dk.bec.bookanything.model.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import java.util.List;

@Validated
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FacilityCreateDto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    String nip;

    @Column(nullable = false)
    String krs;

    @Column(nullable = false)
    private AddressDto addressEntity;

    @Column(nullable = false)
    private FacilityTypeEntity facilityTypeEntity;

}
