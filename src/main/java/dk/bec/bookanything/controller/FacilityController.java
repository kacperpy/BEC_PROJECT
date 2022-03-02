package dk.bec.bookanything.controller;

import dk.bec.bookanything.dto.FacilityCreateDto;
import dk.bec.bookanything.dto.FacilityReadDto;
import dk.bec.bookanything.dto.FeatureReadDto;
import dk.bec.bookanything.mapper.FacilityMapper;
import dk.bec.bookanything.model.AddressEntity;
import dk.bec.bookanything.model.FacilityEntity;
import dk.bec.bookanything.model.FacilityTypeEntity;
import dk.bec.bookanything.service.AddressService;
import dk.bec.bookanything.service.FacilityService;
import dk.bec.bookanything.service.FacilityTypeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class FacilityController {

    private final FacilityService facilityService;
    private final FacilityMapper facilityMapper;
    private final FacilityTypeService facilityTypeService;
    private final AddressService addressService;

    public FacilityController(FacilityService facilityService, FacilityMapper facilityMapper, FacilityTypeService facilityTypeService, AddressService addressService) {
        this.facilityService = facilityService;
        this.facilityMapper = facilityMapper;
        this.facilityTypeService = facilityTypeService;
        this.addressService = addressService;
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

    @GetMapping("/facilities")
    ResponseEntity<List<FacilityReadDto>> getFacilitiesByCityAndType(@RequestParam(name = "facilityTypeId", required = false) Long facilityTypeId,
                                                              @RequestParam(name = "city", required = false) String city) {
        if (facilityTypeId != null && city != null) {
            Optional<FacilityTypeEntity> facilityTypeById = Optional.ofNullable(facilityTypeService.getFacilityTypeById(facilityTypeId));
            Optional<List<AddressEntity>> addressEntitiesByCity = addressService.getAddressesByCity(city);
            if (facilityTypeById.isPresent() && addressEntitiesByCity.isPresent()) {
                return new ResponseEntity<>(getFacilitiesByAddressEntitiesAndType(addressEntitiesByCity.get(), facilityTypeById.get()), HttpStatus.OK);
            }
        }
        if (facilityTypeId != null) {
            Optional<FacilityTypeEntity> facilityTypeById = Optional.ofNullable(facilityTypeService.getFacilityTypeById(facilityTypeId));
            if (facilityTypeById.isPresent()) {
                return new ResponseEntity<>(getFacilitiesByType(facilityTypeById.get()), HttpStatus.OK);
            }
        }
        if (city != null) {
            Optional<List<AddressEntity>> addressEntitiesByCity = addressService.getAddressesByCity(city);
            if (addressEntitiesByCity.isPresent()) {
                return new ResponseEntity<>(getFacilitiesByAddressEntities(addressEntitiesByCity.get()), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    private List<FacilityReadDto> getFacilitiesByType(FacilityTypeEntity facilityTypeEntity) {
        Optional<List<FacilityEntity>> facilitiesByType = facilityService.getFacilitiesByType(facilityTypeEntity);
        List<FacilityReadDto> facilities = new ArrayList<>();
        facilitiesByType.ifPresent(facilityEntities -> facilityEntities.forEach(facilityEntity -> facilities.add(facilityMapper.mapFacilityEntityToReadDto(facilityEntity))));
        return facilities;
    }

    private List<FacilityReadDto> getFacilitiesByAddressEntities(List<AddressEntity> addressesByCity) {
        Optional<List<FacilityEntity>> facilitiesByCity = facilityService.getFacilitiesByAddressIn(addressesByCity);
        List<FacilityReadDto> facilities = new ArrayList<>();
        facilitiesByCity.ifPresent(facilityEntities -> facilityEntities.forEach(facilityEntity -> facilities.add(facilityMapper.mapFacilityEntityToReadDto(facilityEntity))));
        return facilities;
    }

    private List<FacilityReadDto> getFacilitiesByAddressEntitiesAndType(List<AddressEntity> addressesByCity, FacilityTypeEntity facilityTypeEntity) {
        Optional<List<FacilityEntity>> facilitiesByAddressInAndType = facilityService.getFacilitiesByAddressInAndType(addressesByCity, facilityTypeEntity);
        List<FacilityReadDto> facilities = new ArrayList<>();
        facilitiesByAddressInAndType.ifPresent(facilityEntities -> facilityEntities.forEach(facilityEntity -> facilities.add(facilityMapper.mapFacilityEntityToReadDto(facilityEntity))));
        return facilities;
    }
}
