INSERT INTO SP_PLANT (id, name, plant_type, min_humidity, max_humidity)
VALUES (-10, 'Ernest', 'Succulent', 0.02, 0.07),
       (-9, 'Marcel', 'Monstera', 0.06, 0.09);

INSERT INTO SP_SENSORS (id, name, sensor_type, sensor_value, plant_id)
VALUES (1, 'Temperature Plant1', 'TEMPERATURE', 21.3, -10),
       (2, 'Humidity Plant1', 'HUMIDITY', 0.03, -10),
       (3, 'Light Plant1', 'LIGHT', 0.0, -10),
       (4, 'Temperature Plant2', 'TEMPERATURE', 0.0, -9),
       (5, 'Humidity Plant2', 'HUMIDITY', 0.04, -9),
       (6, 'Light Plant2', 'LIGHT', 0.0, -9);
