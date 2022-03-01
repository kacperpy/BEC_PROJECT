package dk.bec.bookanything.repository;

import dk.bec.bookanything.model.DiscountCodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscountCodeRepository extends JpaRepository<DiscountCodeEntity, Long> {

DiscountCodeEntity findDiscountCodeEntityById(Long id);
void deleteDiscountCodeEntityById(Long id);

}
