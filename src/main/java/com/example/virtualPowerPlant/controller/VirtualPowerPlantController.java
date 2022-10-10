package com.example.virtualPowerPlant.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.virtualPowerPlant.dto.PostcodeAreaBatteries;
import com.example.virtualPowerPlant.model.Battery;
import com.example.virtualPowerPlant.service.BatteryService;

@RestController
@CrossOrigin("*")
public class VirtualPowerPlantController {

	@Autowired
	private BatteryService batteryService;

	private static final String errorDatabaseInsert = "Problem with inserting data into database.";
	private static final String errorPostcodeSelection = "Wrong parameters! 1st parameter should be upper bound, 2nd lower bound";

	@PostMapping("/storeBatteries")
	public ResponseEntity<Object> storeBateries(@RequestBody List<Battery> batteries) {
		boolean successfull = batteryService.saveAll(batteries);
		if (successfull) {
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.badRequest().body(errorDatabaseInsert);

	}

	@GetMapping("/postcodeSelection/{upperBound}/{lowerBound}")
	public ResponseEntity<Object> postcodeSelection(@PathVariable("upperBound") Integer upperBound,
			@PathVariable("lowerBound") Integer lowerBound) {
		if(lowerBound.intValue() > upperBound.intValue()) {
			return ResponseEntity.badRequest().body(errorPostcodeSelection);
		}
		PostcodeAreaBatteries batteries = batteryService.getPostcodeAreaInfo(upperBound, lowerBound);
		return ResponseEntity.ok().body(batteries);
	}
}
