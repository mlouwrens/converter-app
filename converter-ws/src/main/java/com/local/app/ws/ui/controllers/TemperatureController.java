package com.local.app.ws.ui.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.local.app.ws.services.TemperatureService;
import com.local.app.ws.ui.models.ConversionResponse;

@RestController
@RequestMapping("/temp")
public class TemperatureController {
	
	@Autowired
	TemperatureService tempService;

	/**
	 * Get unit types available for conversion.
	 * @return The unit types
	 */	
	@GetMapping("/units")
	public List<String> getUnitTypes() {
		
		return tempService.getUnitTypes();
	}
	
	/**
	 * Performs the conversions on the specified unit type.
	 * @param unit The unit type to convert from
	 * @param value The value of the unit to convert
	 * @return The list of unit types and the conversion values
	 */	
	@GetMapping
	public ResponseEntity<List<ConversionResponse>> getConversions(
			@RequestParam(value="unit" ) String unit,
			@RequestParam(value="value") double value) {
		
		List<ConversionResponse> conversions = tempService.convert(unit, value);
		
		if (conversions.size() < 1)
			return new ResponseEntity<List<ConversionResponse>>(conversions, HttpStatus.NO_CONTENT);
		else
			return new ResponseEntity<List<ConversionResponse>>(conversions, HttpStatus.OK);
	}
}
