package com.emse.SmartPlant.api;

import com.emse.SmartPlant.dao.PlantDao;
import com.emse.SmartPlant.dao.PlantTypeDao;
import com.emse.SmartPlant.dto.PlantType;
import com.emse.SmartPlant.dto.PlantTypeMapper;
import com.emse.SmartPlant.model.PlantTypeEntity;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/api/types")
@Transactional
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
        PlantTypeEntity entity = typedao.findByName(name);
        return entity != null ? PlantTypeMapper.of(entity) : null;
    }


    // Delete the plant type by its name
    @DeleteMapping(path = "/{name}")
    public void delete(@PathVariable String name) {
        typedao.deleteByName(name);
        plantdao.deleteByPlantType(name);

    }

    // Create a plant type
    @PostMapping
    public ResponseEntity<PlantType> create(@RequestBody PlantTypeCommand type) { // (9)
        PlantTypeEntity entity = new PlantTypeEntity(type.name(),type.min_humidity(),type.max_humidity(), type.min_temperature(),type.max_temperature());
        PlantTypeEntity saved = typedao.save(entity);
        return ResponseEntity.ok(PlantTypeMapper.of(saved));
    }

    // Update a plant type (with max and min humidity)
    @PutMapping(path = "/{name}")
    public ResponseEntity<PlantType> update(@PathVariable String name, @RequestBody PlantTypeCommand type) {
        PlantTypeEntity entity = typedao.findByName(name);
        if (entity == null) {
            return ResponseEntity.notFound().build();
        }
        entity.setMaxHumidity(type.max_humidity());
        entity.setMinHumidity(type.min_humidity());
        entity.setMinTemperature(type.min_temperature());
        entity.setMaxTemperature(type.max_temperature());


        return ResponseEntity.ok(PlantTypeMapper.of(entity));
    }


}
