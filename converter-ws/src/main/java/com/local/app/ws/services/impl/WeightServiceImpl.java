	package com.local.app.ws.services.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.local.app.ws.exceptions.InvalidOptionException;
import com.local.app.ws.services.WeightService;
import com.local.app.ws.services.data.WeightDataService;
import com.local.app.ws.shared.Utils;
import com.local.app.ws.ui.models.ConversionResponse;

@Service
public class WeightServiceImpl implements WeightService {
	
	WeightDataService weightDataService;
	Utils utils;
	
	public WeightServiceImpl() { }
	
	@Autowired
	public WeightServiceImpl(WeightDataService weightDataService, Utils utils) {
		
		this.weightDataService = weightDataService;
		this.utils = utils;
	}	

	@Override
	public List<String> getUnitTypes() {
		
		List<String> types = weightDataService.getKeys();
		
		if (types == null)
			types = new ArrayList<String>();
		
		Collections.sort(types);
		
		return types;
	}

	@Override
	public List<ConversionResponse> convert(String unit, double value) {
		
		List<ConversionResponse> conversions = new ArrayList<ConversionResponse>();	
		Map<String, Double> map = weightDataService.getMap();
				
		if (map != null && !map.isEmpty())
		{
			Double baseValue = map.get(unit);
		
			if (baseValue == null){
				throw new InvalidOptionException("Conversion from " + unit + " not supported.");
			}

			map.forEach((k,v) -> conversions.add(
				new ConversionResponse(k, utils.formatDouble((value * baseValue / v)))));
		
			Collections.sort(conversions);
		}
		
		return conversions;
	}

}
