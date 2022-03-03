package dk.bec.bookanything.controller;

import dk.bec.bookanything.dto.ReservationCreateDto;
import dk.bec.bookanything.dto.ReservationReadDto;
import dk.bec.bookanything.mapper.ReservationMapper;
import dk.bec.bookanything.model.BookableObjectEntity;
import dk.bec.bookanything.model.ReservationEntity;
import dk.bec.bookanything.model.UserEntity;
import dk.bec.bookanything.service.*;
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

    private final UserService userService;

    private final BookableObjectService bookableObjectService;

    private final EmailServiceImpl emailService;

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
        Optional<UserEntity> usr = userService.getUserById(reservationCreateDto.getUserId());
        Optional<ReservationEntity> res = reservationService.createReservation(reservationMapper.mapReservationCreateDtoToReservationEntity(reservationCreateDto, null));
        if (res.isPresent()) {
            if (usr.isPresent()) {
                emailService.sendSimpleMessage(usr.get().getEmail(), "new reservation", "You booked " + res.get().getBookableObjectEntity().getName() + " at " + res.get().getBookableObjectEntity().getFeature().getFacility().getName() +
                        "\nNumber of people: " + res.get().getPeopleNumber() + "\n Your reservation number is " + res.get().getId() + "\n Date: " + res.get().getDateFrom() + " to " + res.get().getDateTo());
                return res.map(reservationEntity -> new ResponseEntity<>(reservationMapper.mapReservationEntityToReservationReadDto(reservationEntity), HttpStatus.CREATED)).orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
            }
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReservationReadDto> updateReservation(@RequestBody @Valid ReservationCreateDto reservationCreateDto, @PathVariable("id") Long id) {
        Optional<BookableObjectEntity> bookableObjectEntity = bookableObjectService.getBookableObjectById(reservationCreateDto.getBookableObjectId());
        Optional<UserEntity> usr = userService.getUserById(reservationCreateDto.getUserId());
        if (bookableObjectEntity.isPresent()) {
            Optional<ReservationEntity> res = reservationService.updateReservation(reservationMapper.mapReservationCreateDtoToReservationEntity(reservationCreateDto, id), id);
            if (res.isPresent()) {
                if (usr.isPresent()) {
                    emailService.sendSimpleMessage(usr.get().getEmail(), "reservation update", "You updated your reservation " + res.get().getBookableObjectEntity().getName() + " at " + res.get().getBookableObjectEntity().getFeature().getFacility().getName() +
                            "\nNumber of people: " + res.get().getPeopleNumber() + "\n Your reservation number is " + res.get().getId() + "\n New date: " + res.get().getDateFrom() + " to " + res.get().getDateTo());
                    return res.map(reservationEntity -> new ResponseEntity<>(reservationMapper.mapReservationEntityToReservationReadDto(reservationEntity), HttpStatus.CREATED)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
                }
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ReservationReadDto> deleteReservation(@PathVariable("id") Long id) {
        Optional<ReservationEntity> res = reservationService.deleteReservation(id);
        return res.map(reservationEntity -> new ResponseEntity<>(reservationMapper.mapReservationEntityToReservationReadDto(reservationEntity), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


}
