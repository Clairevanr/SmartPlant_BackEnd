package com.emse.SmartPlant.model;

import jakarta.persistence.*;


@Entity
@Table(name = "SP_SENSORS")
public class SensorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String name; // Sensor name


    private Double value;

    @Enumerated(EnumType.STRING)
    private SensorType sensorType;

    private Long plantId;

    // Default constructor
    public SensorEntity() {}

    // Constructor
    public SensorEntity(Long id, String name, SensorType sensorType, Double value) {
        this.id = id;
        this.name = name;
        this.sensorType = sensorType;
        this.value = value;
    }

    //  Constructor defined by name and Type
    public SensorEntity(SensorType sensorType, String name) {
        this.sensorType = sensorType;
        this.name = name;
    }

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public SensorType getSensorType() {
        return sensorType;
    }

    public void setSensorType(SensorType sensorType) {
        this.sensorType = sensorType;
    }

    public Long getPlantId() {
        return plantId;
    }

    public void setPlantId(Long plantId) {
        this.plantId = plantId;
    }
}
