package dk.bec.bookanything.controller;

import dk.bec.bookanything.dto.BookableObjectCreateDto;
import dk.bec.bookanything.dto.BookableObjectReadDto;
import dk.bec.bookanything.mapper.BookableObjectMapper;
import dk.bec.bookanything.model.BookableObjectEntity;
import dk.bec.bookanything.model.ReservationEntity;
import dk.bec.bookanything.service.BookableObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class BookableObjectController {

    private final BookableObjectService bookableObjectService;
    private final BookableObjectMapper bookableObjectMapper;

    @Autowired
    public BookableObjectController(BookableObjectService bookableObjectService, BookableObjectMapper bookableObjectMapper) {
        this.bookableObjectService = bookableObjectService;
        this.bookableObjectMapper = bookableObjectMapper;
    }

    @GetMapping("/bookable-objects")
    public ResponseEntity<List<BookableObjectReadDto>> getBookableObjects() {
        return ResponseEntity.ok()
                .body(bookableObjectService.getAllBookableObjects());
    }

    @PostMapping("/bookable-objects")
    public ResponseEntity<BookableObjectCreateDto> createBookableObject(@Valid @RequestBody BookableObjectCreateDto bookableObjectCreateDto) {
        try {
            bookableObjectService.createBookableObject(bookableObjectMapper.mapDtoToEntity(bookableObjectCreateDto, null));
            return new ResponseEntity<>(bookableObjectCreateDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR); //TODO change response code
        }
    }

    @GetMapping("/bookable-objects/{id}")
    public ResponseEntity<BookableObjectReadDto> getBookableObjectById(@PathVariable("id") Long id) {
        return ResponseEntity.ok()
                .body(bookableObjectMapper.mapEntityToDto(bookableObjectService.getBookableObjectById(id).get())); //TODO error handling
    }

    @DeleteMapping("/bookable-objects/{id}")
    public void deleteBookableObjectById(@PathVariable("id") Long id) {
        bookableObjectService.deleteBookableObjectById(id); //TODO return response
    }

    @PutMapping("/bookable-objects/{id}")
    public Optional<BookableObjectReadDto> updateBookableObjectById(@Valid @RequestBody BookableObjectEntity bookableObjectEntity, @PathVariable("id") Long id) { //TODO unused parameter!
        return Optional.ofNullable(
                bookableObjectService.updateBookableObject(bookableObjectEntity)); //TODO response
    }

    @GetMapping("/bookable-objects/{id}/reservations")
    public Optional<List<ReservationEntity>> getReservationsForBookableObject(@PathVariable("id") Long id) {
        return Optional.ofNullable(bookableObjectService.getReservationsForBookableObject(id)); //TODO response
    }
}