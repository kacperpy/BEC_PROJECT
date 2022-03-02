package dk.bec.bookanything.controller;


import dk.bec.bookanything.dto.FeatureCreateDto;
import dk.bec.bookanything.mapper.FeatureMapper;
import dk.bec.bookanything.model.FeatureEntity;
import dk.bec.bookanything.service.FeatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/feautres")
    public List<FeatureEntity> getAllFeatures(){
        return featureService.getFeatures();
    }

    @GetMapping("/feautres/{id}")
    public Optional<FeatureEntity> getFeatureById(@PathVariable("id") Long id){
        return featureService.getFeatureById(id);
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

}

