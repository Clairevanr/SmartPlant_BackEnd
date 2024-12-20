package com.emse.SmartPlant.dto;

import com.emse.SmartPlant.model.SensorEntity;

public class SensorMapper {
    public static Sensor of(SensorEntity sensor){
        return new Sensor(
                sensor.getId(),
                sensor.getName(),
                sensor.getValue(),
                sensor.getSensorType(),
                sensor.getPlantId()
        );
    }
}
