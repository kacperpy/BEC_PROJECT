package dk.bec.bookanything.service;

import dk.bec.bookanything.dto.BookableObjectReadDto;
import dk.bec.bookanything.mapper.BookableObjectMapper;
import dk.bec.bookanything.model.BookableObjectEntity;
import dk.bec.bookanything.model.ReservationEntity;
import dk.bec.bookanything.repository.BookableObjectRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookableObjectService {

    private final BookableObjectRepository bookableObjectRepository;
    private final BookableObjectMapper bookableObjectMapper;

    public BookableObjectService(BookableObjectRepository bookableObjectRepository, BookableObjectMapper bookableObjectMapper) {
        this.bookableObjectRepository = bookableObjectRepository;
        this.bookableObjectMapper = bookableObjectMapper;
    }

    public List<BookableObjectReadDto> getAllBookableObjects() {
        return bookableObjectRepository.findAll().stream()
                .map(bookableObjectMapper::mapEntityToDto).collect(Collectors.toList());
    }

    public void createBookableObject(BookableObjectEntity bookableObjectEntity) {
        bookableObjectRepository.save(bookableObjectEntity);
    }

    public Optional<BookableObjectEntity> getBookableObjectById(Long id) {
        return bookableObjectRepository.findById(id);
    }

    public void deleteBookableObjectById(Long id) {
        bookableObjectRepository.deleteById(id);
    }

    public List<ReservationEntity> getReservationsForBookableObject(Long bookableObjectId) {
        Optional<BookableObjectEntity> bookableObjectEntity = bookableObjectRepository.findById(bookableObjectId);
        if (bookableObjectEntity.isPresent())
            return bookableObjectEntity.get().getReservations();

        return Collections.emptyList();
    }

    @Transactional
    public BookableObjectReadDto updateBookableObject(BookableObjectEntity bookableObjectEntity) {
        bookableObjectRepository.save(bookableObjectEntity);
        return bookableObjectMapper.mapEntityToDto(bookableObjectEntity);
    }
}