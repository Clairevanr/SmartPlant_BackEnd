package com.emse.SmartPlant.model;


import jakarta.persistence.*;

@Entity
@Table(name = "SP_PLANT_TYPE")
public class PlantTypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 255)
    private String name; // Type de plante (ex: succulent, fern)

    private Double minHumidity; // Humidité minimale

    private Double maxHumidity; // Humidité maximale

    private Double minTemperature;

    private Double maxTemperature;

    // Constructeurs
    public PlantTypeEntity() {}

    public PlantTypeEntity(String name, Double minHumidity, Double maxHumidity, Double minTemperature, Double maxTemperature) {
        this.name = name;
        this.minHumidity = minHumidity;
        this.maxHumidity = maxHumidity;
        this.minTemperature = minTemperature;
        this.maxTemperature = maxTemperature;
    }

    // Getters et setters
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

    public Double getMinHumidity() {
        return minHumidity;
    }

    public void setMinHumidity(Double minHumidity) {
        this.minHumidity = minHumidity;
    }

    public Double getMaxHumidity() {
        return maxHumidity;
    }

    public void setMaxHumidity(Double maxHumidity) {
        this.maxHumidity = maxHumidity;
    }

    public Double getMinTemperature() {
        return minTemperature;
    }
    public void setMinTemperature(Double minTemperature) {
        this.minTemperature = minTemperature;
    }
    public Double getMaxTemperature() {
        return maxTemperature;
    }
    public void setMaxTemperature(Double maxTemperature) {
        this.maxTemperature = maxTemperature;
    }
}

