package dk.bec.bookanything.repository;

import dk.bec.bookanything.model.DayOpenEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DayOpenRepository extends CrudRepository<DayOpenEntity, Long> {

}
