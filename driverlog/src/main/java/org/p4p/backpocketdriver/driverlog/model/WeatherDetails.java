package org.p4p.backpocketdriver.driverlog.model;

import java.util.Date;

public class WeatherDetails {
	
	public WeatherDetails(Date timeStamp, WeatherConditionClassification weatherCondition) {
		super();
		this.timeStamp = timeStamp;
		this.weatherCondition = weatherCondition;
	}
	public static enum WeatherConditionClassification{
		RAIN, DRIZZLE, FINE, FOG, OTHER
	}
	
	private Date timeStamp;
	private WeatherConditionClassification weatherCondition;
	
	public Date getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}
	public WeatherConditionClassification getCondition() {
		return weatherCondition;
	}
	public void setCondition(WeatherConditionClassification condition) {
		this.weatherCondition = condition;
	}
	
	
	
	
	

}
