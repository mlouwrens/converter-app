package com.local.app.ws.services.data;

import java.util.List;
import java.util.Map;

public interface WeightDataService {

	Map<String, Double> getMap();
	
	List<String> getKeys();
}
