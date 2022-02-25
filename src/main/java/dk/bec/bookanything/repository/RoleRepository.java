package dk.bec.bookanything.repository;

import dk.bec.bookanything.model.ReservationEntity;
import dk.bec.bookanything.model.RoleEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<RoleEntity, Long> {
}
