package dk.bec.bookanything.service;

import dk.bec.bookanything.model.DiscountCodeEntity;
import dk.bec.bookanything.model.ReservationEntity;
import dk.bec.bookanything.repository.ReservationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;

    public List<ReservationEntity> getReservations() {
        return reservationRepository.findAll();
    }

    public Optional<ReservationEntity> getReservationByUUId(UUID uuid) { return reservationRepository.findByUuid(uuid); }

    public Optional<ReservationEntity> createReservation(ReservationEntity reservationEntity) {
        return Optional.of(reservationRepository.save(reservationEntity));
    }

    public void deleteReservation(String uuid) {
        reservationRepository.deleteByUuid(UUID.fromString(uuid));
    }
}
