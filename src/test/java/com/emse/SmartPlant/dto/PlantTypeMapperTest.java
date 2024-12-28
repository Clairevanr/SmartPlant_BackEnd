package com.emse.SmartPlant.dto;

import com.emse.SmartPlant.model.PlantTypeEntity;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PlantTypeMapperTest {

    @Test
    void testPlantTypeEntityToPlantTypeMappingWithRecursiveComparison() {
        // Mock PlantTypeEntity object
        PlantTypeEntity plantTypeEntity = new PlantTypeEntity();
        plantTypeEntity.setName("Tropical");
        plantTypeEntity.setMaxHumidity(90.0);
        plantTypeEntity.setMinHumidity(70.0);


        // Expected PlantType object
        PlantType expectedPlantType = new PlantType(
                "Tropical",
                90.0,
                70.0
        );

        // Mapping the entity to the DTO
        PlantType actualPlantType = PlantTypeMapper.of(plantTypeEntity);

        // Using recursive assertion
        assertThat(actualPlantType).usingRecursiveAssertion().isEqualTo(expectedPlantType);
    }
}
