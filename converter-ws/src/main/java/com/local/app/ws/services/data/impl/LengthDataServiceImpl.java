package com.local.app.ws.services.data.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.local.app.ws.services.data.LengthDataService;

@Service
public class LengthDataServiceImpl  implements LengthDataService {

	Map<String, Double> unitTypes;
	
	public LengthDataServiceImpl() {
		
		createMap();
	}

	private void createMap() {
		
		unitTypes = new HashMap<String, Double>();
		unitTypes.put("Millimetre (mm)", 1.0);
		unitTypes.put("Centimetre (cm)", 10.0);
		unitTypes.put("Metre (m)", 1000.0);
		unitTypes.put("Inch (in)", 25.4);
		unitTypes.put("Foot (ft)", 304.8);
		unitTypes.put("Yard (yd)", 914.4);
	}	
	
	@Override
	public Map<String, Double> getMap() {
		
		return unitTypes;
	}
	
	@Override
	public List<String> getKeys() {
		
		return new ArrayList<>(unitTypes.keySet());
	}
}
