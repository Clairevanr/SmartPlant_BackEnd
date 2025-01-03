package com.emse.SmartPlant.dao;


import com.emse.SmartPlant.model.SensorEntity;
import com.emse.SmartPlant.model.SensorType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SensorDao extends JpaRepository<SensorEntity, Long> {

    @Query("select c from SensorEntity c where c.sensorType=:sensorType ")
    List<SensorEntity> findBySensorType(@Param("sensorType") SensorType sensorType);

    @Query("select c from SensorEntity c where c.plantId=:plantId ")
    List<SensorEntity> findByPlantId(Long plantId);

    @Modifying
    @Query("delete from SensorEntity c where c.plantId=:plantId ")
    void deleteByPlantId(Long plantId);
}
