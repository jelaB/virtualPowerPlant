package com.example.virtualPowerPlant;

import org.mockito.Mockito;

import com.example.virtualPowerPlant.model.Battery;

public class TestData {

	public static final String TEST_NAME_1 = "A21tWu";
	public static final String TEST_NAME_2 = "AyBt08";
	public static final String TEST_NAME_3 = "23QTRn";

	public static final int TEST_POSTCODE_1 = 21000;
	public static final int TEST_POSTCODE_2 = 11005;
	public static final int TEST_POSTCODE_3 = 11000;

	public static final double TEST_CAPACITY_1 = 2500;
	public static final double TEST_CAPACITY_2 = 1500;
	public static final double TEST_CAPACITY_3 = 2560.2;

	public static Battery mockBatteryObject(String name, int postcode, double capacity) {
		Battery battery = Mockito.mock(Battery.class);
		battery.setName(name);
		battery.setPostcode(postcode);
		battery.setWattCapacity(capacity);
		return battery;
	}

}
