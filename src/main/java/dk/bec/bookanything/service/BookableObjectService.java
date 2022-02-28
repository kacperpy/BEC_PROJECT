package dk.bec.bookanything.service;

import dk.bec.bookanything.dto.BookableObjectReadDto;
import dk.bec.bookanything.model.BookableObjectEntity;
import dk.bec.bookanything.repository.BookableObjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

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

    public Optional<BookableObjectEntity> getBookableObjectById(Long id) {
        return bookableObjectRepository.findById(id);
    }

    @Transactional
    public BookableObjectReadDto updateBookableObject(BookableObjectEntity bookableObjectEntity) {
        bookableObjectRepository.save(bookableObjectEntity);
        return convertToReadDto(bookableObjectEntity);
    }

    private BookableObjectReadDto convertToReadDto(BookableObjectEntity bookableObjectEntity) {
        return BookableObjectReadDto.builder()
                .id(bookableObjectEntity.getId())
                .name(bookableObjectEntity.getName())
                .time_period(bookableObjectEntity.getTime_period())
                .capacity(bookableObjectEntity.getCapacity())
                .description(bookableObjectEntity.getDescription())
                .date_time(bookableObjectEntity.getDate_time())
                .reservation_count(bookableObjectEntity.getReservations() != null ? bookableObjectEntity.getReservations().size() : 0)
                .feature(bookableObjectEntity.getFeature())
                .build();
    }
}