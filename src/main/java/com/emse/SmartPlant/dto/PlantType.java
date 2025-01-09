package com.emse.SmartPlant.dto;

public record PlantType(String name, Double max_humidity, Double min_humidity, Double min_temperature, Double max_temperature) {
}
