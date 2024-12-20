package com.emse.SmartPlant.api;

import com.emse.SmartPlant.model.SensorType;

public record SensorCommand(String name, Double value, SensorType sensorType) {
}