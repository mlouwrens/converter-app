package com.local.app.ws.services.Impl;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.InjectMocks;
import org.mockito.Spy;

import com.local.app.ws.exceptions.InvalidOptionException;
import com.local.app.ws.services.impl.TemperatureServiceImpl;
import com.local.app.ws.shared.Utils;
import com.local.app.ws.ui.models.ConversionResponse;

@ExtendWith(MockitoExtension.class)
class TemperatureServiceImplTest {
	
	@InjectMocks
	TemperatureServiceImpl impl;	
	@Spy
	Utils utils;	

	@Test
	public void getUnitTypes_basic() {
		
		assertEquals(Arrays.asList("Celsius (C)", "Farenheit (F)"), impl.getUnitTypes());
	}
	
	@Test
	public void convert_metricToImperial() {

		assertArrayEquals(Arrays.asList(new ConversionResponse("Celsius (C)", 34.0), new ConversionResponse("Farenheit (F)", 93.2)).toArray(), impl.convert("Celsius (C)", 34).toArray());		
	}
	
	@Test
	public void convert_imperialToMetric() {
		
		assertArrayEquals(Arrays.asList(new ConversionResponse("Celsius (C)", 122.77778), new ConversionResponse("Farenheit (F)", 253.0)).toArray(), impl.convert("Farenheit (F)", 253).toArray());
	}
	
	@Test
	public void convert_invalidOption() {
		
	    Exception exception = assertThrows(InvalidOptionException.class, () -> {
	    	impl.convert("Kelvin (K)", -15.4);
	    });
	 
	    assertTrue(exception.getMessage().contains("Conversion from Kelvin (K) not supported."));
	}

}
