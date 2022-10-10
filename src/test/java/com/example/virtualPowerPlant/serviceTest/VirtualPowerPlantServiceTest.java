package com.example.virtualPowerPlant.serviceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.virtualPowerPlant.TestData;
import com.example.virtualPowerPlant.dto.PostcodeAreaBatteries;
import com.example.virtualPowerPlant.model.Battery;
import com.example.virtualPowerPlant.repository.BatteryRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class VirtualPowerPlantServiceTest {

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private BatteryRepository batteryRepository;

	@Test
	public void storeBateriesTest() throws Exception {
		String uri = "/storeBatteries";
		Battery battery = new Battery(TestData.TEST_NAME_1, TestData.TEST_POSTCODE_1,
				TestData.TEST_CAPACITY_1);

		mockMvc.perform(post(uri, 42L).contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objectMapper.writeValueAsString(List.of(battery)))).andExpect(status().isOk());
	}

	@Test
	public void postcodeSelection() throws Exception {
		String uri = "/postcodeSelection/21005/11000";
		List<Battery> batteries = List.of(
				new Battery(TestData.TEST_NAME_1, TestData.TEST_POSTCODE_1, TestData.TEST_CAPACITY_1),
				new Battery(TestData.TEST_NAME_2, TestData.TEST_POSTCODE_2, TestData.TEST_CAPACITY_2),
				new Battery(TestData.TEST_NAME_3, TestData.TEST_POSTCODE_3, TestData.TEST_CAPACITY_3));
		List<Battery> savedBatteries = batteryRepository.saveAll(batteries);
		assertEquals(batteries, savedBatteries);

		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);

		String content = mvcResult.getResponse().getContentAsString();
		PostcodeAreaBatteries response = objectMapper.readValue(content, PostcodeAreaBatteries.class);
		assertTrue(response.getBatteries().size() > 0);
	}
}
