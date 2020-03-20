	package com.local.app.ws.services.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.local.app.ws.exceptions.InvalidOptionException;
import com.local.app.ws.services.TemperatureService;
import com.local.app.ws.shared.Utils;
import com.local.app.ws.ui.models.ConversionResponse;

@Service
public class TemperatureServiceImpl implements TemperatureService {
	
	Utils utils;
	
	private static final String	UNIT_CELCIUS	= "Celsius (C)";
	private static final String	UNIT_FARENHEIT	= "Farenheit (F)";
	
	public TemperatureServiceImpl() {
		
	}	
	
	@Autowired
	public TemperatureServiceImpl(Utils utils) {
		
		this.utils = utils;
	}	

	@Override
	public List<String> getUnitTypes() {
		
		return Arrays.asList(UNIT_CELCIUS, UNIT_FARENHEIT);
	}	

	@Override
	public List<ConversionResponse> convert(String unit, double value) {

		List<ConversionResponse> conversions = new ArrayList<ConversionResponse>();		
		
		if (UNIT_CELCIUS.equals(unit)) {
			conversions.add(new ConversionResponse(UNIT_CELCIUS, utils.formatDouble(value)));
			conversions.add(new ConversionResponse(UNIT_FARENHEIT, utils.formatDouble(value * 9 / 5 + 32)));
		}
		else if (UNIT_FARENHEIT.equals(unit)) {
			conversions.add(new ConversionResponse(UNIT_CELCIUS, utils.formatDouble((value - 32) * 5 / 9)));
			conversions.add(new ConversionResponse(UNIT_FARENHEIT, utils.formatDouble(value)));			
		}
		else
			throw new InvalidOptionException("Conversion from " + unit + " not supported.");
		
		return conversions;
	}

}
