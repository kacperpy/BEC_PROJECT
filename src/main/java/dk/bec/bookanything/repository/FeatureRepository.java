package dk.bec.bookanything.repository;

import dk.bec.bookanything.model.FeatureEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeatureRepository extends CrudRepository<FeatureEntity, Long> {
}
