package dk.bec.bookanything.controller;

import dk.bec.bookanything.model.BookableObjectEntity;
import dk.bec.bookanything.service.BookableObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class BookableObjectController {

    private BookableObjectService bookableObjectService;

    @Autowired
    public BookableObjectController(BookableObjectService bookableObjectService) {
        this.bookableObjectService = bookableObjectService;
    }

    @GetMapping("/bookable-objects")
    public List<BookableObjectEntity> bookableObjects() {
        return bookableObjectService.getAllBookableObjects();
    }

    @PostMapping("/bookable-objects")
    public ResponseEntity<BookableObjectEntity> createBookableObject(@RequestBody BookableObjectEntity bookableObjectEntity) {
        try {
            bookableObjectService.createBookableObject(bookableObjectEntity);
            return new ResponseEntity<>(bookableObjectEntity, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/bookable-objects/{uuid}")
    public BookableObjectEntity getBookableObjectByUUID(@PathVariable("uuid") UUID uuid) {
        return bookableObjectService.getBookableObjectByUUID(uuid);
    }
}
