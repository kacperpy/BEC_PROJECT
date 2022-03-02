package dk.bec.bookanything.service;

import dk.bec.bookanything.dto.BookableObjectReadDto;
import dk.bec.bookanything.dto.FeatureReadDto;
import dk.bec.bookanything.mapper.BookableObjectMapper;
import dk.bec.bookanything.mapper.FeatureMapper;
import dk.bec.bookanything.model.FeatureEntity;
import dk.bec.bookanything.repository.FeatureRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FeatureService {

    private final FeatureRepository featureRepository;
    private final BookableObjectMapper bookableObjectMapper;
    private final FeatureMapper featureMapper;

    public FeatureService(FeatureRepository featureRepository, BookableObjectMapper bookableObjectMapper, FeatureMapper featureMapper) {
        this.featureRepository = featureRepository;
        this.bookableObjectMapper = bookableObjectMapper;
        this.featureMapper = featureMapper;
    }

    public Optional<FeatureEntity> createFeature(FeatureEntity featureEntity){
        return Optional.of(featureRepository.save(featureEntity));
    }

    public Optional<FeatureEntity> getFeatureById(Long id){
        return featureRepository.findById(id);
    }

    public void deleteFeatureById(Long id){
        featureRepository.deleteById(id);
    }

    public List<FeatureReadDto> getFeatures() {
        return featureRepository.findAll().stream()
                .map(featureMapper::mapFeatureEntityToDto).collect(Collectors.toList());
    }

    public Optional<FeatureEntity> updateFeatureObject(FeatureEntity featureEntity, Long id) {
        if(getFeatureById(id).isPresent())
            return Optional.of(featureRepository.save(featureEntity));
        else
            return Optional.empty();
    }

    public Optional<List<BookableObjectReadDto>> getBookableForFeatureId(Long id) {
            return getFeatureById(id).map(featureEntity -> featureEntity.getBookableObjects().stream()
            .map(bookableObjectMapper::mapEntityToDto).collect(Collectors.toList()));
    }
}
