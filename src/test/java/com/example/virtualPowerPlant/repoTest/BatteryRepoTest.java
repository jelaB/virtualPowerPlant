package com.example.virtualPowerPlant.repoTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.virtualPowerPlant.TestData;
import com.example.virtualPowerPlant.model.Battery;
import com.example.virtualPowerPlant.repository.BatteryRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class BatteryRepoTest {

	@MockBean
	private BatteryRepository batteryRepository;

	@Test
	public void storeBattery() {
		Battery battery = TestData.mockBatteryObject(TestData.TEST_NAME_1, TestData.TEST_POSTCODE_1,
				TestData.TEST_CAPACITY_1);
		when(batteryRepository.save(any(Battery.class))).thenReturn(battery);
		Battery saved = batteryRepository.save(battery);
		assertEquals(battery, saved);
	}

	@Test
	public void findByPostcodeInterval() {
		List<Battery> batteries = List.of(
				TestData.mockBatteryObject(TestData.TEST_NAME_1, TestData.TEST_POSTCODE_1, TestData.TEST_CAPACITY_1),
				TestData.mockBatteryObject(TestData.TEST_NAME_2, TestData.TEST_POSTCODE_2, TestData.TEST_CAPACITY_2),
				TestData.mockBatteryObject(TestData.TEST_NAME_3, TestData.TEST_POSTCODE_3, TestData.TEST_CAPACITY_3));

		when(batteryRepository.saveAll(Mockito.<Battery>anyList())).thenReturn(batteries);
		when(batteryRepository.findByPostcodeBetween(Mockito.anyInt(), Mockito.anyInt())).thenReturn(batteries);

		List<Battery> savedBatteries = batteryRepository.saveAll(batteries);
		assertEquals(batteries, savedBatteries);

		List<Battery> queriedBatteries = batteryRepository.findByPostcodeBetween(TestData.TEST_POSTCODE_1,
				TestData.TEST_POSTCODE_3);
		assertEquals(batteries, queriedBatteries);

	}

}
