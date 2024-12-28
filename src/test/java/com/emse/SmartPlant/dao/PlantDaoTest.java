package com.emse.SmartPlant.dao;


import com.emse.SmartPlant.model.PlantEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;




import java.util.List;

@DataJpaTest
public class PlantDaoTest {

    @Autowired
    private PlantDao plantDao;

    @Test
    public void shouldFindAPlantById() {
        PlantEntity plant = plantDao.getReferenceById(1L);
        Assertions.assertThat(plant.getName()).isEqualTo("Ernest");
        Assertions.assertThat(plant.getPlantType()).isEqualTo("Succulent");
    }

    @Test
    public void shouldFindAPlantByName() {
        PlantEntity plant = plantDao.findByName("Marcel");
        Assertions.assertThat(plant.getId()).isEqualTo(2L);
        Assertions.assertThat(plant.getPlantType()).isEqualTo("Monstera");
    }

    @Test
    public void shouldFindAPlantByPlantType() {
        PlantEntity plant = plantDao.findByPlantType("Monstera").getFirst();
        Assertions.assertThat(plant.getId()).isEqualTo(2L);
        Assertions.assertThat(plant.getName()).isEqualTo("Marcel");
    }

    @Test
    public void shouldDeletePlantByPlantType() {
        plantDao.deleteByPlantType("Succulent");

        List<PlantEntity> plants = plantDao.findByPlantType("Succulent");
        Assertions.assertThat(plants).isEmpty();
    }


}

