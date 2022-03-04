package dk.bec.bookanything.services;

import dk.bec.bookanything.model.FacilityTypeEntity;
import dk.bec.bookanything.repository.FacilityTypeRepository;
import dk.bec.bookanything.service.FacilityTypeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class FacilityTypeServiceTest {

    FacilityTypeService facilityTypeService;
    FacilityTypeRepository facilityTypeRepository;

    private static final String RESTAURANT_NAME = "restaurant";
    private static final String GYM_NAME = "gym";
    private static final String CINEMA_NAME = "cinema";
    private static final String OPERA_NAME = "opera";

    private static final List<FacilityTypeEntity> facilityTypes = Arrays.asList(
            FacilityTypeEntity.builder().name(RESTAURANT_NAME).build(),
            FacilityTypeEntity.builder().name(GYM_NAME).build(),
            FacilityTypeEntity.builder().name(CINEMA_NAME).build(),
            FacilityTypeEntity.builder().name(OPERA_NAME).build()
    );

    private final FacilityTypeEntity facilityTypeEntity = FacilityTypeEntity.builder()
            .id(1L)
            .name(GYM_NAME)
            .build();
    @Before
    public void setup() {
        facilityTypeRepository = mock(FacilityTypeRepository.class);
        facilityTypeService = new FacilityTypeService(facilityTypeRepository);
    }


    @Test
    public void getAllFacilityTypes(){
        Mockito.when(facilityTypeRepository.findAll()).thenReturn(facilityTypes);
        List<FacilityTypeEntity> result = facilityTypeService.getFacilityTypes();
        assertEquals(4, result.size());
        verify(facilityTypeRepository, times(1)).findAll();
    }

    @Test
    public void getFacilityTypeById(){

        Mockito.when(facilityTypeRepository.findById(1L)).thenReturn(Optional.ofNullable(facilityTypeEntity));
        Optional<FacilityTypeEntity> result = facilityTypeService.getFacilityTypeById(1L);
        assertTrue(result.isPresent());
        assertEquals("gym", result.get().getName());

    }

    @Test
    public void addFacilityType(){
        Mockito.when(facilityTypeRepository.save(facilityTypeEntity)).thenReturn(facilityTypeEntity);
        Optional<FacilityTypeEntity> result = Optional.ofNullable(facilityTypeService.addFacilityType(facilityTypeEntity));
        assertTrue(result.isPresent());
        assertEquals("gym", result.get().getName());
        verify(facilityTypeRepository, times(1)).save(facilityTypeEntity);
    }

    @Test
    public void deleteFacilityType(){
        Mockito.when(facilityTypeRepository.findById(1L)).thenReturn(Optional.of(facilityTypeEntity));
        doNothing().when(facilityTypeRepository).delete(facilityTypeEntity);
        facilityTypeService.deleteFacilityTypeById(1L);
        verify(facilityTypeRepository, times(1)).delete(facilityTypeEntity);
    }

    @Test
    public void updateUserTest() {
        Mockito.when(facilityTypeRepository.findById(1L)).thenReturn(Optional.of(facilityTypeEntity));
        facilityTypeService.updateFacilityType(1L, facilityTypeEntity);
        verify(facilityTypeRepository, times(1)).save(facilityTypeEntity);

    }

}
