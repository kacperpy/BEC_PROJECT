package dk.bec.bookanything.repository.springData;

import dk.bec.bookanything.model.AddressEntity;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<AddressEntity, Long> {

}
