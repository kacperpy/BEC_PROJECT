package dk.bec.bookanything.controller;

import dk.bec.bookanything.dto.ReservationCreateDto;
import dk.bec.bookanything.dto.ReservationReadDto;
import dk.bec.bookanything.mapper.ReservationMapper;
import dk.bec.bookanything.model.BookableObjectEntity;
import dk.bec.bookanything.model.ReservationEntity;
import dk.bec.bookanything.service.BookableObjectService;
import dk.bec.bookanything.service.ReservationService;
import lombok.AllArgsConstructor;
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

    private final ReservationService reservationService;
    private final BookableObjectService bookableObjectService;
    private final ReservationMapper reservationMapper;

    @GetMapping
    public ResponseEntity<List<ReservationReadDto>> getReservations() {
        List<ReservationEntity> res = reservationService.getReservations();
        List<ReservationReadDto> reservationReadDtos = res.stream().map(reservationMapper::mapReservationEntityToReservationReadDto).collect(Collectors.toList());
        return new ResponseEntity<>(reservationReadDtos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationReadDto> getReservation(@PathVariable("id") Long id) {
        Optional<ReservationEntity> res = reservationService.getReservationById(id);
        return res.map(reservationEntity -> new ResponseEntity<>(reservationMapper.mapReservationEntityToReservationReadDto(reservationEntity), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<ReservationReadDto> createReservation(@RequestBody @Valid ReservationCreateDto reservationCreateDto) {
        Optional<ReservationEntity> res = reservationService.createReservation(reservationMapper.mapReservationCreateDtoToReservationEntity(reservationCreateDto, null));
        return res.map(reservationEntity -> new ResponseEntity<>(reservationMapper.mapReservationEntityToReservationReadDto(reservationEntity), HttpStatus.CREATED)).orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReservationReadDto> updateReservation(@RequestBody @Valid ReservationCreateDto reservationCreateDto, @PathVariable("id") Long id) {
        Optional<BookableObjectEntity> bookableObjectEntity = bookableObjectService.getBookableObjectById(reservationCreateDto.getBookableObjectId());

        if (bookableObjectEntity.isPresent()) {
            Optional<ReservationEntity> res = reservationService.updateReservation(reservationMapper.mapReservationCreateDtoToReservationEntity(reservationCreateDto, id), id);
            return res.map(reservationEntity -> new ResponseEntity<>(reservationMapper.mapReservationEntityToReservationReadDto(reservationEntity), HttpStatus.CREATED)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ReservationReadDto> deleteReservation(@PathVariable("id") Long id) {
        Optional<ReservationEntity> res = reservationService.deleteReservation(id);
        return res.map(reservationEntity -> new ResponseEntity<>(reservationMapper.mapReservationEntityToReservationReadDto(reservationEntity), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


}
