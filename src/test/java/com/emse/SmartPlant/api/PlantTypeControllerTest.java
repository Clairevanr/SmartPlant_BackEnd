package com.emse.SmartPlant.api;


import com.emse.SmartPlant.dao.PlantDao;
import com.emse.SmartPlant.dao.PlantTypeDao;
import com.emse.SmartPlant.model.PlantTypeEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class PlantTypeControllerTest {

    private MockMvc mockMvc;
    private PlantTypeDao typedao;
    private PlantDao plantdao;
    private PlantTypeEntity plantTypeEntity;

    @BeforeEach
    void setUp() {
        // Create mock dependencies
        typedao = mock(PlantTypeDao.class);
        plantdao = mock(PlantDao.class);

        // Initialize controller with mocks
        PlantTypeController controller = new PlantTypeController(typedao, plantdao);

        // Set up MockMvc with the controller
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        // Sample data
        plantTypeEntity = new PlantTypeEntity("Fern", 30.0, 70.0);
    }

    @Test
    void testFindAll() throws Exception {
        when(typedao.findAll()).thenReturn(Arrays.asList(plantTypeEntity));

        mockMvc.perform(get("/api/types"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Fern"))
                .andExpect(jsonPath("$[0].min_humidity").value(30))
                .andExpect(jsonPath("$[0].max_humidity").value(70));
    }

    @Test
    void testFindByName() throws Exception {
        when(typedao.findByName("Fern")).thenReturn(plantTypeEntity);

        mockMvc.perform(get("/api/types/Fern"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Fern"))
                .andExpect(jsonPath("$.min_humidity").value(30))
                .andExpect(jsonPath("$.max_humidity").value(70));
    }

    @Test
    void testFindByName_NotFound() throws Exception {
        when(typedao.findByName("Cactus")).thenReturn(null);

        mockMvc.perform(get("/api/types/Cactus"))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }

    @Test
    void testDelete() throws Exception {
        doNothing().when(typedao).deleteByName("Fern");
        doNothing().when(plantdao).deleteByPlantType("Fern");

        mockMvc.perform(delete("/api/types/Fern"))
                .andExpect(status().isOk());

        verify(typedao, times(1)).deleteByName("Fern");
        verify(plantdao, times(1)).deleteByPlantType("Fern");
    }

    @Test
    void testCreate() throws Exception {
        PlantTypeCommand command = new PlantTypeCommand("Fern", 70.0, 30.0);
        when(typedao.save(Mockito.any(PlantTypeEntity.class))).thenReturn(plantTypeEntity);

        mockMvc.perform(post("/api/types")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "name": "Fern",
                                    "min_humidity": 30.0,
                                    "max_humidity": 70.0
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Fern"))
                .andExpect(jsonPath("$.min_humidity").value(30.0))
                .andExpect(jsonPath("$.max_humidity").value(70.0));
    }

    @Test
    void testUpdate() throws Exception {
        when(typedao.findByName("Fern")).thenReturn(plantTypeEntity);

        mockMvc.perform(put("/api/types/Fern")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "name": "Fern",
                                    "min_humidity": 35.0,
                                    "max_humidity": 75.0
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Fern"))
                .andExpect(jsonPath("$.min_humidity").value(35.0))
                .andExpect(jsonPath("$.max_humidity").value(75.0));
    }

    @Test
    void testUpdate_NotFound() throws Exception {
        when(typedao.findByName("Cactus")).thenReturn(null);

        mockMvc.perform(put("/api/types/Cactus")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "name": "Cactus",
                                    "min_humidity": 35.0,
                                    "max_humidity": 75.0
                                }
                                """))
                .andExpect(status().isNotFound());
    }
}
