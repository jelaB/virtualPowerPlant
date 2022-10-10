package com.example.virtualPowerPlant.dto;

import java.util.List;

import com.example.virtualPowerPlant.model.Battery;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PostcodeAreaBatteries {

	private List<Battery> batteries;
	private double totalWattCapacity;
	private double averageWattCapacity;

	public PostcodeAreaBatteries() {
	}

	public PostcodeAreaBatteries(List<Battery> batteries) {
		this.batteries = batteries;
	}

}
