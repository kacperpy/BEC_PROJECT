package dk.bec.bookanything.controller;


import dk.bec.bookanything.dto.FacilityTypeCreateDto;
import dk.bec.bookanything.dto.FacilityTypeReadDto;
import dk.bec.bookanything.mapper.FacilityTypeMapper;
import dk.bec.bookanything.model.FacilityTypeEntity;
import dk.bec.bookanything.service.FacilityTypeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/facility-types")
public class FacilityTypeController {

    private final FacilityTypeService facilityTypeService;
    private final FacilityTypeMapper facilityTypeMapper;

    public FacilityTypeController(FacilityTypeService facilityTypeService, FacilityTypeMapper facilityTypeMapper) {
        this.facilityTypeService = facilityTypeService;
        this.facilityTypeMapper = facilityTypeMapper;
    }

    @GetMapping("/")
    public List<FacilityTypeReadDto> facilityTypes(){
        return facilityTypeService.getFacilityTypes().stream().map(facilityTypeMapper::mapFacilityTypeEntityToDto).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FacilityTypeReadDto> getFacilityTypesById(@PathVariable("id")Long id){
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
    public ResponseEntity<FacilityTypeCreateDto> addFacilityType(@Valid @RequestBody FacilityTypeCreateDto facilityType){
        try {
            facilityTypeService.addFacilityType(facilityTypeMapper.mapFacilityTypeDtoToEntity(facilityType, null));
            return new ResponseEntity<>(facilityType, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<FacilityTypeCreateDto> updateFacilityTypes(@PathVariable("id") Long id, @Valid @RequestBody FacilityTypeCreateDto facilityType){
        try {
            facilityTypeService.updateFacilityType(id, facilityTypeMapper.mapFacilityTypeDtoToEntity(facilityType, id));
            return new ResponseEntity<>(facilityType, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }






}
