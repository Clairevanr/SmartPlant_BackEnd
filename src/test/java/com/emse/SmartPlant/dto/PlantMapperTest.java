package com.emse.SmartPlant.dto;


import com.emse.SmartPlant.model.PlantEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PlantMapperTest {

    @Test
    void testPlantEntityToPlantMapping() {
        // Arrange: Create a mock PlantEntity
        PlantEntity plantEntity = new PlantEntity();
        plantEntity.setId(1L);
        plantEntity.setName("Clara");
        plantEntity.setPlantType("Monstera");
        plantEntity.setCurrent_humidity(60.5);
        plantEntity.setCurrent_temperature(22.3);
        plantEntity.setCurrent_light(30.0);

        // Act: Map to Plant DTO
        Plant actualPlant = PlantMapper.of(plantEntity);

        // Definition of the expected plant

        Plant expectedPlant = new Plant(
                1L,
                "Clara",
                "Monstera",
                60.5,
                22.3,
                30.0
        );

        Assertions.assertThat(actualPlant).usingRecursiveAssertion().isEqualTo(expectedPlant);


    }

}
