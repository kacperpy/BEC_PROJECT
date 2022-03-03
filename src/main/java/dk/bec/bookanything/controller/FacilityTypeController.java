package dk.bec.bookanything.controller;


import dk.bec.bookanything.dto.FacilityTypeDto;
import dk.bec.bookanything.mapper.FacilityTypeMapper;
import dk.bec.bookanything.model.FacilityTypeEntity;
import dk.bec.bookanything.service.FacilityTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/facility-types")
@RequiredArgsConstructor
public class FacilityTypeController {

    private final FacilityTypeService facilityTypeService;
    private final FacilityTypeMapper facilityTypeMapper;

    @GetMapping("/")
    public List<FacilityTypeEntity> facilityTypes(){
        return facilityTypeService.getFacilityTypes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FacilityTypeDto> getFacilityTypesById(@PathVariable("id")Long id){
        Optional<FacilityTypeEntity> facilityTypeEntity = facilityTypeService.getFacilityTypeById(id);
       return facilityTypeEntity.map(typeEntity -> new ResponseEntity<>(facilityTypeMapper.mapFacilityTypeEntityToDto(typeEntity), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    @DeleteMapping("/")
    public void deleteFacilityType(@RequestBody FacilityTypeEntity facilityType){
        facilityTypeService.deleteFacilityType(facilityType);
    }

    @DeleteMapping("/{id}")
    public void deleteFacilityType(@PathVariable("id") Long id){
        facilityTypeService.deleteFacilityTypeById(id);
    }

    @PostMapping("/")
    public ResponseEntity<FacilityTypeDto> addFacilityType(@Valid @RequestBody FacilityTypeDto facilityType){
        try {
            facilityTypeService.addFacilityType(facilityTypeMapper.mapFacilityTypeDtoToEntity(facilityType, null));
            return new ResponseEntity<>(facilityType, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<FacilityTypeDto> updateFacilityTypes(@PathVariable("id") Long id, @Valid @RequestBody FacilityTypeDto facilityType){
        try {
            facilityTypeService.updateFacilityType(id, facilityTypeMapper.mapFacilityTypeDtoToEntity(facilityType, id));
            return new ResponseEntity<>(facilityType, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }






}
