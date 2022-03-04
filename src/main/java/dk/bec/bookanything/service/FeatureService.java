package dk.bec.bookanything.service;

import dk.bec.bookanything.dto.BookableObjectReadDto;
import dk.bec.bookanything.mapper.BookableObjectMapper;
import dk.bec.bookanything.model.BookableObjectEntity;
import dk.bec.bookanything.model.FeatureEntity;
import dk.bec.bookanything.model.ReservationEntity;
import dk.bec.bookanything.repository.FeatureRepository;
import dk.bec.bookanything.utils.TimeUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FeatureService {

    private final FeatureRepository featureRepository;
    private final BookableObjectMapper bookableObjectMapper;
    private final TimeUtils timeUtils;

    public FeatureService(FeatureRepository featureRepository, BookableObjectMapper bookableObjectMapper, TimeUtils timeUtils) {
        this.featureRepository = featureRepository;
        this.bookableObjectMapper = bookableObjectMapper;
        this.timeUtils = timeUtils;
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

    public List<BookableObjectReadDto> getFilteredBookableObjectsForFeatureId(Long id, LocalDateTime from, LocalDateTime to, int people_amount) {

        List<BookableObjectEntity> filtered = featureRepository.findById(id).get().getBookableObjects().stream().filter(
                bo -> bo.getCapacity() >= people_amount &&
                        (bo.getDateTime() == null ||
                        ((timeUtils.isEqualOrAfter(bo.getDateTime(), from)) &&
                                (timeUtils.isEqualOrBefore(bo.getDateTime(), to))))
        ).collect(Collectors.toList());

        List<Long> idsToDelete = filtered.stream().filter(bo -> bo.getReservations().stream().anyMatch(
                r -> (timeUtils.isEqualOrAfter(from, r.getDateFrom())) &&
                        (timeUtils.isEqualOrBefore(to, r.getDateTo()))
        ) && !bo.getReusable()).map(BookableObjectEntity::getId).collect(Collectors.toList());

        for(BookableObjectEntity bo : filtered) {
            if (bo.getReservations().stream().anyMatch(
                    r -> (timeUtils.isEqualOrAfter(from, r.getDateFrom())) &&
                            (timeUtils.isEqualOrBefore(to, r.getDateTo()))
            ) && bo.getReusable()) {
                if (bo.getReservations().stream().map(ReservationEntity::getPeopleNumber).count() >= bo.getCapacity()) {
                    idsToDelete.add(bo.getId());
                }
            }
        }

        filtered = filtered.stream().filter(
                bo -> !idsToDelete.contains(bo.getId())
        ).collect(Collectors.toList());

        return filtered.stream().map(bookableObjectMapper::mapEntityToDto).collect(Collectors.toList());
    }

    public FeatureEntity updateFeatureObject(FeatureEntity featureEntity, Long id) {
        if(getFeatureById(id).isPresent())
            featureRepository.save(featureEntity);
        return featureEntity;
    }

    public List<BookableObjectReadDto> getBookableForFeatureId(Long id) {
        return featureRepository.findById(id).get().getBookableObjects().stream().map(
                bookableObjectMapper::mapEntityToDto
        ).collect(Collectors.toList());
    }
}
