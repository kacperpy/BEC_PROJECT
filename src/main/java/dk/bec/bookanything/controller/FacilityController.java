package dk.bec.bookanything.controller;

import dk.bec.bookanything.dto.AddressDto;
import dk.bec.bookanything.dto.FacilityCreateDto;
import dk.bec.bookanything.dto.FacilityReadDto;
import dk.bec.bookanything.model.AddressEntity;
import dk.bec.bookanything.model.FacilityEntity;
import dk.bec.bookanything.service.AddressService;
import dk.bec.bookanything.service.FacilityService;
import dk.bec.bookanything.service.FacilityTypeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class FacilityController {

    private final FacilityService facilityService;
    private final FacilityTypeService facilityTypeService;

    public FacilityController(FacilityService facilityService, FacilityTypeService facilityTypeService) {
        this.facilityService = facilityService;
        this.facilityTypeService = facilityTypeService;
    }

    @GetMapping("/facilities/{id}")
    ResponseEntity<FacilityReadDto> getFacility(@PathVariable("id") Long id) {
        Optional<FacilityEntity> facilityOptional = facilityService.getFacilityById(id);
        Optional<FacilityReadDto> facilityDtoOptional = facilityOptional.map(this::createFacilityDto);

        return facilityDtoOptional.map(facilityDto -> new ResponseEntity<>(facilityDto, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/facilities")
    ResponseEntity<FacilityReadDto> createFacility(@Valid @RequestBody FacilityCreateDto facilityDto) {
        FacilityEntity facility = createFacilityEntity(null, facilityDto);
        Optional<FacilityEntity> facilityOptional = facilityService.createFacility(facility);

        return facilityOptional.map(facilityEntity -> new ResponseEntity<>(createFacilityDto(facilityEntity), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));

    }

    @PutMapping("/facilities/{id}")
    ResponseEntity<FacilityReadDto> updateFacility(@PathVariable("id") Long id, @RequestBody FacilityCreateDto facilityDto) {
        FacilityEntity facility = createFacilityEntity(id, facilityDto);
        Optional<FacilityEntity> facilityOptional = facilityService.updateFacility(id, facility);

        return facilityOptional.map(facilityEntity -> new ResponseEntity<>(createFacilityDto(facilityEntity), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @DeleteMapping("/facilities/{id}")
    ResponseEntity<FacilityCreateDto> deleteFacility(@PathVariable("id") Long id) {
        facilityService.deleteFacilityById(id);

        return facilityService.getFacilityById(id).isPresent() ? new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR) : new ResponseEntity<>(HttpStatus.OK);
    }

    private FacilityEntity createFacilityEntity(Long id, FacilityCreateDto dto){
        return FacilityEntity.builder().id(id)
                .addressEntity(new AddressEntity(dto.getAddressDto()))
                .facilityTypeEntity(facilityTypeService.getFacilityTypeById(dto.getFacilityTypeId()))
                .name(dto.getName())
                .nip(dto.getNip())
                .krs(dto.getKrs()).build();
    }

    private FacilityReadDto createFacilityDto(FacilityEntity facilityEntity){
        return FacilityReadDto.builder()
                .addressDto(new AddressDto(facilityEntity.getAddressEntity()))
                .facilityTypeEntity(facilityEntity.getFacilityTypeEntity())
                .featureEntities(facilityEntity.getFeatureEntities()
                )
                .name(facilityEntity.getName())
                .nip(facilityEntity.getNip())
                .krs(facilityEntity.getKrs()).build();
    }


}
