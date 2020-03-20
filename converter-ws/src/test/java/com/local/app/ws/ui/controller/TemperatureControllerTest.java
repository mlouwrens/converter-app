package com.local.app.ws.ui.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.local.app.ws.services.TemperatureService;
import com.local.app.ws.ui.controllers.TemperatureController;
import com.local.app.ws.ui.models.ConversionResponse;

@ExtendWith(SpringExtension.class)
@WebMvcTest(TemperatureController.class)
class TemperatureControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private TemperatureService temperatureService;

	@Test
	public void getUnitTypes_basic() throws Exception {
		
		when(temperatureService.getUnitTypes()).thenReturn(Arrays.asList("Celsius (C)", "Farenheit (F)"));
		
		mockMvc.perform(MockMvcRequestBuilders.get("/temp/units"))
			.andExpect(status().isOk())
			.andExpect(content().json("[\"Celsius (C)\",\"Farenheit (F)\"]"))
			.andReturn();
	}
	
	@Test
	public void getUnitTypes_noResponseData() throws Exception {
		
		when(temperatureService.getUnitTypes()).thenReturn(Arrays.asList());

		mockMvc.perform(MockMvcRequestBuilders.get("/temp/units"))
			.andExpect(status().isOk())
			.andExpect(content().json("[]"))
			.andReturn();
	}	
	
	@Test
	public void getUnitTypes_nullResponse() throws Exception {
		
		when(temperatureService.getUnitTypes()).thenReturn(null);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/temp/units"))
			.andExpect(status().isOk())
			.andExpect(content().string(""))
			.andReturn();
	}		
	
	@Test
	public void getConversions_basic() throws Exception {
		
		when(temperatureService.convert("Celsius (C)", 24.0)).thenReturn(Arrays.asList(new ConversionResponse("Celsius (C)", 24.0), new ConversionResponse("Farenheit (F)", 75.2)));
		
		mockMvc.perform(MockMvcRequestBuilders.get("/temp?unit=Celsius (C)&value=24.0"))
			.andExpect(status().isOk())
			.andExpect(content().json("[{unit:\"Celsius (C)\",value:24.0},{unit:\"Farenheit (F)\",value:75.2}]"))
			.andReturn();
	}
	
	@Test
	public void getConversions_noResponseData() throws Exception {
		
		when(temperatureService.convert("Celsius (C)", 24.0)).thenReturn(Arrays.asList());
		
		mockMvc.perform(MockMvcRequestBuilders.get("/temp?unit=Celsius (C)&value=24.0"))
			.andExpect(status().isNoContent())
			.andExpect(content().json("[]"))
			.andReturn();
	}	
	
	@Test
	public void getConversions_noUnitInput() throws Exception {
		
		mockMvc.perform(MockMvcRequestBuilders.get("/temp?value=24.0"))
			.andExpect(status().isBadRequest())
			.andReturn();
	}
	
	@Test
	public void getConversions_noValueInput() throws Exception {
						
		mockMvc.perform(MockMvcRequestBuilders.get("/temp?unit=Celsius (C)"))
			.andExpect(status().isBadRequest())
			.andReturn();
	}

}
