package com.emse.SmartPlant.dto;

import com.emse.SmartPlant.model.SensorType;

public record Plant(Long id, String name, String plantType,Double current_humidity,Double current_temperature, Double current_Light) {
}
