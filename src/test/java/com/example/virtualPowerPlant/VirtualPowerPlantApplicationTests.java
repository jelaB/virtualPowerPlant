package com.example.virtualPowerPlant;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.virtualPowerPlant.controller.VirtualPowerPlantController;
import com.example.virtualPowerPlant.repository.BatteryRepository;
import com.example.virtualPowerPlant.service.BatteryService;

@SpringBootTest
class VirtualPowerPlantApplicationTests {

	@Autowired
	private BatteryService batteryService;
	@Autowired
	private BatteryRepository batteryRepository;
	@Autowired
	private VirtualPowerPlantController virtualPowerPlantController;

	@Test
	void contextLoads() {
		assertThat(batteryService).isNotNull();
		assertThat(batteryRepository).isNotNull();
		assertThat(virtualPowerPlantController).isNotNull();
	}

}
