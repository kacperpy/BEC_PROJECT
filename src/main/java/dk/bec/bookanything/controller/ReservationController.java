package dk.bec.bookanything.controller;

import dk.bec.bookanything.model.ReservationEntity;
import dk.bec.bookanything.service.ReservationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/reservations")
@AllArgsConstructor
public class ReservationController {

    @Autowired
    private final ReservationService reservationService;

    @GetMapping
    public ResponseEntity<List<ReservationEntity>> getDiscountCodes() {
        List<ReservationEntity> res = reservationService.getReservations();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
