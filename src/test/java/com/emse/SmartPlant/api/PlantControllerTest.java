package com.emse.SmartPlant.api;

import com.emse.SmartPlant.dao.PlantDao;
import com.emse.SmartPlant.dao.SensorDao;
import com.emse.SmartPlant.dto.Plant;
import com.emse.SmartPlant.model.PlantEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class PlantControllerTest {

    private MockMvc mockMvc;

    @Mock
    private PlantDao plantDao;

    @Mock
    private SensorDao sensorDao;

    @InjectMocks
    private PlantController plantController;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(plantController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void testFindAllPlants() throws Exception {
        // Prepare mock data
        PlantEntity plantEntity = new PlantEntity("Rose", "Flower");
        when(plantDao.findAll()).thenReturn(Collections.singletonList(plantEntity));

        // Perform GET request
        mockMvc.perform(get("/api/plants"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Rose"))
                .andExpect(jsonPath("$[0].plantType").value("Flower"));
    }

    @Test
    void testFindPlantById() throws Exception {
        // Prepare mock data
        PlantEntity plantEntity = new PlantEntity("Rose", "Flower");
        when(plantDao.findById(1L)).thenReturn(Optional.of(plantEntity));

        // Perform GET request
        mockMvc.perform(get("/api/plants/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Rose"))
                .andExpect(jsonPath("$.plantType").value("Flower"));
    }

    @Test
    void testCreatePlant() throws Exception {
        // Prepare mock data
        PlantEntity plantEntity = new PlantEntity("Rose", "Flower");
        when(plantDao.save(any(PlantEntity.class))).thenReturn(plantEntity);

        // Prepare request body
        String plantJson = objectMapper.writeValueAsString(new PlantCommand("Rose", "Flower"));

        // Perform POST request
        mockMvc.perform(post("/api/plants")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(plantJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Rose"))
                .andExpect(jsonPath("$.plantType").value("Flower"));
    }

    @Test
    void testUpdatePlant() throws Exception {
        // Prepare mock data
        PlantEntity plantEntity = new PlantEntity("Rose", "Flower");
        when(plantDao.findById(1L)).thenReturn(Optional.of(plantEntity));
        when(plantDao.save(any(PlantEntity.class))).thenReturn(plantEntity);

        // Prepare request body
        String plantJson = objectMapper.writeValueAsString(new PlantCommand("Tulip", "Flower"));

        // Perform PUT request
        mockMvc.perform(put("/api/plants/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(plantJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Tulip"))
                .andExpect(jsonPath("$.plantType").value("Flower"));
    }

    @Test
    void testDeletePlant() throws Exception {
        // Perform DELETE request
        mockMvc.perform(delete("/api/plants/1"))
                .andExpect(status().isOk());

        // Verify interactions
        verify(plantDao, times(1)).deleteById(1L);
        verify(sensorDao, times(1)).deleteByPlantId(1L);
    }

    @Test
    void testPlantNotFound() throws Exception {
        // Prepare mock data
        when(plantDao.findById(1L)).thenReturn(Optional.empty());

        // Perform GET request
        mockMvc.perform(get("/api/plants/1"))
                .andExpect(status().isNotFound());
    }
}
