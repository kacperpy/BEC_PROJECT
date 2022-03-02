package dk.bec.bookanything.repository;

import dk.bec.bookanything.model.DayOpenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DayOpenRepository extends JpaRepository<DayOpenEntity, Long> {

}
