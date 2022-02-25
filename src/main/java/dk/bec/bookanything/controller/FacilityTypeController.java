package dk.bec.bookanything.controller;


import dk.bec.bookanything.model.FacilityTypeEntity;
import dk.bec.bookanything.service.FacilityTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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

    @GetMapping("/facility-types/{uuid}")
    public FacilityTypeEntity getFacilityTypesByUuid(@PathVariable("uuid")UUID uuid){
      return facilityTypeService.getFacilityTypeByUUID(uuid);
    }

    @DeleteMapping("/facility-types")
    public void deleteFacilityType(@RequestBody FacilityTypeEntity facilityType){
        facilityTypeService.deleteFacilityType(facilityType);
    }

    @DeleteMapping("/facility-types/{uuid}")
    public void deleteFacilityType(@PathVariable("uuid") UUID uuid){
        facilityTypeService.deleteFacilityTypeByUuid(uuid);
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

    @PutMapping("/facility-types/{uuid}")
    public ResponseEntity<FacilityTypeEntity> updateFacilityTypes(@PathVariable("uuid") UUID uuid, @RequestBody FacilityTypeEntity facilityType){
        try {
            facilityTypeService.updateFacilityType(uuid, facilityType);
            return new ResponseEntity<>(facilityType, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }






}
