-- Table SENSORS
INSERT INTO SP_SENSORS ( name, sensor_type, sensor_value, plant_id) VALUES
                                                      ( 'Temperature plant1', 'TEMPERATURE', 21.3,1),
                                                      ( 'Humidity plant1', 'HUMIDITY', 30.0,1),
                                                      ( 'Light plant1', 'LIGHT', 0.0,1),
                                                      ( 'Temperature plant2', 'TEMPERATURE', 0.0,2),
                                                      ( 'Humidity plant2', 'HUMIDITY', 40.0,2),
                                                      ( 'Light plant2', 'LIGHT', 12.2,2);

-- Table PLANTS
INSERT INTO SP_PLANT (name, plant_type) VALUES
                                                     ( 'Ernest', 'Succulent'),
                                                     ( 'Marcel', 'Monstera');

-- Table PLANT TYPE
INSERT INTO SP_PLANT_TYPE (name, min_humidity, max_humidity, min_temperature, max_temperature) VALUES
                                                     ('Succulent',  15.0, 80.0, 15.0, 40.0),
                                                     ('Monstera', 50.0, 90.0, 20.0, 40.0);
