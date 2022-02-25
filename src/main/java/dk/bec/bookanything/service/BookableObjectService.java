package dk.bec.bookanything.service;

import dk.bec.bookanything.model.BookableObjectEntity;
import dk.bec.bookanything.repository.BookableObjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
