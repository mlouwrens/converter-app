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
class WeightControllerIT {
	
	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void getUnitTypes_basic() throws Exception {
		
		String response = this.restTemplate.getForObject("/weight/units", String.class);
		
		JSONAssert.assertEquals("[\"Gram (g)\",\"Kilogram (kg)\",\"Ounce (oz)\",\"Pound (lb)\"]", response, true);
	}
	
	@Test
	public void getConversions_basic() throws Exception {
		
		ResponseEntity<String> response = this.restTemplate.getForEntity("/weight?unit=Gram (g)&value=25.0", String.class);
		
		JSONAssert.assertEquals("[{unit:\"Gram (g)\",value:25.0},{unit:\"Kilogram (kg)\",value:0.025},{unit:\"Ounce (oz)\",value:0.88185},{unit:\"Pound (lb)\",value:0.05512}]", response.getBody(), true);
	}		

}
