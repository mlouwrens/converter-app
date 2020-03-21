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
import com.local.app.ws.services.data.WeightDataService;
import com.local.app.ws.services.impl.WeightServiceImpl;
import com.local.app.ws.shared.Utils;
import com.local.app.ws.ui.models.ConversionResponse;

@ExtendWith(MockitoExtension.class)
class WeightServiceImplTest {
	
	@InjectMocks
	WeightServiceImpl impl;
	@Mock
	WeightDataService dataServiceMock;
	@Spy
	Utils utils;

	@Test
	public void getUnitTypes_basic() {
		
		when(dataServiceMock.getKeys()).thenReturn(Arrays.asList("Ounce (oz)", "Pound (lb)", "Gram (g)"));
		assertEquals(Arrays.asList("Gram (g)", "Ounce (oz)", "Pound (lb)"), impl.getUnitTypes());
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
		assertArrayEquals(Arrays.asList(new ConversionResponse("Gram (g)", 861.0), new ConversionResponse("Ounce (oz)", 30.37091), new ConversionResponse("Pound (lb)", 1.89818)).toArray(), impl.convert("Gram (g)", 861).toArray());				
	}
	
	@Test
	public void convert_imperialToMetric() {

		when(dataServiceMock.getMap()).thenReturn(createMap());		
		assertArrayEquals(Arrays.asList(new ConversionResponse("Gram (g)", -10750.1304), new ConversionResponse("Ounce (oz)", -379.2), new ConversionResponse("Pound (lb)", -23.7)).toArray(), impl.convert("Pound (lb)", -23.7).toArray());		
	}	
	
	@Test
	public void convert_invalidOption() {
		
		when(dataServiceMock.getMap()).thenReturn(createMap());
	    Exception exception = assertThrows(InvalidOptionException.class, () -> {
	    	impl.convert("Centimetre (cm)", 578);
	    });
	 
	    assertTrue(exception.getMessage().contains("Conversion from Centimetre (cm) not supported."));
	}
	
	@Test
	public void convert_nullMap() {
		
		when(dataServiceMock.getMap()).thenReturn(null);
		assertArrayEquals(Arrays.asList().toArray(), impl.convert("Pound (lb)", 9.3).toArray());
	}	
	
	@Test
	public void convert_emptyMap() {
		
		when(dataServiceMock.getMap()).thenReturn(new HashMap<String, Double>());
		assertArrayEquals(Arrays.asList().toArray(), impl.convert("Pound (lb)", 9.3).toArray());
	}	

	private Map<String, Double> createMap() {
		
		Map<String, Double> map = new HashMap<String, Double>();
		map.put("Gram (g)", 1000.0);
		map.put("Pound (lb)", 453592.0);
		map.put("Ounce (oz)", 28349.5);
		
		return map;
	}

}
