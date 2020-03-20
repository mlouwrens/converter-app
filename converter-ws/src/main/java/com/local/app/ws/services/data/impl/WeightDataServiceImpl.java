package com.local.app.ws.services.data.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.local.app.ws.services.data.WeightDataService;

@Service
public class WeightDataServiceImpl  implements WeightDataService {

	Map<String, Double> unitTypes;
	
	public WeightDataServiceImpl() {
		
		createMap();
	}

	private void createMap() {
		
		unitTypes = new HashMap<String, Double>();
		unitTypes.put("Gram (g)", 1000.0);
		unitTypes.put("Kilogram (kg)", 1000000.0);
		unitTypes.put("Pound (lb)", 453592.0);
		unitTypes.put("Ounce (oz)", 28349.5);
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
