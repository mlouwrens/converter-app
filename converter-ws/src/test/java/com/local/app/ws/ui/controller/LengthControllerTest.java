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
import com.local.app.ws.services.LengthService;
import com.local.app.ws.ui.controllers.LengthController;
import com.local.app.ws.ui.models.ConversionResponse;

@ExtendWith(SpringExtension.class)
@WebMvcTest(LengthController.class)
class LengthControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private LengthService lengthService;

	@Test
	public void getUnitTypes_basic() throws Exception {
		
		when(lengthService.getUnitTypes()).thenReturn(Arrays.asList("Centimetre (cm)", "Inch (in)", "Metre (m)"));
		
		mockMvc.perform(MockMvcRequestBuilders.get("/length/units"))
			.andExpect(status().isOk())
			.andExpect(content().json("[\"Centimetre (cm)\",\"Inch (in)\",\"Metre (m)\"]"))
			.andReturn();
	}
	
	@Test
	public void getUnitTypes_noResponseData() throws Exception {
		
		when(lengthService.getUnitTypes()).thenReturn(Arrays.asList());
		
		mockMvc.perform(MockMvcRequestBuilders.get("/length/units"))
			.andExpect(status().isOk())
			.andExpect(content().json("[]"))
			.andReturn();
	}	
	
	@Test
	public void getUnitTypes_nullResponse() throws Exception {
		
		when(lengthService.getUnitTypes()).thenReturn(null);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/length/units"))
			.andExpect(status().isOk())
			.andExpect(content().string(""))
			.andReturn();
	}		
	
	@Test
	public void getConversions_basic() throws Exception {
		
		when(lengthService.convert("Centimetre (cm)", 5.0)).thenReturn(Arrays.asList(new ConversionResponse("Centimetre (cm)", 5.0), new ConversionResponse("Inch (in)", 1.96850)));
		
		mockMvc.perform(MockMvcRequestBuilders.get("/length?unit=Centimetre (cm)&value=5.0"))
			.andExpect(status().isOk())
			.andExpect(content().json("[{unit:\"Centimetre (cm)\",value:5.0},{unit:\"Inch (in)\",value:1.96850}]"))
			.andReturn();
	}
	
	@Test
	public void getConversions_noResponseData() throws Exception {
		
		when(lengthService.convert("Centimetre (cm)", 5.0)).thenReturn(Arrays.asList());
		
		mockMvc.perform(MockMvcRequestBuilders.get("/length?unit=Centimetre (cm)&value=5.0"))
			.andExpect(status().isNoContent())
			.andExpect(content().json("[]"))
			.andReturn();
	}	
	
	@Test
	public void getConversions_noUnitInput() throws Exception {
		
		mockMvc.perform(MockMvcRequestBuilders.get("/length?value=5.0"))
			.andExpect(status().isBadRequest())
			.andReturn();
	}
	
	@Test
	public void getConversions_noValueInput() throws Exception {
		
		mockMvc.perform(MockMvcRequestBuilders.get("/length?unit=Centimetre (cm)"))
			.andExpect(status().isBadRequest())
			.andReturn();
	}

}
