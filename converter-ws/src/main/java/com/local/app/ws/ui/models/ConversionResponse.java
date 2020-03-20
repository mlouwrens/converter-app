package com.local.app.ws.ui.models;

import java.util.Objects;

public class ConversionResponse implements Comparable<ConversionResponse> {

	private String unit;	
	private Double value;
	
	public ConversionResponse() {}
	
	public ConversionResponse(String unit, 
			Double value)
	{
		this.unit = unit;
		this.value = value;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnitTo(String unit) {
		this.unit = unit;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}
	
    //Test equal, override equals() and hashCode()
    @Override
    public boolean equals(Object o) {    	
        if (this == o) 
        	return true;
        
        if (o == null || getClass() != o.getClass()) 
        	return false;
                
        ConversionResponse response = (ConversionResponse) o;
        return unit == response.unit && value.equals(response.value); 
    }

    @Override
    public int hashCode() {
        return Objects.hash(unit, value);
    }	
    
    @Override
    public int compareTo(ConversionResponse cr) {
        return this.unit.compareTo(cr.unit);
    }
}
