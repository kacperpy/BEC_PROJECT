package dk.bec.bookanything.controller;

import dk.bec.bookanything.dto.ReservationCreateDto;
import dk.bec.bookanything.model.BookableObjectEntity;
import dk.bec.bookanything.model.ReservationEntity;
import dk.bec.bookanything.service.BookableObjectService;
import dk.bec.bookanything.service.ReservationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/reservations")
@AllArgsConstructor
public class ReservationController {

    @Autowired
    private final ReservationService reservationService;

    @Autowired
    private final BookableObjectService bookableObjectService;

    @GetMapping
    public ResponseEntity<List<ReservationEntity>> getReservations() {
        List<ReservationEntity> res = reservationService.getReservations();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationEntity> getReservation(@PathVariable("id") Long id) {
        Optional<ReservationEntity> res = reservationService.getReservationById(id);
        return res.map(reservationEntity -> new ResponseEntity<>(reservationEntity, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<ReservationEntity> createReservation(@RequestBody @Valid ReservationCreateDto reservationCreateDto) {
        Optional<BookableObjectEntity> bookableObjectEntity = bookableObjectService.getBookableObjectById(reservationCreateDto.getBookableObjectId());
        if (bookableObjectEntity.isPresent()) {
            Optional<ReservationEntity> res = reservationService.createReservation(createReservationDtoToEntity(reservationCreateDto, bookableObjectEntity.get()));
            return res.map(reservationEntity -> new ResponseEntity<>(reservationEntity, HttpStatus.CREATED)).orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    private ReservationEntity createReservationDtoToEntity(ReservationCreateDto reservationCreateDto, BookableObjectEntity bookableObjectEntity) {
        return ReservationEntity.builder()
                                .bookableObjectEntity(bookableObjectEntity)
                                .dateFrom(reservationCreateDto.getDateFrom())
                                .dateTo(reservationCreateDto.getDateTo())
                                .build();
    }
}
