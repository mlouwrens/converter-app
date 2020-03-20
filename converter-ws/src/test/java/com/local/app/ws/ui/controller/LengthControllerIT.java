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
class LengthControllerIT {
	
	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void getUnitTypes_basic() throws Exception {
		
		String response = this.restTemplate.getForObject("/length/units", String.class);
		
		JSONAssert.assertEquals("[\"Centimetre (cm)\",\"Foot (ft)\",\"Inch (in)\",\"Metre (m)\",\"Millimetre (mm)\",\"Yard (yd)\"]", response, true);
	}
	
	@Test
	public void getConversions_basic() throws Exception {

		ResponseEntity<String> response = this.restTemplate.getForEntity("/length?unit=Foot (ft)&value=11.2", String.class);
		
		JSONAssert.assertEquals("[{unit:\"Centimetre (cm)\",value:341.376},{unit:\"Foot (ft)\",value:11.2},{unit:\"Inch (in)\",value:134.4},{unit:\"Metre (m)\",value:3.41376},{unit:\"Millimetre (mm)\",value:3413.76},{unit:\"Yard (yd)\",value:3.73333}]", response.getBody(), true);
	}	

}
