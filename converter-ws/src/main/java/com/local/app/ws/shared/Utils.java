package com.local.app.ws.shared;

import java.text.DecimalFormat;

import org.springframework.stereotype.Service;

@Service
public class Utils {
	
	
	/**
	 * Rounds the double value up to the last 5 digits 
	 * @param d The double value to be formatted
	 * @return The formatted value
	 */
	public Double formatDouble(double d) {
		
		DecimalFormat df = new DecimalFormat("#.#####");
		  return Double.valueOf(df.format(d));
	}

}
