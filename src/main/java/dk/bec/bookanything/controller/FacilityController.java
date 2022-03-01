package dk.bec.bookanything.controller;

import dk.bec.bookanything.dto.FacilityCreateDto;
import dk.bec.bookanything.dto.FacilityReadDto;
import dk.bec.bookanything.dto.FeatureReadDto;
import dk.bec.bookanything.mapper.FacilityMapper;
import dk.bec.bookanything.model.FacilityEntity;
import dk.bec.bookanything.service.FacilityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class FacilityController {

    private final FacilityService facilityService;
    private final FacilityMapper facilityMapper;

    public FacilityController(FacilityService facilityService, FacilityMapper facilityMapper) {
        this.facilityService = facilityService;
        this.facilityMapper = facilityMapper;
    }

    @GetMapping("/facilities/{id}")
    ResponseEntity<FacilityReadDto> getFacility(@PathVariable("id") Long id) {
        Optional<FacilityEntity> facilityOptional = facilityService.getFacilityById(id);

        return facilityOptional.map(facilityEntity -> new ResponseEntity<>(facilityMapper.mapFacilityEntityToReadDto(facilityEntity), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/facilities/{id}/features")
    ResponseEntity<List<FeatureReadDto>> getFeaturesForFacility(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(
                facilityService.getFeaturesForFacility(id)
        );
    }

    @PostMapping("/facilities")
    ResponseEntity<FacilityReadDto> createFacility(@Valid @RequestBody FacilityCreateDto facilityDto) {
        FacilityEntity facility = facilityMapper.mapFacilityCreateDtoToEntity(facilityDto, null);
        Optional<FacilityEntity> facilityOptional = facilityService.createFacility(facility);

        return facilityOptional.map(facilityEntity -> new ResponseEntity<>(facilityMapper.mapFacilityEntityToReadDto(facilityEntity), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));

    }

    @PutMapping("/facilities/{id}")
    ResponseEntity<FacilityReadDto> updateFacility(@PathVariable("id") Long id, @RequestBody FacilityCreateDto facilityDto) {
        FacilityEntity facility = facilityMapper.mapFacilityCreateDtoToEntity(facilityDto, id);
        Optional<FacilityEntity> facilityOptional = facilityService.updateFacility(id, facility);

        return facilityOptional.map(facilityEntity -> new ResponseEntity<>(facilityMapper.mapFacilityEntityToReadDto(facilityEntity), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @DeleteMapping("/facilities/{id}")
    ResponseEntity<FacilityCreateDto> deleteFacility(@PathVariable("id") Long id) {
        facilityService.deleteFacilityById(id);

        return facilityService.getFacilityById(id).isPresent() ? new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR) : new ResponseEntity<>(HttpStatus.OK);
    }
}
