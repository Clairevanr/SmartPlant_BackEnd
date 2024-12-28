package com.emse.SmartPlant.dao;


import com.emse.SmartPlant.model.PlantTypeEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import static org.junit.jupiter.api.Assertions.assertFalse;


@DataJpaTest
public class PlantTypeDaoTest {

    @Autowired
    private PlantTypeDao plantTypeDao;

    @Test
    public void shouldFindATypeByName() {
        PlantTypeEntity type = plantTypeDao.findByName("Succulent");
        Assertions.assertThat(type.getMinHumidity()).isEqualTo(15.0);
        Assertions.assertThat(type.getMaxHumidity()).isEqualTo(80.0);
    }

    @Test
    public void shouldDeleteATypeByName() {
        plantTypeDao.deleteByName("Monstera");

        boolean exists = plantTypeDao.existsByName("Monstera");
        assertFalse(exists);

    }



}
