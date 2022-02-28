package dk.bec.bookanything.controller;


import dk.bec.bookanything.model.FacilityTypeEntity;
import dk.bec.bookanything.service.FacilityTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/API")
public class FacilityTypeController {

    private FacilityTypeService facilityTypeService;

    @Autowired
    public FacilityTypeController(FacilityTypeService facilityTypeService){
        this.facilityTypeService = facilityTypeService;
    }

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
    public ResponseEntity<FacilityTypeEntity> addFacilityType(@RequestBody FacilityTypeEntity facilityType){
        try {
            facilityTypeService.addFacilityType(facilityType);
            return new ResponseEntity<>(facilityType, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/facility-types/{id}")
    public ResponseEntity<FacilityTypeEntity> updateFacilityTypes(@PathVariable("id") Long id, @RequestBody FacilityTypeEntity facilityType){
        try {
            facilityTypeService.updateFacilityType(id, facilityType);
            return new ResponseEntity<>(facilityType, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }






}
