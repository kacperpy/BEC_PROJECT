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
@RequestMapping("/api/bookable-objects")
public class BookableObjectController {

    private final BookableObjectService bookableObjectService;
    private final BookableObjectMapper bookableObjectMapper;

    @Autowired
    public BookableObjectController(BookableObjectService bookableObjectService, BookableObjectMapper bookableObjectMapper) {
        this.bookableObjectService = bookableObjectService;
        this.bookableObjectMapper = bookableObjectMapper;
    }

    @GetMapping("/")
    public ResponseEntity<List<BookableObjectReadDto>> getBookableObjects() {
        return ResponseEntity.ok()
                .body(bookableObjectService.getAllBookableObjects());
    }

    @PostMapping("/")
    public ResponseEntity<BookableObjectCreateDto> createBookableObject(@Valid @RequestBody BookableObjectCreateDto bookableObjectCreateDto) {
        try {
            bookableObjectService.createBookableObject(bookableObjectMapper.mapDtoToEntity(bookableObjectCreateDto, null));
            return new ResponseEntity<>(bookableObjectCreateDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookableObjectReadDto> getBookableObjectById(@PathVariable("id") Long id) {
        Optional<BookableObjectEntity> bookableObjectEntity = bookableObjectService.getBookableObjectById(id);
        return bookableObjectEntity.map(objectEntity -> new ResponseEntity<>(bookableObjectMapper.mapEntityToDto(objectEntity), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public void deleteBookableObjectById(@PathVariable("id") Long id) {
        bookableObjectService.deleteBookableObjectById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookableObjectReadDto> updateBookableObjectById(@Valid @RequestBody BookableObjectCreateDto bookableObjectCreateDto, @PathVariable("id") Long id) {
              return new ResponseEntity<>(bookableObjectService.updateBookableObject(bookableObjectMapper.mapDtoToEntity(bookableObjectCreateDto,id)), HttpStatus.OK);
    }

    @GetMapping("/{id}/reservations")
    public ResponseEntity<List<ReservationEntity>> getReservationsForBookableObject(@PathVariable("id") Long id) {
        return new ResponseEntity<>(bookableObjectService.getReservationsForBookableObject(id), HttpStatus.OK);
    }
}