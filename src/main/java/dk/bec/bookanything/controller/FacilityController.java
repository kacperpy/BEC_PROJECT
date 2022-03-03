package dk.bec.bookanything.controller;

import dk.bec.bookanything.dto.*;
import dk.bec.bookanything.mapper.DiscountCodeMapper;
import dk.bec.bookanything.mapper.FacilityMapper;
import dk.bec.bookanything.model.AddressEntity;
import dk.bec.bookanything.model.FacilityEntity;
import dk.bec.bookanything.model.FacilityTypeEntity;
import dk.bec.bookanything.service.AddressService;
import dk.bec.bookanything.service.FacilityService;
import dk.bec.bookanything.service.FacilityTypeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/facilities")
@Validated
public class FacilityController {

    private final FacilityService facilityService;
    private final FacilityMapper facilityMapper;
    private final FacilityTypeService facilityTypeService;
    private final AddressService addressService;
    private final DiscountCodeMapper discountCodeMapper;

    public FacilityController(FacilityService facilityService, FacilityMapper facilityMapper, FacilityTypeService facilityTypeService, AddressService addressService, DiscountCodeMapper discountCodeMapper) {
        this.facilityService = facilityService;
        this.facilityMapper = facilityMapper;
        this.facilityTypeService = facilityTypeService;
        this.addressService = addressService;
        this.discountCodeMapper = discountCodeMapper;
    }

    @GetMapping("/{id}")
    ResponseEntity<FacilityReadDto> getFacility(@PathVariable("id") Long id) {
        return facilityService.getFacilityById(id).map(facilityEntity -> new ResponseEntity<>(facilityMapper.mapFacilityEntityToReadDto(facilityEntity), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/{id}/features")
    ResponseEntity<List<FeatureReadDto>> getFeaturesForFacility(@PathVariable("id") Long id) {
        return facilityService.getFeaturesForFacility(id).map(featureReadDtos -> new ResponseEntity<>(featureReadDtos, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/{id}/days_open")
    ResponseEntity<List<DayOpenReadDto>> getDaysOpenForFacility(@PathVariable("id") Long id) {
        return facilityService.getDaysOpenForFacility(id).map(dayOpenReadDtos -> new ResponseEntity<>(dayOpenReadDtos, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/")
    ResponseEntity<FacilityReadDto> createFacility(@Valid @RequestBody FacilityCreateDto facilityDto) {
        FacilityEntity facility = facilityMapper.mapFacilityCreateDtoToEntity(facilityDto, null);
        Optional<FacilityEntity> facilityOptional = facilityService.createFacility(facility);

        return facilityOptional.map(facilityEntity -> new ResponseEntity<>(facilityMapper.mapFacilityEntityToReadDto(facilityEntity), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));

    }

    @PutMapping("/{id}")
    ResponseEntity<FacilityReadDto> updateFacility(@PathVariable("id") Long id, @Valid @RequestBody FacilityCreateDto facilityDto) {
        FacilityEntity facility = facilityMapper.mapFacilityCreateDtoToEntity(facilityDto, id);
        Optional<FacilityEntity> facilityOptional = facilityService.updateFacility(id, facility);

        return facilityOptional.map(facilityEntity -> new ResponseEntity<>(facilityMapper.mapFacilityEntityToReadDto(facilityEntity), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<FacilityCreateDto> deleteFacility(@PathVariable("id") Long id) {
        facilityService.deleteFacilityById(id);

        return facilityService.getFacilityById(id).isPresent() ? new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR) : new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}/discount-codes")
    ResponseEntity<List<DiscountCodeReadDto>> getFacilityDiscountCodes(@PathVariable("id") Long id){
        List<DiscountCodeReadDto> discountCodeReadDtos = facilityService.getFacilityById(id).get().getDiscountCodes().stream().map(discountCodeMapper::discountCodeEntityToDto).collect(Collectors.toList());
        if(discountCodeReadDtos.size()>0)return new ResponseEntity<List<DiscountCodeReadDto>>(discountCodeReadDtos,HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @GetMapping("/")
    ResponseEntity<List<FacilityReadDto>> getFacilitiesByCityAndType(@RequestParam(name = "facilityTypeId", required = false) Long facilityTypeId,
                                                                     @RequestParam(name = "city", required = false) String city) {
        if (facilityTypeId != null && city != null) {
            Optional<FacilityTypeEntity> facilityTypeById = facilityTypeService.getFacilityTypeById(facilityTypeId);
            Optional<List<AddressEntity>> addressEntitiesByCity = addressService.getAddressesByCity(city);
            if (facilityTypeById.isPresent() && addressEntitiesByCity.isPresent()) {
                return new ResponseEntity<>(getFacilitiesByAddressEntitiesAndType(addressEntitiesByCity.get(), facilityTypeById.get()), HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (facilityTypeId != null) {
            Optional<FacilityTypeEntity> facilityTypeById = facilityTypeService.getFacilityTypeById(facilityTypeId);
            return facilityTypeById.map(facilityTypeEntity -> new ResponseEntity<>(getFacilitiesByType(facilityTypeEntity), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
        }
        if (city != null) {
            Optional<List<AddressEntity>> addressEntitiesByCity = addressService.getAddressesByCity(city);
            return addressEntitiesByCity.map(addressEntities -> new ResponseEntity<>(getFacilitiesByAddressEntities(addressEntities), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    private List<FacilityReadDto> getFacilitiesByType(FacilityTypeEntity facilityTypeEntity) {
        Optional<List<FacilityEntity>> facilitiesByType = facilityService.getFacilitiesByType(facilityTypeEntity);
        List<FacilityReadDto> facilities = new ArrayList<>();
        facilitiesByType.ifPresent(facilityEntities -> facilityEntities
                .forEach(facilityEntity -> facilities.add(facilityMapper.mapFacilityEntityToReadDto(facilityEntity))));
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
