package com.emse.SmartPlant.api;

import com.emse.SmartPlant.dao.SensorDao;
import com.emse.SmartPlant.model.SensorEntity;
import com.emse.SmartPlant.model.SensorType;
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

class SensorControllerTest {

    private MockMvc mockMvc;

    @Mock
    private SensorDao sensorDao;

    @InjectMocks
    private SensorController sensorController;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(sensorController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void testFindAllSensors() throws Exception {
        // Prepare mock data
        SensorEntity sensorEntity = new SensorEntity(SensorType.TEMPERATURE, "Sensor1");
        sensorEntity.setValue(25.0);
        when(sensorDao.findAll()).thenReturn(Collections.singletonList(sensorEntity));

        // Perform GET request
        mockMvc.perform(get("/api/sensors"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Sensor1"))
                .andExpect(jsonPath("$[0].sensorType").value(SensorType.TEMPERATURE.toString()))
                .andExpect(jsonPath("$[0].value").value(25.0));
    }

    @Test
    void testFindSensorById() throws Exception {
        // Prepare mock data
        SensorEntity sensorEntity = new SensorEntity(SensorType.TEMPERATURE, "Sensor1");
        sensorEntity.setValue(25.0);
        when(sensorDao.findById(1L)).thenReturn(Optional.of(sensorEntity));

        // Perform GET request
        mockMvc.perform(get("/api/sensors/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Sensor1"))
                .andExpect(jsonPath("$.sensorType").value(SensorType.TEMPERATURE.toString()))
                .andExpect(jsonPath("$.value").value(25.0));
    }

    @Test
    void testFindSensorById_NotFound() throws Exception {
        // Prepare mock data
        when(sensorDao.findById(1L)).thenReturn(Optional.empty());

        // Perform GET request
        mockMvc.perform(get("/api/sensors/1"))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }

    @Test
    void testCreateSensor() throws Exception {
        // Prepare mock data
        SensorEntity sensorEntity = new SensorEntity(SensorType.HUMIDITY, "Sensor2");
        sensorEntity.setValue(60.0);
        when(sensorDao.save(any(SensorEntity.class))).thenReturn(sensorEntity);

        // Prepare request body
        String sensorJson = objectMapper.writeValueAsString(new SensorCommand("Sensor2", 60.0, SensorType.HUMIDITY ));

        // Perform POST request
        mockMvc.perform(post("/api/sensors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(sensorJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Sensor2"))
                .andExpect(jsonPath("$.sensorType").value(SensorType.HUMIDITY.toString()))
                .andExpect(jsonPath("$.value").value(60.0));
    }

    @Test
    void testUpdateSensor() throws Exception {
        // Prepare mock data
        SensorEntity sensorEntity = new SensorEntity(SensorType.TEMPERATURE, "Sensor1");
        sensorEntity.setValue(25.0);
        when(sensorDao.findById(1L)).thenReturn(Optional.of(sensorEntity));
        when(sensorDao.save(any(SensorEntity.class))).thenReturn(sensorEntity);

        // Prepare request body
        String sensorJson = objectMapper.writeValueAsString(new SensorCommand("UpdatedSensor", 100.0, SensorType.LIGHT));

        // Perform PUT request
        mockMvc.perform(put("/api/sensors/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(sensorJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("UpdatedSensor"))
                .andExpect(jsonPath("$.sensorType").value(SensorType.LIGHT.toString()))
                .andExpect(jsonPath("$.value").value(100.0));
    }

    @Test
    void testUpdateSensor_NotFound() throws Exception {
        // Prepare mock data
        when(sensorDao.findById(1L)).thenReturn(Optional.empty());

        // Prepare request body
        String sensorJson = objectMapper.writeValueAsString(new SensorCommand("NonExistentSensor", 0.0, null));

        // Perform PUT request
        mockMvc.perform(put("/api/sensors/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(sensorJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testDeleteSensor() throws Exception {
        // Perform DELETE request
        mockMvc.perform(delete("/api/sensors/1"))
                .andExpect(status().isOk());

        // Verify interactions
        verify(sensorDao, times(1)).deleteById(1L);
    }
}
