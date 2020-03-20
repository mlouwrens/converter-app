package com.local.app.ws.services;

import java.util.List;

import com.local.app.ws.ui.models.ConversionResponse;

public interface TemperatureService {
	
	List<String> getUnitTypes();
	
	List<ConversionResponse> convert(String unit, double value);
}
