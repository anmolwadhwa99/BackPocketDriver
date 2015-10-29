package org.p4p.backpocketdriver.driverlog.model;

import java.util.Date;

import org.p4p.backpocketdriver.driverlog.model.JourneyClassifications.RoadSpeedClassification;
import org.p4p.backpocketdriver.driverlog.model.JourneyClassifications.RoadTypeClassification;
import org.p4p.backpocketdriver.driverlog.model.JourneyClassifications.TimeOfDay;

public class JourneyLog {
	

	private Long timeStamp;
	private String coordinates;
	private double driverSpeed;
	private RoadSpeedClassification roadSpeed;
	private RoadTypeClassification roadType;
	private TimeOfDay timeOfDay;
	private double accuracy;
	
	public JourneyLog(){
		
	}
	
	public JourneyLog(Long timeStamp, String coordinates, double driverSpeed,
			RoadSpeedClassification roadSpeed, RoadTypeClassification roadType,
			TimeOfDay timeOfDay, double accuracy) {
		super();
		this.timeStamp = timeStamp;
		this.coordinates = coordinates;
		this.driverSpeed = driverSpeed;
		this.roadSpeed = roadSpeed;
		this.roadType = roadType;
		this.timeOfDay = timeOfDay;
		this.accuracy = accuracy;
	}
	
	public JourneyLog(Long timeStamp, String coordinates, double driverSpeed,
			RoadSpeedClassification roadSpeed, RoadTypeClassification roadType,
			TimeOfDay timeOfDay) {
		super();
		this.timeStamp = timeStamp;
		this.coordinates = coordinates;
		this.driverSpeed = driverSpeed;
		this.roadSpeed = roadSpeed;
		this.roadType = roadType;
		this.timeOfDay = timeOfDay;
		
	}
	
	public double getAccuracy() {
		return accuracy;
	}

	public void setAccuracy(double accuracy) {
		this.accuracy = accuracy;
	}

	public Long getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(Long timeStamp) {
		this.timeStamp = timeStamp;
	}
	public String getCoordinates() {
		return coordinates;
	}
	public void setCoordinates(String coordinates) {
		this.coordinates = coordinates;
	}
	public double getDriverSpeed() {
		return driverSpeed;
	}
	public void setDriverSpeed(double driverSpeed) {
		this.driverSpeed = driverSpeed;
	}
	public RoadSpeedClassification getRoadSpeed() {
		return roadSpeed;
	}
	public void setRoadSpeed(RoadSpeedClassification roadSpeed) {
		this.roadSpeed = roadSpeed;
	}
	public RoadTypeClassification getRoadType() {
		return roadType;
	}
	public void setRoadType(RoadTypeClassification roadType) {
		this.roadType = roadType;
	}
	public TimeOfDay getTimeOfDay() {
		return timeOfDay;
	}
	public void setTimeOfDay(TimeOfDay timeOfDay) {
		this.timeOfDay = timeOfDay;
	}
	
	@Override
	public String toString(){
		return timeStamp + "," + coordinates + ","
			 + roadType + "," +  roadSpeed + ","
			 + timeOfDay + "," + driverSpeed + "," + accuracy;
	}
	
	
	
}
