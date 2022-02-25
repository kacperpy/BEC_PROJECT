package dk.bec.bookanything.repository.springData;

import dk.bec.bookanything.model.DiscountCodeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscountCodeRepository extends CrudRepository<DiscountCodeEntity, Long> {

}
