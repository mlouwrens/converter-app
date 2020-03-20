package com.local.app.ws.ui.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
class TemperatureControllerIT {
	
	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void getUnitTypes_basic() throws Exception {
		
		String response = this.restTemplate.getForObject("/temp/units", String.class);
		
		JSONAssert.assertEquals("[\"Celsius (C)\",\"Farenheit (F)\"]", response, true);
	}
	
	@Test
	public void getConversions_basic() throws Exception {
	
		ResponseEntity<String> response = this.restTemplate.getForEntity("/temp?unit=Farenheit (F)&value=173.0", String.class);

		JSONAssert.assertEquals("[{unit:\"Celsius (C)\",value:78.33333},{unit:\"Farenheit (F)\",value:173.0}]", response.getBody(), true);
	}	

}
