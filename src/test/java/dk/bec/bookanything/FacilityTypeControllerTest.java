package dk.bec.bookanything;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import dk.bec.bookanything.controller.FacilityTypeController;
import dk.bec.bookanything.model.FacilityTypeEntity;
import dk.bec.bookanything.service.FacilityTypeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(FacilityTypeController.class)
public class FacilityTypeControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    FacilityTypeService facilityTypeService;

    private static final List<FacilityTypeEntity> facilityTypes = Arrays.asList(
            new FacilityTypeEntity("restaurant"),
            new FacilityTypeEntity("gym"),
            new FacilityTypeEntity("cinema"),
            new FacilityTypeEntity("opera")
    );

    @Test
    void getAllFacilityTypes() throws Exception {

        Mockito.when(facilityTypeService.getFacilityTypes()).thenReturn(facilityTypes);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/API/facility-types")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(4)))
                .andExpect(jsonPath("$[2].name", is("cinema")));
    }

//    @Test
//    void getFacilityTypeById() throws Exception {
//        Mockito.when(facilityTypeService.getFacilityTypeById(2L)).thenReturn(facilityTypes.get(1));
//        String name = "gym";
//
//        mockMvc.perform(MockMvcRequestBuilders
//                .get("/API/facility-types/2")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$[0].name", is(name)));
//    }



    @Test
    void addFacilityType() throws Exception{
        FacilityTypeEntity facilityTypeEntity = facilityTypes.get(1);
        Mockito.when(facilityTypeService.addFacilityType(facilityTypeEntity)).thenReturn(facilityTypeEntity);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/API/facility-types")
                .content(mapToJson(facilityTypeEntity))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void addFacilityTypeBadRequest() throws Exception{
        FacilityTypeEntity facilityTypeEntity = facilityTypes.get(1);
        Mockito.when(facilityTypeService.addFacilityType(facilityTypeEntity)).thenThrow(new RuntimeException());

        mockMvc.perform(MockMvcRequestBuilders
                .post("/API/facility-types")
                .content(mapToJson(facilityTypeEntity))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void deleteFacilityType() throws Exception{
        FacilityTypeEntity facilityTypeEntity = facilityTypes.get(1);

        mockMvc.perform(MockMvcRequestBuilders
                .delete("/API/facility-types/1"))
                .andExpect(status().isOk());
    }

    @Test
    void updateFeatureType() throws Exception{
        FacilityTypeEntity facilityTypeEntity = facilityTypes.get(1);

        mockMvc.perform(MockMvcRequestBuilders
                .put("/API/facility-types/3")
                .content(mapToJson(facilityTypeEntity))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


    static String mapToJson(Object object) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        return ow.writeValueAsString(object);
    }
}
