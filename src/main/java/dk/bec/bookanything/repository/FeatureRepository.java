package dk.bec.bookanything.repository;

import dk.bec.bookanything.model.BookableObjectEntity;
import dk.bec.bookanything.model.FeatureEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FeatureRepository extends JpaRepository<FeatureEntity, Long> {
}
