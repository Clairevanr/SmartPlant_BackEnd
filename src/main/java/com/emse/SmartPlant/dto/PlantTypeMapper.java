package com.emse.SmartPlant.dto;

import com.emse.SmartPlant.model.PlantTypeEntity;

public class PlantTypeMapper {
    public static PlantType of(PlantTypeEntity type) {
        return new PlantType(
                type.getName(),
                type.getMaxHumidity(),
                type.getMinHumidity()
        );
    }
}
