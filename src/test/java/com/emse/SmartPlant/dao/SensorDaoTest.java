package com.emse.SmartPlant.dao;

import com.emse.SmartPlant.model.PlantEntity;
import com.emse.SmartPlant.model.SensorEntity;
import com.emse.SmartPlant.model.SensorType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
public class SensorDaoTest {

    @Autowired
    private SensorDao sensorDao;
    private PlantDao plantDao;

    @Test
    public void shouldFindASensorById() {
        SensorEntity sensor = sensorDao.getReferenceById(1L);
        Assertions.assertThat(sensor.getName()).isEqualTo("Temperature plant1");
        Assertions.assertThat(sensor.getValue()).isEqualTo(21.3);
        Assertions.assertThat(sensor.getSensorType()).isEqualTo(SensorType.TEMPERATURE);
    }

    @Test
    public void shouldFindSensorsByPlantId() {
        SensorEntity sensor = sensorDao.findByPlantId(1L).getFirst();
        Assertions.assertThat(sensor.getName()).isEqualTo("Temperature plant1");
        Assertions.assertThat(sensor.getValue()).isEqualTo(21.3);
        Assertions.assertThat(sensor.getSensorType()).isEqualTo(SensorType.TEMPERATURE);
    }

    @Test
    public void shouldFindASensorBySensorType() {
        SensorEntity sensor = sensorDao.findBySensorType(SensorType.TEMPERATURE).getFirst(); // Supposons que la méthode retourne une liste
        Assertions.assertThat(sensor.getName()).isEqualTo("Temperature plant1");
        Assertions.assertThat(sensor.getValue()).isEqualTo(21.3);
        Assertions.assertThat(sensor.getSensorType()).isEqualTo(SensorType.TEMPERATURE);
    }


    @Test
    public void shouldDeleteSensorByPlantId() {
        sensorDao.deleteByPlantId(1L);

        List<SensorEntity> sensors = sensorDao.findByPlantId(1L);
        Assertions.assertThat(sensors).isEmpty();
    }

}

