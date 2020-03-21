package com.local.app.ws.services.Impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;

import com.local.app.ws.exceptions.InvalidOptionException;
import com.local.app.ws.services.data.LengthDataService;
import com.local.app.ws.services.impl.LengthServiceImpl;
import com.local.app.ws.shared.Utils;
import com.local.app.ws.ui.models.ConversionResponse;

@ExtendWith(MockitoExtension.class)
class LengthServiceImplTest {
	
	@InjectMocks
	LengthServiceImpl impl;
	@Mock
	LengthDataService dataServiceMock;
	@Spy
	Utils utils;

	@Test
	public void getUnitTypes_basic() {
		
		when(dataServiceMock.getKeys()).thenReturn(Arrays.asList("Centimetre (cm)", "Metre (m)", "Inch (in)"));
		assertEquals(Arrays.asList("Centimetre (cm)", "Inch (in)", "Metre (m)"), impl.getUnitTypes());
	}
	
	@Test
	public void getUnitTypes_noData() {

		when(dataServiceMock.getKeys()).thenReturn(Arrays.asList());
		assertEquals(Arrays.asList(), impl.getUnitTypes());
	}	
	
	@Test
	public void getUnitTypes_null() {
		
		when(dataServiceMock.getKeys()).thenReturn(null);
		assertEquals(Arrays.asList(), impl.getUnitTypes());
	}
	
	@Test
	public void convert_metricToImperial() {

		when(dataServiceMock.getMap()).thenReturn(createMap());
		assertArrayEquals(Arrays.asList(new ConversionResponse("Centimetre (cm)", 510.0), new ConversionResponse("Inch (in)", 200.78740), new ConversionResponse("Metre (m)", 5.1)).toArray(), impl.convert("Metre (m)", 5.1).toArray());		
	}
	
	@Test
	public void convert_imperialToMetric() {

		when(dataServiceMock.getMap()).thenReturn(createMap());		
		assertArrayEquals(Arrays.asList(new ConversionResponse("Centimetre (cm)", 19.685), new ConversionResponse("Inch (in)", 7.75), new ConversionResponse("Metre (m)", 0.19685)).toArray(), impl.convert("Inch (in)", 7.75).toArray());
	}	
	
	@Test
	public void convert_invalidOption() {
		
		when(dataServiceMock.getMap()).thenReturn(createMap());
	    Exception exception = assertThrows(InvalidOptionException.class, () -> {
	    	impl.convert("Kilogram (kg)", 0.25);
	    });
	 
	    assertTrue(exception.getMessage().contains("Conversion from Kilogram (kg) not supported."));
	}
	
	@Test
	public void convert_nullMap() {
		
		when(dataServiceMock.getMap()).thenReturn(null);
		assertArrayEquals(Arrays.asList().toArray(), impl.convert("Metre (m)", 5.1).toArray());
	}	
	
	@Test
	public void convert_emptyMap() {
		
		when(dataServiceMock.getMap()).thenReturn(new HashMap<String, Double>());
		assertArrayEquals(Arrays.asList().toArray(), impl.convert("Metre (m)", 5.1).toArray());
	}	

	private Map<String, Double> createMap() {
		
		Map<String, Double> map = new HashMap<String, Double>();
		map.put("Centimetre (cm)", 10.0);
		map.put("Metre (m)", 1000.0);
		map.put("Inch (in)", 25.4);
		
		return map;
	}

}
