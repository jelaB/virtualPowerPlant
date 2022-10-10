package com.example.virtualPowerPlant.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.virtualPowerPlant.dto.PostcodeAreaBatteries;
import com.example.virtualPowerPlant.model.Battery;
import com.example.virtualPowerPlant.repository.BatteryRepository;

@Service
public class BatteryService {

	@Autowired
	private BatteryRepository batteryRepository;
	
	private static final double DEFAULT_VALUE = -1;

	public boolean saveAll(List<Battery> batteries) {
		List<Battery> savedBatteries = batteryRepository.saveAll(batteries);
		if (savedBatteries != null && !savedBatteries.isEmpty()) {
			return true;
		}

		return false;
	}

	public PostcodeAreaBatteries getPostcodeAreaInfo(int upperBound, int lowerBound) {
		List<Battery> batteries = getAllBatteriesWithinARangeSorted(upperBound, lowerBound);
		PostcodeAreaBatteries postcodeBatteries = new PostcodeAreaBatteries(batteries);
		calculateStatistics(postcodeBatteries);
		return postcodeBatteries;
	}

	private List<Battery> getAllBatteriesWithinARangeSorted(int upperBound, int lowerBound) {
		List<Battery> batteries = batteryRepository.findByPostcodeBetween(lowerBound, upperBound);
		if (batteries != null && !batteries.isEmpty()) {
			Collections.sort(batteries, Comparator.comparing(Battery::getName));
			return batteries;
		}
		return null;
	}
	
	private void calculateStatistics(PostcodeAreaBatteries postcodeBatteries) {
		if (postcodeBatteries.getBatteries() == null || postcodeBatteries.getBatteries().isEmpty()) {
			postcodeBatteries.setTotalWattCapacity(DEFAULT_VALUE);
			postcodeBatteries.setAverageWattCapacity(DEFAULT_VALUE);
		}
		DoubleSummaryStatistics statistics = postcodeBatteries.getBatteries().stream().mapToDouble(Battery::getWattCapacity)
				.summaryStatistics();
		postcodeBatteries.setTotalWattCapacity(statistics.getSum());
		postcodeBatteries.setAverageWattCapacity(statistics.getAverage());
	}

}
