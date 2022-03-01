package dk.bec.bookanything.service;

import dk.bec.bookanything.model.FeatureEntity;
import dk.bec.bookanything.repository.FeatureRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FeatureService {

    private final FeatureRepository featureRepository;

    public FeatureService(FeatureRepository featureRepository) {
        this.featureRepository = featureRepository;
    }

    public FeatureEntity createFeature(FeatureEntity featureEntity){
        return featureRepository.save(featureEntity);
    }

    public Optional<FeatureEntity> getFeatureById(Long id){
        return featureRepository.findById(id);
    }

    public void deleteFeatureById(Long id){
        featureRepository.deleteById(id);
    }

    public List<FeatureEntity> getFeatures() {
        return featureRepository.findAll();
    }

    public FeatureEntity updateFeatureObject(FeatureEntity featureEntity, Long id) {
        if(getFeatureById(id).isPresent())
            featureRepository.save(featureEntity);
        return featureEntity;
    }
}
