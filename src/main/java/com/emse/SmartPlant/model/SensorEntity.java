package com.emse.SmartPlant.model;

import jakarta.persistence.*;


@Entity
@Table( name = "SP_SENSORS")
public class SensorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Génération automatique de l'ID
    private Long id;

    @Column(nullable = false, length = 255)
    private String name; // Nom du capteur


    private Double value;

    @Enumerated(EnumType.STRING)
    private SensorType sensorType;

    private Long plantId; // Utilisez Long au lieu de Double pour refléter une clé primaire

    // Constructeur par défaut
    public SensorEntity() {}

    // Constructeur avec paramètres
    public SensorEntity(Long id, String name, SensorType sensorType, Double value) {
        this.id = id;
        this.name = name;
        this.sensorType = sensorType;
        this.value = value;
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
