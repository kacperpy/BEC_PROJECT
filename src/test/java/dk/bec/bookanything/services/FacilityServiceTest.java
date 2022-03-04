package dk.bec.bookanything.services;


import dk.bec.bookanything.mapper.DayOpenMapper;
import dk.bec.bookanything.mapper.FeatureMapper;
import dk.bec.bookanything.model.AddressEntity;
import dk.bec.bookanything.model.FacilityEntity;
import dk.bec.bookanything.model.FacilityTypeEntity;
import dk.bec.bookanything.repository.FacilityRepository;
import dk.bec.bookanything.service.FacilityService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class FacilityServiceTest {

    FacilityService facilityService;
    FacilityRepository facilityRepository;
    FeatureMapper featureMapper;
    DayOpenMapper dayOpenMapper;


    @Before
    public void setup() {
        facilityRepository = mock(FacilityRepository.class);
        featureMapper = mock(FeatureMapper.class);
        dayOpenMapper = mock(DayOpenMapper.class);
        facilityService = new FacilityService(facilityRepository, featureMapper, dayOpenMapper);
    }


    FacilityEntity facility1 = FacilityEntity.builder()
            .id(1L)
            .name("Ala bonjour restaurant")
            .nip("1234567890")
            .krs("1234567890")
            .addressEntity(AddressEntity.builder().id(1L).country("Poland").city("Warsaw").postalCode("02777").streetNumber("2").street("Jerozalimskie").propertyNumber("23").build())
            .build();
    FacilityEntity facility2 = FacilityEntity.builder()
            .id(2L)
            .name("Family restaurant")
            .nip("0987654321")
            .krs("0987654321")
            .addressEntity(AddressEntity.builder().id(2L).country("Poland").city("Warsaw").postalCode("02889").streetNumber("99/1").street("Neopodleglosci").propertyNumber("112").build())
            .build();
    FacilityEntity facility3 = FacilityEntity.builder()
            .id(3L)
            .name("Jeff's restaurant")
            .nip("3334445555")
            .krs("1112223333")
            .addressEntity(AddressEntity.builder().id(3L).country("Poland").city("Warsaw").postalCode("02995").streetNumber("2").street("Marszalkowska").propertyNumber("267").build())
            .build();
    List<FacilityEntity> facilityList = Arrays.asList(facility1, facility2, facility3);
    FacilityTypeEntity facilityTypeEntity = FacilityTypeEntity.builder().id(1L).name("Restaurant").build();
    AddressEntity addressEntity = AddressEntity.builder().id(3L).country("Poland").city("Warsaw").postalCode("02995").streetNumber("2").street("Marszalkowska").propertyNumber("267").build();


    @Test
    public void getFacilityByIdTest() {
        Mockito.when(facilityRepository.findById(1L)).thenReturn(Optional.of(facility1));
        Optional<FacilityEntity> result = facilityService.getFacilityById(1L);
        assertTrue(result.isPresent());
        assertEquals("Ala bonjour restaurant", result.get().getName());
        assertEquals("1234567890", result.get().getNip());
        assertEquals("1234567890", result.get().getKrs());
        assertEquals("Jerozalimskie", result.get().getAddressEntity().getStreet());
    }

    @Test
    public void createFacility() {
        Mockito.when(facilityRepository.save(facility1)).thenReturn(facility1);
        Optional<FacilityEntity> result = facilityService.createFacility(facility1);
        assertTrue(result.isPresent());
        assertEquals("Ala bonjour restaurant", result.get().getName());
        assertEquals("1234567890", result.get().getNip());
        assertEquals("1234567890", result.get().getKrs());
        assertEquals("Jerozalimskie", result.get().getAddressEntity().getStreet());
        verify(facilityRepository, times(1)).save(facility1);

    }

    @Test
    public void updateFacilityTest() {
        Mockito.when(facilityRepository.findById(1L)).thenReturn(Optional.of(facility1));
        Mockito.when(facilityRepository.save(facility2)).thenReturn(facility2);
        Optional<FacilityEntity> result = facilityService.updateFacility(1L, facility2);
        assertTrue(result.isPresent());
        assertEquals("Family restaurant", result.get().getName());
        assertEquals("0987654321", result.get().getNip());
        assertEquals("0987654321", result.get().getKrs());
        assertEquals("Neopodleglosci", result.get().getAddressEntity().getStreet());
        verify(facilityRepository, times(1)).save(facility2);
    }

    @Test
    public void getFacilitiesByTypeTest() {
        Mockito.when(facilityRepository.findByFacilityTypeEntity(facilityTypeEntity)).thenReturn(Optional.ofNullable(facilityList));
        Optional<List<FacilityEntity>> result = facilityService.getFacilitiesByType(facilityTypeEntity);
        assertTrue(result.isPresent());
        assertEquals(3, result.get().size());

    }

    @Test
    public void getFacilitiesByAddressInTest() {
        Mockito.when(facilityRepository.findByAddressEntityIn(Collections.singletonList(addressEntity))).thenReturn(Optional.ofNullable(facilityList));
        Optional<List<FacilityEntity>> result = facilityService.getFacilitiesByAddressIn(Collections.singletonList(addressEntity));
        assertTrue(result.isPresent());
        assertEquals(3, result.get().size());

    }

    @Test
    public void getFacilitiesByAddressInAndTypeTest() {
        Mockito.when(facilityRepository.findByAddressEntityInAndFacilityTypeEntity(Collections.singletonList(addressEntity), facilityTypeEntity)).thenReturn(Optional.ofNullable(facilityList));
        Optional<List<FacilityEntity>> result = facilityService.getFacilitiesByAddressInAndType(Collections.singletonList(addressEntity), facilityTypeEntity);
        assertTrue(result.isPresent());
        assertEquals(3, result.get().size());
    }

    @Test
    public void deleteFacilityTest() {
        doNothing().when(facilityRepository).deleteById(1L);
        facilityService.deleteFacilityById(1L);
        verify(facilityRepository, times(1)).deleteById(1L);
    }


}
