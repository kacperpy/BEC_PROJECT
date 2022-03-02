package dk.bec.bookanything.service;

import dk.bec.bookanything.model.ReservationEntity;
import dk.bec.bookanything.repository.ReservationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;

    public List<ReservationEntity> getReservations() {
        return reservationRepository.findAll();
    }

    public Optional<ReservationEntity> getReservationById(Long id) { return reservationRepository.findById(id); }

    public Optional<ReservationEntity> createReservation(ReservationEntity reservationEntity) {
        return Optional.of(reservationRepository.save(reservationEntity));
    }

    public Optional<ReservationEntity> updateReservation(ReservationEntity reservationEntity, Long id) {
        Optional<ReservationEntity> reservation = getReservationById(id);

        if (reservation.isPresent()) {
            return Optional.of(reservationRepository.save(reservationEntity));
        } else {
            return Optional.empty();
        }
    }



    public Optional<ReservationEntity> deleteReservation(Long id) {
        Optional<ReservationEntity> reservation = getReservationById(id);
        if (reservation.isPresent()) {
            reservationRepository.deleteById(id);
        }
        return reservation;
    }
}
