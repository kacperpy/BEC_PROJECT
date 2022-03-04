package dk.bec.bookanything.services;

import dk.bec.bookanything.model.AddressEntity;
import dk.bec.bookanything.repository.AddressRepository;
import dk.bec.bookanything.service.AddressService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class AddressServiceTest {

    AddressService addressService;
    AddressRepository addressRepository;

    private static final String STREET_NAME = "street";
    private static final String STREET_NUMBER = "68";
    private static final String PROPERTY_NUMBER = "1312";
    private static final String CITY_NAME = "Radom";
    private static final String POSTAL_CODE = "11111";
    private static final String COUNTRY_NAME = "Rzeczpospolita Polska";


    private final AddressEntity addressEntity = AddressEntity.builder()
            .id(1L)
            .street(STREET_NAME)
            .streetNumber(STREET_NUMBER)
            .propertyNumber(PROPERTY_NUMBER)
            .city(CITY_NAME)
            .postalCode(POSTAL_CODE)
            .country(COUNTRY_NAME)
            .build();

    private final List<AddressEntity> addressEntities = Arrays.asList(
            addressEntity,
            addressEntity,
            addressEntity
    );

    @Before
    public void setup() {
        addressRepository = mock(AddressRepository.class);
        addressService = new AddressService(addressRepository);
    }


    @Test
    public void getAddressById(){
        Mockito.when(addressRepository.findById(1L)).thenReturn(Optional.ofNullable(addressEntity));
        Optional<AddressEntity> result = addressService.getAddressById(1L);
        assertEquals(addressEntity, result.get());
        verify(addressRepository, times(1)).findById(1L);
    }


    @Test
    public void getAddressByCity(){
        Mockito.when(addressRepository.findByCity(CITY_NAME)).thenReturn(Optional.of(addressEntities));
        Optional<List<AddressEntity>> result = addressService.getAddressesByCity(CITY_NAME);
        assertEquals(addressEntities, result.get());
        verify(addressRepository, times(1)).findByCity(CITY_NAME);
    }


    @Test
    public void deleteAddress(){
        doNothing().when(addressRepository).deleteById(1L);
        addressRepository.deleteById(1L);
        verify(addressRepository,times(1)).deleteById(1L);
    }
}
