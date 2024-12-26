-- Table SENSORS
INSERT INTO SP_SENSORS (id, name, sensor_type, sensor_value, plant_id) VALUES
                                                      (1, 'Temperature plant1', 'TEMPERATURE', 21.3,1),
                                                      (2, 'Humidity plant1', 'HUMIDITY', 30.0,1),
                                                      (3, 'Light plant1', 'LIGHT', 0.0,1),
                                                      (4, 'Temperature plant2', 'TEMPERATURE', 0.0,2),
                                                      (5, 'Humidity plant2', 'HUMIDITY', 40.0,2),
                                                      (6, 'Light plant2', 'LIGHT', 12.2,2);

-- Table PLANTS
INSERT INTO SP_PLANT (id, name, plant_type) VALUES
                                                     (1, 'Ernest', 'Succulent'),
                                                     (2, 'Marcel', 'Monstera');

-- Table PLANT TYPE
INSERT INTO SP_PLANT_TYPE (name, min_humidity, max_humidity) VALUES
                                                     ('Succulent',  15.0, 80.0),
                                                     ('Monstera', 50.0, 90.0);
