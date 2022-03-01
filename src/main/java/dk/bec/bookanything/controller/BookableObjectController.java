package dk.bec.bookanything.controller;

import dk.bec.bookanything.dto.BookableObjectCreateDto;
import dk.bec.bookanything.dto.BookableObjectReadDto;
import dk.bec.bookanything.model.BookableObjectEntity;
import dk.bec.bookanything.model.ReservationEntity;
import dk.bec.bookanything.service.BookableObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class BookableObjectController {

    private BookableObjectService bookableObjectService;

    @Autowired
    public BookableObjectController(BookableObjectService bookableObjectService) {
        this.bookableObjectService = bookableObjectService;
    }

    @GetMapping("/bookable-objects")
    public ResponseEntity<List<BookableObjectReadDto>> getBookableObjects() {
       List<ReservationEntity> l = bookableObjectService.getBookableObjectById(1L).get().getReservations();
        return ResponseEntity.ok()
                .body(bookableObjectService.getAllBookableObjects());
    }

    @PostMapping("/bookable-objects")
    public ResponseEntity<BookableObjectCreateDto> createBookableObject(@RequestBody BookableObjectCreateDto bookableObjectCreateDto) {
        try {
            bookableObjectService.createBookableObject(dtoToEntity(bookableObjectCreateDto, null));
            return new ResponseEntity<>(bookableObjectCreateDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/bookable-objects/{id}")
    public Optional<BookableObjectEntity> getBookableObjectById(@PathVariable("id") Long id) {
        return bookableObjectService.getBookableObjectById(id);
    }

    @DeleteMapping("/bookable-objects/{id}")
    public void deleteBookableObjectById(@PathVariable("id") Long id) {
        bookableObjectService.deleteBookableObjectById(id);
    }

    @PutMapping("/bookable-objects/{id}")
    public Optional<BookableObjectReadDto> updateBookableObjectById(@RequestBody BookableObjectCreateDto bookableObjectCreateDto, @PathVariable("id") Long id) {
        return Optional.ofNullable(bookableObjectService.updateBookableObject(dtoToEntity(bookableObjectCreateDto, id)));
    }

    private BookableObjectEntity dtoToEntity(BookableObjectCreateDto bookableObjectCreateDto, Long id) {
        return BookableObjectEntity.builder()
                .id(id)
                .name(bookableObjectCreateDto.getName())
                .time_period(bookableObjectCreateDto.getTime_period())
                .capacity(bookableObjectCreateDto.getCapacity())
                .description(bookableObjectCreateDto.getDescription())
                .date_time(bookableObjectCreateDto.getDate_time())
                .reservations(bookableObjectCreateDto.getReservations())
                .feature(bookableObjectCreateDto.getFeature())
                .build();
    }

    @GetMapping("/bookable-objects/{id}/reservations")
    public Optional<List<ReservationEntity>> getReservationsForBookableObject(@PathVariable("id") Long id) {
        return Optional.ofNullable(bookableObjectService.getReservationsForBookableObject(id));
    }
}