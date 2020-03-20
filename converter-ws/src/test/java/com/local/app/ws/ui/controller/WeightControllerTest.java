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
import com.local.app.ws.services.WeightService;
import com.local.app.ws.ui.controllers.WeightController;
import com.local.app.ws.ui.models.ConversionResponse;

@ExtendWith(SpringExtension.class)
@WebMvcTest(WeightController.class)
class WeightControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private WeightService weightService;

	@Test
	public void getUnitTypes_basic() throws Exception {
		
		when(weightService.getUnitTypes()).thenReturn(Arrays.asList("Gram (g)", "Kilogram (kg)", "Ounce (oz)"));
		
		mockMvc.perform(MockMvcRequestBuilders.get("/weight/units"))
			.andExpect(status().isOk())
			.andExpect(content().json("[\"Gram (g)\",\"Kilogram (kg)\",\"Ounce (oz)\"]"))
			.andReturn();
	}
	
	@Test
	public void getUnitTypes_noResponseData() throws Exception {
		
		when(weightService.getUnitTypes()).thenReturn(Arrays.asList());

		mockMvc.perform(MockMvcRequestBuilders.get("/weight/units"))
			.andExpect(status().isOk())
			.andExpect(content().json("[]"))
			.andReturn();
	}	
	
	@Test
	public void getUnitTypes_nullResponse() throws Exception {
		
		when(weightService.getUnitTypes()).thenReturn(null);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/weight/units"))
			.andExpect(status().isOk())
			.andExpect(content().string(""))
			.andReturn();
	}		
	
	@Test
	public void getConversions_basic() throws Exception {
		
		when(weightService.convert("Ounce (oz)", 3.0)).thenReturn(Arrays.asList(new ConversionResponse("Gram (g)", 85.0485), new ConversionResponse("Ounce (oz)", 3.0)));
		
		mockMvc.perform(MockMvcRequestBuilders.get("/weight?unit=Ounce (oz)&value=3.0"))	
			.andExpect(status().isOk())
			.andExpect(content().json("[{unit:\"Gram (g)\",value:85.0485},{unit:\"Ounce (oz)\",value:3.0}]"))
			.andReturn();
	}
	
	@Test
	public void getConversions_noResponseData() throws Exception {
		
		when(weightService.convert("Ounce (oz)", 3.0)).thenReturn(Arrays.asList());

		mockMvc.perform(MockMvcRequestBuilders.get("/weight?unit=Ounce (oz)&value=3.0"))
			.andExpect(status().isNoContent())
			.andExpect(content().json("[]"))
			.andReturn();
	}	
	
	@Test
	public void getConversions_noUnitInput() throws Exception {
		
		mockMvc.perform(MockMvcRequestBuilders.get("/weight?value=3.0"))
			.andExpect(status().isBadRequest())
			.andReturn();
	}
	
	@Test
	public void getConversions_noValueInput() throws Exception {
					
		mockMvc.perform(MockMvcRequestBuilders.get("/weight?unit=Ounce (oz)"))
			.andExpect(status().isBadRequest())
			.andReturn();
	}

}
