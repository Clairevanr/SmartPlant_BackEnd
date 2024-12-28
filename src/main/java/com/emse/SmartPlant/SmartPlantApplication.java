package com.emse.SmartPlant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication(scanBasePackages = "com.emse.SmartPlant")
@EntityScan(basePackages = "com.emse.SmartPlant.model")

public class SmartPlantApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartPlantApplication.class, args);
	}

}
