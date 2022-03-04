package dk.bec.bookanything.services;

import dk.bec.bookanything.mapper.BookableObjectMapper;
import dk.bec.bookanything.model.FeatureEntity;
import dk.bec.bookanything.repository.FeatureRepository;
import dk.bec.bookanything.service.FeatureService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class FeatureServiceTest {

    FeatureService featureService;
    FeatureRepository featureRepository;

    private final FeatureEntity featureEntity1 = FeatureEntity.builder()
            .id(1L)
            .name("feature1")
            .description("description1").build();

    private final FeatureEntity featureEntity2 = FeatureEntity.builder()
            .id(1L)
            .name("feature2")
            .description("description2").build();

    @Before
    public void setup(){
        featureRepository = mock(FeatureRepository.class);
        featureService = new FeatureService(featureRepository, new BookableObjectMapper(featureRepository));
    }

    @Test
    public void getFeatureByIdTest(){
        Mockito.when(featureRepository.findById(1L)).thenReturn(Optional.ofNullable(featureEntity1));
        Optional<FeatureEntity> result = featureService.getFeatureById(1L);
        assertTrue(result.isPresent());
        assertEquals("feature1", result.get().getName());
    }

    @Test
    public void addFeatureTest(){
         featureService.createFeature(featureEntity1);
         verify(featureRepository, times(1)).save(featureEntity1);
    }

    @Test
    public void modifyFeatureTest(){
        Mockito.when(featureRepository.findById(1L)).thenReturn(Optional.ofNullable(featureEntity1));
        featureService.updateFeatureObject(featureEntity2,1L);
        verify(featureRepository, times(1)).save(featureEntity2);
    }

    @Test
    public void deleteFeatureTest(){
        doNothing().when(featureRepository).deleteById(1L);
        featureService.deleteFeatureById(1L);
        verify(featureRepository,times(1)).deleteById(1L);
    }



}
