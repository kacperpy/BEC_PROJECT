package dk.bec.bookanything.repository.springData;

import dk.bec.bookanything.model.FeatureEntity;
import org.springframework.data.repository.CrudRepository;

public interface FeatureRepository extends CrudRepository<FeatureEntity, Long> {
}
