package com.local.app.ws.shared;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class UtilsTest {

	@Test
	public void formatDouble_roundup() {
		Utils utils = new Utils();
		
		Double actualResult = utils.formatDouble(12345.1234567);
		Double expectedResult = 12345.12346;
		
		assertEquals(expectedResult, actualResult);
	}
	
	@Test
	public void formatDouble_rounddown() {
		Utils utils = new Utils();
		
		Double actualResult = utils.formatDouble(123.1234543);
		Double expectedResult = 123.12345;
		
		assertEquals(expectedResult, actualResult);
	}
	
	@Test
	public void formatDouble_zero() {
		Utils utils = new Utils();
		
		Double actualResult = utils.formatDouble(0.0);
		Double expectedResult = 0.0;
		
		assertEquals(expectedResult, actualResult);
	}	
	
	@Test
	public void formatDouble_int() {
		Utils utils = new Utils();
		
		Double actualResult = utils.formatDouble(12345);
		Double expectedResult = 12345.0;
		
		assertEquals(expectedResult, actualResult);
	}	
	
	@Test
	public void formatDouble_negative() {
		Utils utils = new Utils();
		
		Double actualResult = utils.formatDouble(-99999.999999);
		Double expectedResult = -100000.0;
		
		assertEquals(expectedResult, actualResult);
	}	
	
	@Test
	public void formatDouble_noRound() {
		Utils utils = new Utils();
		
		Double actualResult = utils.formatDouble(12345.99999);
		Double expectedResult = 12345.99999;
		
		assertEquals(expectedResult, actualResult);
	}	

}
