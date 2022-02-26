package dk.bec.bookanything.service;


import dk.bec.bookanything.model.DiscountCodeEntity;
import dk.bec.bookanything.repository.DiscountCodeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional
public class DiscountCodeService {

    private final DiscountCodeRepository discountCodeRepository;

    public List<DiscountCodeEntity> getDiscountCodes() {
        return discountCodeRepository.findAll();
    }

    public Optional<DiscountCodeEntity> getDiscountCode(String uuid) {
        return Optional.of(discountCodeRepository.findDiscountCodeEntityByUuid(UUID.fromString(uuid)));
    }

    public Optional<DiscountCodeEntity> createDiscountCode(DiscountCodeEntity discountCodeEntity) {
        return Optional.of(discountCodeRepository.save(discountCodeEntity));
    }


    public void deleteDiscountCode(String uuid) {
        discountCodeRepository.deleteDiscountCodeEntityByUuid(UUID.fromString(uuid));
    }

    public Optional<DiscountCodeEntity> updateDiscountCode(DiscountCodeEntity discountCode){
      DiscountCodeEntity discountCodeEntity = discountCodeRepository.findDiscountCodeEntityByUuid(discountCode.getUuid());
      discountCodeEntity.setCode(discountCode.getCode());
      discountCodeEntity.setAmount(discountCode.getAmount());
      discountCodeEntity.setFacility(discountCode.getFacility());
      return Optional.of(discountCodeRepository.save(discountCodeEntity));

    }
}
