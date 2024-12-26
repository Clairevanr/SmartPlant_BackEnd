package com.emse.SmartPlant.dao;

import com.emse.SmartPlant.dto.PlantType;
import com.emse.SmartPlant.model.PlantEntity;
import com.emse.SmartPlant.model.PlantTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PlantTypeDao extends JpaRepository<PlantTypeEntity, Long> {

    @Query("select c from PlantTypeEntity c where c.name=:name")
    PlantTypeEntity findByName(@Param("name") String name);

    @Modifying
    @Query("delete from PlantEntity c where c.name=:name")
    void deleteByName(String name);
}
