package com.emse.SmartPlant.api;

public record PlantTypeCommand(String name,Double max_humidity, Double min_humidity, Double max_temperature, Double min_temperature ){
}
