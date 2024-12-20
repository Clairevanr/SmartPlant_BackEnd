package com.emse.SmartPlant.dto;

import com.emse.SmartPlant.model.SensorType;

public record Sensor(Long id, String name, Double value, SensorType sensorType, Long plantId) {
}
