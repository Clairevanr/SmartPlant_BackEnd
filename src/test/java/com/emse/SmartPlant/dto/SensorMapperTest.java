package com.emse.SmartPlant.dto;

import com.emse.SmartPlant.model.SensorEntity;
import com.emse.SmartPlant.model.SensorType;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SensorMapperTest {

    @Test
    void testSensorEntityToSensorMappingWithRecursiveComparison() {
        // A mock SensorEntity
        SensorEntity sensorEntity = new SensorEntity();
        sensorEntity.setId(10L);
        sensorEntity.setName("Plant3 Temperature");
        sensorEntity.setSensorType(SensorType.TEMPERATURE);
        sensorEntity.setValue(22.5);
        sensorEntity.setPlantId(3L);

        // The expected sensor
        Sensor expectedSensor = new Sensor(
                10L,
                "Plant3 Temperature",
                22.5,
                SensorType.TEMPERATURE,
                3L
        );

        // Mapping the Entity to the DTO
        Sensor actualSensor = SensorMapper.of(sensorEntity);

        //  Using recursive assertion
        assertThat(actualSensor).usingRecursiveAssertion().isEqualTo(expectedSensor);
    }
}
