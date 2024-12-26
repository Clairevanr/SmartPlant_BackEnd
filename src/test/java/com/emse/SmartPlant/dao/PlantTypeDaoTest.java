package com.emse.SmartPlant.dao;

import com.emse.SmartPlant.model.PlantEntity;
import com.emse.SmartPlant.model.PlantTypeEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public class PlantTypeDaoTest {

    @Autowired
    private PlantTypeDao plantTypeDao;

    @Test
    public void shouldFindATypeByName() {
        PlantTypeEntity type = plantTypeDao.findByName("Succulent");
        Assertions.assertThat(type.getMinHumidity()).isEqualTo(15.0);
        Assertions.assertThat(type.getMaxHumidity()).isEqualTo(80.0);
    }


    @Query("select c from PlantTypeEntity c where c.name=:name")
    Optional<PlantTypeEntity> findByName(@Param("name") String name);

    @Modifying
    @Query("delete from PlantEntity c where c.name=:name")
    void deleteByName(String name);
}
