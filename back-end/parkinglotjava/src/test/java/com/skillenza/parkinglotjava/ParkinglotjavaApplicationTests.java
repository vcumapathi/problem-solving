package com.skillenza.parkinglotjava;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ParkinglotjavaApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ParkingLotRepository parkingRepo;

	String testParking = "{\"createdAt\":\"2019-08-16T16:17:42.798+0000\",\"parkingDuration\":\"60\",\"lot\":\"8\",\"vehicleNumber\":\"108\", \"updatedAt\":\"2019-08-16T16:17:42.798+0000\",\"parkingAmount\":\"20\"}";

	@Test
	public void saveParkingLot() throws Exception {
		ParkingLot parkingLot = new ParkingLot();
		Mockito.when(parkingRepo.save(Mockito.any(ParkingLot.class))).thenReturn(parkingLot);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/parkings").accept(MediaType.APPLICATION_JSON)
				.content(testParking).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.CREATED.value(), response.getStatus());
	}

	ParkingLot parkingLot = new ParkingLot(new Long(1), new Date(), new Date(), 20, 1, 60, 1234);

	@Test
	public void findParkingLot() throws Exception {
		String expected = "{id:1,parkingAmount:\"20\",lot:\"1\",vehicleNumber:\"1234\",parkingDuration:\"60\"}";
		Mockito.when(parkingRepo.findById(1L)).thenReturn(Optional.of(parkingLot));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/parkings").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		System.out.println(result.getResponse());
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}

}
