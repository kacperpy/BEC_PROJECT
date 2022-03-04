package dk.bec.bookanything.service;


import dk.bec.bookanything.model.DiscountCodeEntity;
import dk.bec.bookanything.repository.DiscountCodeRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class DiscountCodeService {

    private final DiscountCodeRepository discountCodeRepository;

    public DiscountCodeService(DiscountCodeRepository discountCodeRepository) {
        this.discountCodeRepository = discountCodeRepository;
    }


    public List<DiscountCodeEntity> getDiscountCodes() {
        return discountCodeRepository.findAll();
    }

    public Optional<DiscountCodeEntity> getDiscountCode(Long id) {
        return Optional.of(discountCodeRepository.findDiscountCodeEntityById(id));
    }

    public Optional<DiscountCodeEntity> createDiscountCode(DiscountCodeEntity discountCodeEntity) {
        return Optional.of(discountCodeRepository.save(discountCodeEntity));
    }


    public void deleteDiscountCode(Long id) {
        Optional<DiscountCodeEntity> discountCodeEntity = getDiscountCode(id);
        discountCodeEntity.ifPresent(discountCodeRepository::delete);
    }

    public void updateDiscountCode(Long id, DiscountCodeEntity discountCode) {
        Optional<DiscountCodeEntity> discountCodeEntity = getDiscountCode(id);
        discountCodeEntity.ifPresent(discountCodeEntity1 -> discountCodeRepository.save(discountCode));
    }
}
