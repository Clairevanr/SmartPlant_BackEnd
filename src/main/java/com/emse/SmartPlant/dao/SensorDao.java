package com.emse.SmartPlant.dao;


import com.emse.SmartPlant.model.PlantEntity;
import com.emse.SmartPlant.model.SensorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SensorDao extends JpaRepository<SensorEntity, Long> {
    List<SensorEntity> findBySensorType(String sensorType);
    List<SensorEntity> findByValueBetween(Double minValue, Double maxValue);
    List<SensorEntity> findByPlantId(Long plantId);
}
