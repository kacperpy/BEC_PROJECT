package dk.bec.bookanything.controller;


import dk.bec.bookanything.dto.FacilityTypeDto;
import dk.bec.bookanything.mapper.FacilityTypeMapper;
import dk.bec.bookanything.model.FacilityTypeEntity;
import dk.bec.bookanything.service.FacilityTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/API") //TODO stick to the bloody convention bro
public class FacilityTypeController {

    private final FacilityTypeService facilityTypeService;
    private final FacilityTypeMapper facilityTypeMapper;

    @Autowired
    public FacilityTypeController(FacilityTypeService facilityTypeService,  FacilityTypeMapper facilityTypeMapper){
        this.facilityTypeService = facilityTypeService;
        this.facilityTypeMapper = facilityTypeMapper;
    }

    //TODO responses

    @GetMapping("/facility-types")
    public List<FacilityTypeEntity> facilityTypes(){
        return facilityTypeService.getFacilityTypes();
    }

    @GetMapping("/facility-types/{id}")
    public FacilityTypeEntity getFacilityTypesById(@PathVariable("id")Long id){
      return facilityTypeService.getFacilityTypeById(id);
    }

    @DeleteMapping("/facility-types")
    public void deleteFacilityType(@RequestBody FacilityTypeEntity facilityType){
        facilityTypeService.deleteFacilityType(facilityType);
    }

    @DeleteMapping("/facility-types/{id}")
    public void deleteFacilityType(@PathVariable("id") Long id){
        facilityTypeService.deleteFacilityTypeById(id);
    }

    @PostMapping("/facility-types")
    public ResponseEntity<FacilityTypeDto> addFacilityType(@Valid @RequestBody FacilityTypeDto facilityType){
        try {
            facilityTypeService.addFacilityType(facilityTypeMapper.mapFacilityTypeDtoToEntity(facilityType, null));
            return new ResponseEntity<>(facilityType, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/facility-types/{id}")
    public ResponseEntity<FacilityTypeDto> updateFacilityTypes(@PathVariable("id") Long id, @Valid @RequestBody FacilityTypeDto facilityType){
        try {
            facilityTypeService.updateFacilityType(id, facilityTypeMapper.mapFacilityTypeDtoToEntity(facilityType, id));
            return new ResponseEntity<>(facilityType, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }






}
