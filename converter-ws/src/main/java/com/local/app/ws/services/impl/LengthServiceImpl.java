	package com.local.app.ws.services.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.local.app.ws.exceptions.InvalidOptionException;
import com.local.app.ws.services.LengthService;
import com.local.app.ws.services.data.LengthDataService;
import com.local.app.ws.shared.Utils;
import com.local.app.ws.ui.models.ConversionResponse;

@Service
public class LengthServiceImpl implements LengthService {
	
	LengthDataService lengthDataService;
	Utils utils;

	public LengthServiceImpl() {}	
	
	@Autowired
	public LengthServiceImpl(LengthDataService lengthDataService, Utils utils) {
		
		this.lengthDataService = lengthDataService;
		this.utils = utils;		
	}
	
	public void setLengthDataService(LengthDataService lengthDataService) {
		
		this.lengthDataService = lengthDataService;
	}

	@Override
	public List<String> getUnitTypes() {
		
		List<String> types = lengthDataService.getKeys();
		
		if (types == null)
			types = new ArrayList<String>();		
		
		Collections.sort(types);
		
		return types;
	}

	@Override
	public List<ConversionResponse> convert(String unit, double value) {

		List<ConversionResponse> conversions = new ArrayList<ConversionResponse>();		
		Map<String, Double> map = lengthDataService.getMap();
		
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
