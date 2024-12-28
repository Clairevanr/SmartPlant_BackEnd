package com.emse.SmartPlant.api;

import com.emse.SmartPlant.dao.PlantDao;
import com.emse.SmartPlant.dao.SensorDao;
import com.emse.SmartPlant.dto.Plant;
import com.emse.SmartPlant.dto.PlantMapper;
import com.emse.SmartPlant.model.PlantEntity;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/api/plants")
@Transactional
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
    public ResponseEntity<Plant> findById(@PathVariable Long id) {
        return plantdao.findById(id)
                .map(plant -> ResponseEntity.ok(PlantMapper.of(plant))) // Return 200 OK with the plant
                .orElse(ResponseEntity.notFound().build()); // Return 404 Not Found if no plant is found
    }


    // Delete the plant by its ID
    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable Long id) {
        plantdao.deleteById(id);
        sensordao.deleteByPlantId(id);

    }

    // Create a plant
    @PostMapping
    public ResponseEntity<Plant> create(@RequestBody PlantCommand plant) { // (9)
        PlantEntity entity = new PlantEntity(plant.name(),plant.plantType());
        PlantEntity saved = plantdao.save(entity);
        return ResponseEntity.ok(PlantMapper.of(saved));
    }

    // Update a plant
    @PutMapping(path = "/{id}")
    public ResponseEntity<Plant> update(@PathVariable Long id, @RequestBody PlantCommand plant) {
        PlantEntity entity = plantdao.findById(id).orElse(null);
        if (entity == null) {
            return ResponseEntity.badRequest().build();
        }
        entity.setName(plant.name());
        entity.setPlantType(plant.plantType());

        return ResponseEntity.ok(PlantMapper.of(entity));
    }




}
