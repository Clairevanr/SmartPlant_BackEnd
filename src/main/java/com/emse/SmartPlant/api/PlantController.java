package com.emse.SmartPlant.api;

import com.emse.SmartPlant.dao.PlantDao;
import com.emse.SmartPlant.dao.SensorDao;
import com.emse.SmartPlant.dto.Plant;
import com.emse.SmartPlant.dto.PlantMapper;
import com.emse.SmartPlant.model.PlantEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class PlantController {

    private final PlantDao plantdao;
    private final SensorDao sensordao;

    public PlantController(PlantDao plantdao, SensorDao sensordao) {
        this.plantdao = plantdao;
        this.sensordao = sensordao;
    }

    // Return the list of plants
    @GetMapping
    public List<Plant> findAll() {
        return plantdao.findAll()
                .stream()
                .map(PlantMapper::of)
                .sorted(Comparator.comparing(Plant::name))
                .collect(Collectors.toList());
    }

    // Returns a plant found by its ID
    @GetMapping(path = "/{id}")
    public Plant findById(@PathVariable Long id) {
        return plantdao.findById(id).map(PlantMapper::of).orElse(null);
    }


    // Delete the plant by its ID
    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable Long id) {
        plantdao.deleteById(id);
        //sensordao.deleteByPlantId(id); // à implémenter

    }



}
