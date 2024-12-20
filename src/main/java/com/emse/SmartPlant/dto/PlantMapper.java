package com.emse.SmartPlant.dto;

import com.emse.SmartPlant.model.PlantEntity;

public class PlantMapper {
    public static Plant of(PlantEntity plant){
        return new Plant(
             plant.getId(),
             plant.getName(),
             plant.getPlantType(),
             plant.getCurrent_humidity(),
             plant.getCurrent_temperature(),
             plant.getCurrent_light(),
             plant.getMinHumidity(),
             plant.getMaxHumidity()
        );
    }
}
