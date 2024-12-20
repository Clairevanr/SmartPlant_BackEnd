package com.emse.SmartPlant.dao;

import com.emse.SmartPlant.model.PlantEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlantDao extends JpaRepository<PlantEntity, Long> {

    @Query("select c from PlantEntity c where c.name=:name")
    PlantEntity findByName(@Param("name") String name);

    @Query("select c from PlantEntity c where c.plantType=:plantType")
    List<PlantEntity> findByPlantType(@Param("plantType") String plantType);

}

