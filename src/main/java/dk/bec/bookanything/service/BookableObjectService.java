package dk.bec.bookanything.service;

import dk.bec.bookanything.model.BookableObjectEntity;
import dk.bec.bookanything.repository.BookableObjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
public class BookableObjectService {

    private BookableObjectRepository bookableObjectRepository;

    @Autowired
    public BookableObjectService(BookableObjectRepository bookableObjectRepository) {
        this.bookableObjectRepository = bookableObjectRepository;
    }

    public List<BookableObjectEntity> getAllBookableObjects() {
        return bookableObjectRepository.findAll();
    }

    public void createBookableObject(BookableObjectEntity bookableObjectEntity) {
        bookableObjectRepository.save(bookableObjectEntity);
    }

    public BookableObjectEntity getBookableObjectByUUID(UUID uuid) {
        return bookableObjectRepository.findByUuid(uuid);
    }

    @Transactional
    public ResponseEntity<String> deleteBookableObject(UUID uuid) {
        bookableObjectRepository.deleteByUuid(uuid);
        return ResponseEntity.ok("Bookable Object with uuid: " + uuid + " was deleted");
    }
}
