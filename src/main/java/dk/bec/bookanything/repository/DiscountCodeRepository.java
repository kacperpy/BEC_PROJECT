package dk.bec.bookanything.repository;

import dk.bec.bookanything.model.DiscountCodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DiscountCodeRepository extends JpaRepository<DiscountCodeEntity, Long> {

DiscountCodeEntity findDiscountCodeEntityByUuid(UUID uuid);
void deleteDiscountCodeEntityByUuid(UUID uuid);

}
