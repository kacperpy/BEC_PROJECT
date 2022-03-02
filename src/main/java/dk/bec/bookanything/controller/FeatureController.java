package dk.bec.bookanything.controller;


import dk.bec.bookanything.dto.BookableObjectReadDto;
import dk.bec.bookanything.dto.FeatureCreateDto;
import dk.bec.bookanything.dto.FeatureReadDto;
import dk.bec.bookanything.mapper.FeatureMapper;
import dk.bec.bookanything.model.FeatureEntity;
import dk.bec.bookanything.service.FeatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class FeatureController {

    private final FeatureMapper featureMapper;
    private final FeatureService featureService;

    @Autowired
    FeatureController(FeatureMapper featureMapper, FeatureService featureService) {
        this.featureMapper = featureMapper;
        this.featureService = featureService;
    }

    @GetMapping("/features")
    public ResponseEntity<List<FeatureEntity>> getAllFeatures(){
        return ResponseEntity.ok()
                .body(featureService.getFeatures());
    }

    @GetMapping("/features/{id}")
    public ResponseEntity<FeatureReadDto> getFeatureById(@PathVariable("id") Long id){
        Optional<FeatureEntity> featureOptional = featureService.getFeatureById(id);
        return featureOptional.map(featureEntity -> new ResponseEntity<>(featureMapper.mapFeatureEntityToDto(featureEntity), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/features")
    public ResponseEntity<FeatureCreateDto> addFeature(@RequestBody FeatureCreateDto feature) {
        try {
            featureService.createFeature(featureMapper.mapFeatureDtoToEntity(feature, null ));
            return new ResponseEntity<>(feature, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/features/{id}")
    public ResponseEntity<FeatureCreateDto> updateFeature(@RequestBody FeatureCreateDto feature, @PathVariable("id") Long id) {
        try {
            featureService.updateFeatureObject(featureMapper.mapFeatureDtoToEntity(feature, id), id);
            return new ResponseEntity<>(feature, HttpStatus.OK); //zwroc dto
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/features/{id}")
    public void deleteFeatureById(@PathVariable("id") Long id){
        featureService.deleteFeatureById(id);
    }

    @GetMapping("/features/{id}/bookable-objects")
    public ResponseEntity<List<BookableObjectReadDto>> getBookableObjectsForFeature(
            @PathVariable("id") Long id,
            @RequestParam(name = "from", required = false) String from,
            @RequestParam(name = "to", required = false) String to,
            @RequestParam(name = "people_amount", required = false) String people_amount
    ){

        if (from != null && to != null && people_amount != null) {
            LocalDateTime requestFrom = LocalDateTime.parse(from);
            LocalDateTime requestTo = LocalDateTime.parse(to);
            int requestPeopleAmount = Integer.parseInt(people_amount);
        }

        return ResponseEntity.ok().body(
                featureService.getBookableForFeatureId(id)
        );
    }

}

