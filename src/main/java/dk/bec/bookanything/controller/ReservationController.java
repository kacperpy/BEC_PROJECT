package dk.bec.bookanything.controller;

import dk.bec.bookanything.dto.ReservationCreateDto;
import dk.bec.bookanything.dto.ReservationReadDto;
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
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/reservations")
@AllArgsConstructor
public class ReservationController {

    @Autowired
    private final ReservationService reservationService;

    @Autowired
    private final BookableObjectService bookableObjectService;

    @GetMapping
    public ResponseEntity<List<ReservationReadDto>> getReservations() {
        List<ReservationEntity> res = reservationService.getReservations();
        List<ReservationReadDto> reservationReadDtos = res.stream().map(ReservationReadDto::new).collect(Collectors.toList());
        return new ResponseEntity<>(reservationReadDtos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationReadDto> getReservation(@PathVariable("id") Long id) {
        Optional<ReservationEntity> res = reservationService.getReservationById(id);
        return res.map(reservationEntity -> new ResponseEntity<>(new ReservationReadDto(reservationEntity), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<ReservationReadDto> createReservation(@RequestBody @Valid ReservationCreateDto reservationCreateDto) {
        Optional<BookableObjectEntity> bookableObjectEntity = bookableObjectService.getBookableObjectById(reservationCreateDto.getBookableObjectId());
        if (bookableObjectEntity.isPresent()) {
            Optional<ReservationEntity> res = reservationService.createReservation(createReservationDtoToEntity(reservationCreateDto, bookableObjectEntity.get()));
            return res.map(reservationEntity -> new ResponseEntity<>(new ReservationReadDto(reservationEntity), HttpStatus.CREATED)).orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
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
