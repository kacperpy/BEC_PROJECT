package dk.bec.bookanything.service;

import dk.bec.bookanything.dto.BookableObjectReadDto;
import dk.bec.bookanything.model.BookableObjectEntity;
import dk.bec.bookanything.model.ReservationEntity;
import dk.bec.bookanything.repository.BookableObjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookableObjectService {

    private BookableObjectRepository bookableObjectRepository;

    @Autowired
    public BookableObjectService(BookableObjectRepository bookableObjectRepository) {
        this.bookableObjectRepository = bookableObjectRepository;
    }

    public List<BookableObjectReadDto> getAllBookableObjects() {
        return bookableObjectRepository.findAll().stream()
                .map(this::convertToReadDto).collect(Collectors.toList());
    }

    public void createBookableObject(BookableObjectEntity bookableObjectEntity) {
        bookableObjectRepository.save(bookableObjectEntity);
    }

    public Optional<BookableObjectEntity> getBookableObjectById(Long id) {
        return bookableObjectRepository.findById(id);
    }

    public List<ReservationEntity> getReservationsForBookableObject(Long bookableObjectId) {
        return bookableObjectRepository.findById(bookableObjectId).get().getReservations();
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