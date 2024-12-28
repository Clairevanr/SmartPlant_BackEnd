package com.emse.SmartPlant.api;

import com.emse.SmartPlant.dao.SensorDao;
import com.emse.SmartPlant.dto.Sensor;
import com.emse.SmartPlant.dto.SensorMapper;
import com.emse.SmartPlant.model.SensorEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/api/sensors")
@Transactional
public class SensorController {

    private final SensorDao sensorDao;

    @Autowired
    public SensorController(SensorDao sensorDao) {
        this.sensorDao = sensorDao;
    }


    @GetMapping
    public List<Sensor> findAll() {
        return sensorDao.findAll()
                .stream()
                .map(SensorMapper::of)
                .sorted(Comparator.comparing(Sensor::name))
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/{id}") //find a sensor by its id
    public Sensor findById(@PathVariable Long id) {
        return sensorDao.findById(id).map(SensorMapper::of).orElse(null);
    }

    @PostMapping // create a sensor
    public ResponseEntity<Sensor> create(@RequestBody SensorCommand sensor) {
        SensorEntity entity = new SensorEntity(sensor.sensorType(), sensor.name());
        entity.setValue(sensor.value());
        SensorEntity saved = sensorDao.save(entity);
        return ResponseEntity.ok(SensorMapper.of(saved));
    }

    @PutMapping(path = "/{id}") // update a sensor
    public ResponseEntity<Sensor> update(@PathVariable Long id, @RequestBody SensorCommand sensor) {
        SensorEntity entity = sensorDao.findById(id).orElse(null);
        if (entity == null) {
            return ResponseEntity.badRequest().build();
        }
        entity.setValue(sensor.value());
        entity.setName(sensor.name());
        entity.setSensorType(sensor.sensorType());
        return ResponseEntity.ok(SensorMapper.of(entity));
    }

    @DeleteMapping(path = "/{id}") // delete a sensor
    public void delete(@PathVariable Long id) {
        sensorDao.deleteById(id);
    }
}
