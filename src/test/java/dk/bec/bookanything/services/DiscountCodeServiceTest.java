package dk.bec.bookanything.services;

import dk.bec.bookanything.model.DiscountCodeEntity;
import dk.bec.bookanything.model.FacilityEntity;
import dk.bec.bookanything.repository.DiscountCodeRepository;
import dk.bec.bookanything.service.DiscountCodeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;


@ExtendWith(SpringExtension.class)
public class DiscountCodeServiceTest {

    DiscountCodeService discountCodeService;

    DiscountCodeRepository discountCodeRepository;

    private final List<DiscountCodeEntity> discountCodeEntities = new ArrayList<>();

    private final DiscountCodeEntity discountCodeEntity1 = DiscountCodeEntity.builder()
            .id(1L)
            .code("hshduvwuevd")
            .amount(2)
            .facility(FacilityEntity.builder().name("restaurant").build()).build();
    private final DiscountCodeEntity discountCodeEntity2 = DiscountCodeEntity.builder()
            .code("weeeeeeee")
            .amount(1)
            .facility(FacilityEntity.builder().name("gym").id(1L).build()).build();
    private final DiscountCodeEntity discountCodeEntity3 = DiscountCodeEntity.builder()
            .code("wooooooo")
            .amount(3)
            .facility(FacilityEntity.builder().name("opera").build()).build();
    private final DiscountCodeEntity discountCodeEntity4 = DiscountCodeEntity.builder()
            .code("jpjpjp420")
            .amount(7)
            .facility(FacilityEntity.builder().name("cinema").build()).build();

    @Before
    public void setup(){
        discountCodeRepository = mock(DiscountCodeRepository.class);
        discountCodeService = new DiscountCodeService(discountCodeRepository);
    }

    @Test
    public void getDiscountCodesTest(){
        discountCodeEntities.add(discountCodeEntity1);
        discountCodeEntities.add(discountCodeEntity2);
        discountCodeEntities.add(discountCodeEntity3);
        discountCodeEntities.add(discountCodeEntity4);

        Mockito.when(discountCodeRepository.findAll()).thenReturn(discountCodeEntities);

        List<DiscountCodeEntity> discountCodeEntityList = discountCodeService.getDiscountCodes();
        assertEquals(4,discountCodeEntityList.size());
        verify(discountCodeRepository, times(1)).findAll();
    }

    @Test
    public void getDiscountCodeByIdTest(){
        Mockito.when(discountCodeRepository.findDiscountCodeEntityById(1L)).thenReturn(discountCodeEntity1);
        Optional<DiscountCodeEntity> result = discountCodeService.getDiscountCode(1L);
        assertTrue(result.isPresent());
        assertEquals("hshduvwuevd", result.get().getCode());
    }

    @Test
    public void addDiscountCodeTest(){
        Mockito.when(discountCodeRepository.save(discountCodeEntity1)).thenReturn(discountCodeEntity1);
        Optional<DiscountCodeEntity> discountCodeEntity = discountCodeService.createDiscountCode(discountCodeEntity1);
        discountCodeEntity.ifPresent(codeEntity -> assertEquals("hshduvwuevd", codeEntity.getCode()));
    }

    @Test
    public void deleteDiscountCodeTest(){
        Mockito.when(discountCodeRepository.findDiscountCodeEntityById(1L)).thenReturn(discountCodeEntity1);
        doNothing().when(discountCodeRepository).delete(discountCodeEntity1);
        discountCodeService.deleteDiscountCode(1L);
        verify(discountCodeRepository, times(1)).delete(discountCodeEntity1);
    }

    @Test
    public void updateDiscountCodeTest(){
        Mockito.when(discountCodeRepository.findDiscountCodeEntityById(1L)).thenReturn(discountCodeEntity1);
        discountCodeService.updateDiscountCode(1L, discountCodeEntity1);
        verify(discountCodeRepository, times(1)).save(discountCodeEntity1);
    }

}
