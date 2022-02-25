package dk.bec.bookanything.repository;

import dk.bec.bookanything.model.DiscountCodeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscountCodeRepository extends CrudRepository<DiscountCodeEntity, Long> {

}
