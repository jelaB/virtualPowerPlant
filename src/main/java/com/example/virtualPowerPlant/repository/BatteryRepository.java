package com.example.virtualPowerPlant.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.virtualPowerPlant.model.Battery;

public interface BatteryRepository extends JpaRepository<Battery, Integer> {

	List<Battery> findByPostcodeBetween(int start, int end);

}
