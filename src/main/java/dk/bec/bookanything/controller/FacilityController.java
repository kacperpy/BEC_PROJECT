package dk.bec.bookanything.controller;

import dk.bec.bookanything.dto.FacilityCreateDto;
import dk.bec.bookanything.dto.FacilityCreateDto;
import dk.bec.bookanything.model.FacilityEntity;
import dk.bec.bookanything.service.FacilityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class FacilityController {

    private final FacilityService facilityService;

    public FacilityController(FacilityService facilityService) {
        this.facilityService = facilityService;
    }

    @GetMapping("/facilities/{id}")
    ResponseEntity<FacilityCreateDto> getFacility(@PathVariable("id") Long id) {
        Optional<FacilityEntity> facilityOptional = facilityService.getFacilityById(id);
        Optional<FacilityCreateDto> facilityDtoOptional = facilityOptional.map(this::createFacilityDto);

        return facilityDtoOptional.map(facilityDto -> new ResponseEntity<>(facilityDto, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/facilities")
    ResponseEntity<FacilityCreateDto> createFacility(@RequestBody FacilityCreateDto facilityDto) {
        FacilityEntity facility = createFacilityEntity(facilityDto);
        Optional<FacilityEntity> facilityOptional = facilityService.createFacility(facility);

        return facilityOptional.map(facilityEntity -> new ResponseEntity<>(createFacilityDto(facilityEntity), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));

    }

    @PutMapping("/facilities/{id}")
    ResponseEntity<FacilityCreateDto> updateFacility(@PathVariable("id") Long id, @RequestBody FacilityCreateDto facilityDto) {
        FacilityEntity facility = createFacilityEntity(facilityDto);
        Optional<FacilityEntity> facilityOptional = facilityService.updateFacility(id, facility);

        return facilityOptional.map(facilityEntity -> new ResponseEntity<>(createFacilityDto(facilityEntity), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @DeleteMapping("/facilities/{id}")
    ResponseEntity<FacilityCreateDto> deleteFacility(@PathVariable("id") Long id) {
        facilityService.deleteFacilityById(id);

        return facilityService.getFacilityById(id).isPresent() ? new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR) : new ResponseEntity<>(HttpStatus.OK);
    }

    private FacilityEntity createFacilityEntity(FacilityCreateDto dto){
        return FacilityEntity.builder().id(dto.getId())
                .addressEntity(dto.getAddressEntity())
                .facilityTypeEntity(dto.getFacilityTypeEntity())
                .name(dto.getName())
                .nip(dto.getNip())
                .krs(dto.getKrs()).build();
    }

    private FacilityCreateDto createFacilityDto(FacilityEntity facilityEntity){
        return FacilityCreateDto.builder().id(facilityEntity.getId())
                .addressEntity(facilityEntity.getAddressEntity())
                .facilityTypeEntity(facilityEntity.getFacilityTypeEntity())
                .name(facilityEntity.getName())
                .nip(facilityEntity.getNip())
                .krs(facilityEntity.getKrs()).build();
    }


}
