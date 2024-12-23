package com.emse.SmartPlant.api;

import com.emse.SmartPlant.dao.PlantDao;
import com.emse.SmartPlant.dao.PlantTypeDao;
import com.emse.SmartPlant.dao.SensorDao;
import com.emse.SmartPlant.dto.Plant;
import com.emse.SmartPlant.dto.PlantMapper;
import com.emse.SmartPlant.dto.PlantType;
import com.emse.SmartPlant.dto.PlantTypeMapper;
import com.emse.SmartPlant.model.PlantEntity;
import com.emse.SmartPlant.model.PlantTypeEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class PlantTypeController {

    private final PlantTypeDao typedao;
    private final PlantDao plantdao;

    public PlantTypeController(PlantTypeDao typedao, PlantDao plantdao) {
        this.typedao = typedao;
        this.plantdao = plantdao;
    }

    // Return the list of plants types
    @GetMapping
    public List<PlantType> findAll() {
        return typedao.findAll()
                .stream()
                .map(PlantTypeMapper::of)
                .sorted(Comparator.comparing(PlantType::name))
                .collect(Collectors.toList());
    }

    // Returns a plant found by its name
    @GetMapping(path = "/{name}")
    public PlantType findByName(@PathVariable String name) {
        return typedao.findByName(name).map(PlantTypeMapper::of).orElse(null);
    }


    // Delete the plant type by its ID
    @DeleteMapping(path = "/{name}")
    public void delete(@PathVariable String name) {
        typedao.deleteByName(name);
        plantdao.deleteByPlantType(name);

    }

    // Create a plant type
    @PostMapping
    public ResponseEntity<PlantType> create(@RequestBody PlantTypeCommand type) { // (9)
        PlantTypeEntity entity = new PlantTypeEntity(type.name(),type.min_humidity(),type.max_humidity());
        PlantTypeEntity saved = typedao.save(entity);
        return ResponseEntity.ok(PlantTypeMapper.of(saved));
    }

    // Update a plant type (with max and min humidity)
    @PutMapping(path = "/{name}")
    public ResponseEntity<PlantType> update(@PathVariable String name, @RequestBody PlantTypeCommand type) {
        PlantTypeEntity entity = typedao.findByName(name).orElse(null);
        if (entity == null) {
            return ResponseEntity.badRequest().build();
        }
        entity.setMaxHumidity(type.max_humidity());
        entity.setMinHumidity(type.min_humidity());

        return ResponseEntity.ok(PlantTypeMapper.of(entity));
    }


}
