package dk.bec.bookanything.services;


import dk.bec.bookanything.model.BookableObjectEntity;
import dk.bec.bookanything.model.ReservationEntity;
import dk.bec.bookanything.model.UserEntity;
import dk.bec.bookanything.repository.ReservationRepository;
import dk.bec.bookanything.service.ReservationService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class ReservationServiceTest {

    ReservationService reservationService;
    ReservationRepository reservationRepository;

    private final UserEntity user1 = UserEntity.builder()
            .id(1L)
            .email("jan.kowalski@gmail.com")
            .birthDate(LocalDate.of(1990, 1, 1))
            .phoneNumber("+48667857312")
            .build();
    private final UserEntity user2 = UserEntity.builder()
            .id(2L)
            .email("adam.nowak@wp.pl")
            .birthDate(LocalDate.of(1985, 1, 1))
            .phoneNumber("+48315420751")
            .build();
    private final UserEntity user3 = UserEntity.builder()
            .id(3L)
            .email("tomek@gmail.com")
            .birthDate(LocalDate.of(1985, 1, 1))
            .phoneNumber("+48791799415")
            .build();

    private final BookableObjectEntity bookableObjectEntity1 = BookableObjectEntity.builder()
            .id(1L)
            .name("Table for 4 person")
            .timePeriod(1)
            .capacity(4)
            .description("Table prepared for 4 person")
            .reusable(true)
            .price(400.)
            .build();
    private final BookableObjectEntity bookableObjectEntity2 = BookableObjectEntity.builder()
            .id(2L)
            .name("Boxing training")
            .capacity(1)
            .description("Boxing practices for people in every age")
            .reusable(true)
            .price(100.)
            .build();
    private final BookableObjectEntity bookableObjectEntity3 = BookableObjectEntity.builder()
            .id(3L)
            .name("Seating place in cinema")
            .capacity(1)
            .description("A seating place for new Disney movie")
            .reusable(true)
            .price(25.)
            .build();

    private final List<ReservationEntity> reservationEntityList = new ArrayList<>();
    private final ReservationEntity reservationEntity1 = ReservationEntity.builder()
            .id(1L)
            .dateFrom(LocalDateTime.of(2022, 1, 15, 12, 30))
            .dateTo(LocalDateTime.of(2022, 1, 15, 13, 30))
            .peopleNumber(4)
            .bookableObjectEntity(bookableObjectEntity1)
            .userEntity(user1)
            .build();
    private final ReservationEntity reservationEntity2 = ReservationEntity.builder()
            .id(2L)
            .dateFrom(LocalDateTime.of(2022, 2, 13, 15, 15))
            .dateTo(LocalDateTime.of(2022, 2, 13, 13, 30))
            .peopleNumber(1)
            .bookableObjectEntity(bookableObjectEntity2)
            .userEntity(user2)
            .build();
    private final ReservationEntity reservationEntity3 = ReservationEntity.builder()
            .id(3L)
            .dateFrom(LocalDateTime.of(2022, 1, 12, 14, 15))
            .dateTo(LocalDateTime.of(2022, 1, 12, 16, 15))
            .peopleNumber(1)
            .bookableObjectEntity(bookableObjectEntity3)
            .userEntity(user3)
            .build();

    @Before
    public void setup() {
        reservationRepository = mock(ReservationRepository.class);
        reservationService = new ReservationService(reservationRepository);
    }

    @Test
    public void getAllReservationsTest() {
        reservationEntityList.add(reservationEntity1);
        reservationEntityList.add(reservationEntity2);
        reservationEntityList.add(reservationEntity3);

        Mockito.when(reservationRepository.findAll()).thenReturn(reservationEntityList);
        List<ReservationEntity> result = reservationService.getReservations();
        assertEquals(3, result.size());
        verify(reservationRepository, times(1)).findAll();
    }

    @Test
    public void getReservationByIdTest(){
        when(reservationRepository.save(reservationEntity1)).thenReturn(reservationEntity1);
        Optional<ReservationEntity> result = reservationService.createReservation(reservationEntity1);
        assertTrue(result.isPresent());
        assertEquals(Long.valueOf(1), result.get().getId());
        assertEquals(reservationEntity1.getDateFrom(), result.get().getDateFrom());
        assertEquals(reservationEntity1.getDateTo(), result.get().getDateTo());
        assertEquals(reservationEntity1.getPeopleNumber(), result.get().getPeopleNumber());
        assertEquals(reservationEntity1.getBookableObjectEntity(), result.get().getBookableObjectEntity());
        assertEquals(reservationEntity1.getUserEntity(), result.get().getUserEntity());
    }

    @Test
    public void addReservationTest() {
        when(reservationRepository.save(reservationEntity1)).thenReturn(reservationEntity1);
        Optional<ReservationEntity> result = reservationService.createReservation(reservationEntity1);
        assertTrue(result.isPresent());
        assertEquals(Long.valueOf(1), result.get().getId());
        assertEquals(reservationEntity1.getDateFrom(), result.get().getDateFrom());
        assertEquals(reservationEntity1.getDateTo(), result.get().getDateTo());
        assertEquals(reservationEntity1.getPeopleNumber(), result.get().getPeopleNumber());
        assertEquals(reservationEntity1.getBookableObjectEntity(), result.get().getBookableObjectEntity());
        assertEquals(reservationEntity1.getUserEntity(), result.get().getUserEntity());
        verify(reservationRepository, times(1)).save(reservationEntity1);
    }

    @Test
    public void deleteReservationTest() {
        Mockito.when(reservationRepository.findById(1L)).thenReturn(Optional.of(reservationEntity1));
        doNothing().when(reservationRepository).delete(reservationEntity1);
        reservationService.deleteReservation(1L);
        verify(reservationRepository, times(1)).deleteById(1L);
    }
}