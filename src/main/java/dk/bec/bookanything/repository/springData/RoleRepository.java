package dk.bec.bookanything.repository.springData;

import dk.bec.bookanything.model.ReservationEntity;
import dk.bec.bookanything.model.RoleEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<RoleEntity, Long> {
}
