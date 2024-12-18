package com.emse.SmartPlant.dao;

import com.emse.SmartPlant.model.PlantEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlantDao extends JpaRepository<PlantEntity, Long> {
    List<PlantEntity> findByName(String name);
    List<PlantEntity> findByPlantType(String plantType);

}

