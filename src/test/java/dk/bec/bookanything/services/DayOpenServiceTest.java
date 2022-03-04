package dk.bec.bookanything.services;

import dk.bec.bookanything.model.AddressEntity;
import dk.bec.bookanything.model.DayOpenEntity;
import dk.bec.bookanything.model.FacilityEntity;
import dk.bec.bookanything.repository.DayOpenRepository;
import dk.bec.bookanything.service.DayOpenService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class DayOpenServiceTest {

    DayOpenService dayOpenService;
    DayOpenRepository dayOpenRepository;

    private final List<DayOpenEntity> dayOpenEntityList = new ArrayList<>();
    private final DayOpenEntity dayOpenEntity1 = DayOpenEntity.builder()
            .id(1L)
            .day(1)
            .hourFrom(LocalDateTime.of(2022,1,2,12,0))
            .hourTo(LocalDateTime.of(2022,1,2,13,0))
            .facility(FacilityEntity.builder()
                    .id(1L)
                    .name("facilityName")
                    .nip("1234567890")
                    .krs("1234567890")
                    .addressEntity(AddressEntity.builder()
                            .id(1L)
                            .street("street")
                            .streetNumber("22")
                            .propertyNumber("1")
                            .city("Warsaw")
                            .country("Poland")
                            .postalCode("02-123")
                            .build())
                    .build())
            .build();

    private final DayOpenEntity dayOpenEntity2 = DayOpenEntity.builder()
            .id(1L)
            .day(2)
            .hourFrom(LocalDateTime.of(2022,2,4,12,0))
            .hourTo(LocalDateTime.of(2022,2,4,13,0))
            .facility(FacilityEntity.builder()
                    .id(1L)
                    .name("facilityName2")
                    .nip("1234567890")
                    .krs("1234567890")
                    .addressEntity(AddressEntity.builder()
                            .id(1L)
                            .street("street")
                            .streetNumber("22")
                            .propertyNumber("1")
                            .city("Warsaw")
                            .country("Poland")
                            .postalCode("02-123")
                            .build())
                    .build())
            .build();


    @Before
    public void setup(){
        dayOpenRepository = mock(DayOpenRepository.class);
        dayOpenService = new DayOpenService(dayOpenRepository);
    }

    @Test
    public void getDayOpenByIdTest(){
        Mockito.when(dayOpenRepository.findById(1L)).thenReturn(Optional.ofNullable(dayOpenEntity1));
        Optional<DayOpenEntity> result = dayOpenService.getDayOpenById(1L);
        assertTrue(result.isPresent());
        Assertions.assertEquals(LocalDateTime.of(2022,1,2,12,0), result.get().getHourFrom());

    }

    @Test
    public void addDayOpenTest(){
        Mockito.when(dayOpenRepository.save(dayOpenEntity1)).thenReturn(dayOpenEntity1);
        Optional<DayOpenEntity> result = dayOpenService.addDayOpen(dayOpenEntity1);
        assertTrue(result.isPresent());
        Assertions.assertEquals(LocalDateTime.of(2022,1,2,12,0), result.get().getHourFrom());
        verify(dayOpenRepository,Mockito.times(1)).save(dayOpenEntity1);
    }

    @Test
    public void deleteDayOpenTest(){
        doNothing().when(dayOpenRepository).deleteById(1L);
        dayOpenService.deleteDayOpen(1L);
        verify(dayOpenRepository,times(1)).deleteById(1L);
    }

    @Test
    public void modifyDayOpenTest(){
        Mockito.when(dayOpenRepository.findById(1L)).thenReturn(Optional.ofNullable(dayOpenEntity1));
        Mockito.when(dayOpenRepository.save(dayOpenEntity2)).thenReturn(dayOpenEntity2);
        dayOpenService.modifyDayOpen(dayOpenEntity2);
        verify(dayOpenRepository,times(1)).save(dayOpenEntity2);

    }
}
