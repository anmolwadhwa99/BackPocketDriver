package org.p4p.backpocketdriver.driverlog.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.p4p.backpocketdriver.driverlog.model.WeatherDetails.WeatherConditionClassification;

@XmlRootElement
@Entity
public class JourneyStatistics {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int statisticId;
	

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "journeyId")
	private Journey journey;
	private WeatherConditionClassification weather;

	//**DURATION**
	//Speed Zones (6)
	private int timeDriven_Twenty_total;
	private int timeDriven_Fifty_total;
	private int timeDriven_Sixty_total;
	private int timeDriven_Eighty_total;
	private int timeDriven_Hundred_total;	
	private int timeDriven_Miscellaneous_total;
	//Road Types (3)
	private int timeDriven_rural_total;
	private int timeDriven_urban_total;
	private int timeDriven_motorway_total;	
	//Time of Day (2)
	private int timeDriven_day_total;
	private int timeDriven_night_total;
	//Speed Zones and Time of Day (12)
	private int timeDriven_Twenty_day;
	private int timeDriven_Twenty_night;
	private int timeDriven_Fifty_day;
	private int timeDriven_Fifty_night;
	private int timeDriven_Sixty_day;
	private int timeDriven_Sixty_night;
	private int timeDriven_Eighty_day;
	private int timeDriven_Eighty_night;
	private int timeDriven_Hundred_day;
	private int timeDriven_Hundred_night;
	private int timeDriven_Miscellaneous_day;
	private int timeDriven_Miscellaneous_night;
	//Road Types and Time of Day (6)
	private int timeDriven_rural_day;
	private int timeDriven_rural_night;
	private int timeDriven_urban_day;
	private int timeDriven_urban_night;
	private int timeDriven_motorway_day;
	private int timeDriven_motorway_night;
	
	
	//**DISTANCE**
	//Speed Zones (6)
	private int distanceTravelled_Twenty_total;
	private int distanceTravelled_Fifty_total;
	private int distanceTravelled_Sixty_total;
	private int distanceTravelled_Eighty_total;
	private int distanceTravelled_Hundred_total;	
	private int distanceTravelled_Miscellaneous_total;
	//Road Types (3)
	private int distanceTravelled_rural_total;
	private int distanceTravelled_urban_total;
	private int distanceTravelled_motorway_total;	
	//Time of Day (2)
	private int distanceTravelled_day_total;
	private int distanceTravelled_night_total;
	//Speed Zones and Time of Day (12)
	private int distanceTravelled_Twenty_day;
	private int distanceTravelled_Twenty_night;
	private int distanceTravelled_Fifty_day;
	private int distanceTravelled_Fifty_night;
	private int distanceTravelled_Sixty_day;
	private int distanceTravelled_Sixty_night;
	private int distanceTravelled_Eighty_day;
	private int distanceTravelled_Eighty_night;
	private int distanceTravelled_Hundred_day;
	private int distanceTravelled_Hundred_night;
	private int distanceTravelled_Miscellaneous_day;
	private int distanceTravelled_Miscellaneous_night;
	//Road Types and Time of Day (6)
	private int distanceTravelled_rural_day;
	private int distanceTravelled_rural_night;
	private int distanceTravelled_urban_day;
	private int distanceTravelled_urban_night;
	private int distanceTravelled_motorway_day;
	private int distanceTravelled_motorway_night;
	
	
	//**AVERAGE SPEED**
	//Speed Zones (6)
	private int averageSpeed_Twenty;
	private int averageSpeed_Fifty;
	private int averageSpeed_Sixty;
	private int averageSpeed_Eighty;
	private int averageSpeed_Hundred;
	private int averageSpeed_Miscellaneous;
	
	//Road Types (3)
	private int averageSpeed_rural;
	private int averageSpeed_urban;
	private int averageSpeed_motorway;
	

	public JourneyStatistics(){		
	}

	public int getTimeDriven_Twenty_total() {
		return timeDriven_Twenty_total;
	}

	public void setTimeDriven_Twenty_total(int timeDriven_Twenty_total) {
		this.timeDriven_Twenty_total = timeDriven_Twenty_total;
	}

	public int getTimeDriven_Fifty_total() {
		return timeDriven_Fifty_total;
	}

	public void setTimeDriven_Fifty_total(int timeDriven_Fifty_total) {
		this.timeDriven_Fifty_total = timeDriven_Fifty_total;
	}

	public int getTimeDriven_Sixty_total() {
		return timeDriven_Sixty_total;
	}

	public void setTimeDriven_Sixty_total(int timeDriven_Sixty_total) {
		this.timeDriven_Sixty_total = timeDriven_Sixty_total;
	}

	public int getTimeDriven_Eighty_total() {
		return timeDriven_Eighty_total;
	}

	public void setTimeDriven_Eighty_total(int timeDriven_Eighty_total) {
		this.timeDriven_Eighty_total = timeDriven_Eighty_total;
	}

	public int getTimeDriven_Hundred_total() {
		return timeDriven_Hundred_total;
	}

	public void setTimeDriven_Hundred_total(int timeDriven_Hundred_total) {
		this.timeDriven_Hundred_total = timeDriven_Hundred_total;
	}

	public int getTimeDriven_Miscellaneous_total() {
		return timeDriven_Miscellaneous_total;
	}

	public void setTimeDriven_Miscellaneous_total(int timeDriven_Miscellaneous_total) {
		this.timeDriven_Miscellaneous_total = timeDriven_Miscellaneous_total;
	}

	public int getTimeDriven_Twenty_day() {
		return timeDriven_Twenty_day;
	}

	public void setTimeDriven_Twenty_day(int timeDriven_Twenty_day) {
		this.timeDriven_Twenty_day = timeDriven_Twenty_day;
	}

	public int getTimeDriven_Twenty_night() {
		return timeDriven_Twenty_night;
	}

	public void setTimeDriven_Twenty_night(int timeDriven_Twenty_night) {
		this.timeDriven_Twenty_night = timeDriven_Twenty_night;
	}

	public int getTimeDriven_Fifty_day() {
		return timeDriven_Fifty_day;
	}

	public void setTimeDriven_Fifty_day(int timeDriven_Fifty_day) {
		this.timeDriven_Fifty_day = timeDriven_Fifty_day;
	}

	public int getTimeDriven_Fifty_night() {
		return timeDriven_Fifty_night;
	}

	public void setTimeDriven_Fifty_night(int timeDriven_Fifty_night) {
		this.timeDriven_Fifty_night = timeDriven_Fifty_night;
	}

	public int getTimeDriven_Sixty_day() {
		return timeDriven_Sixty_day;
	}

	public void setTimeDriven_Sixty_day(int timeDriven_Sixty_day) {
		this.timeDriven_Sixty_day = timeDriven_Sixty_day;
	}

	public int getTimeDriven_Sixty_night() {
		return timeDriven_Sixty_night;
	}

	public void setTimeDriven_Sixty_night(int timeDriven_Sixty_night) {
		this.timeDriven_Sixty_night = timeDriven_Sixty_night;
	}

	public int getTimeDriven_Eighty_day() {
		return timeDriven_Eighty_day;
	}

	public void setTimeDriven_Eighty_day(int timeDriven_Eighty_day) {
		this.timeDriven_Eighty_day = timeDriven_Eighty_day;
	}

	public int getTimeDriven_Eighty_night() {
		return timeDriven_Eighty_night;
	}

	public void setTimeDriven_Eighty_night(int timeDriven_Eighty_night) {
		this.timeDriven_Eighty_night = timeDriven_Eighty_night;
	}

	public int getTimeDriven_Hundred_day() {
		return timeDriven_Hundred_day;
	}

	public void setTimeDriven_Hundred_day(int timeDriven_Hundred_day) {
		this.timeDriven_Hundred_day = timeDriven_Hundred_day;
	}

	public int getTimeDriven_Hundred_night() {
		return timeDriven_Hundred_night;
	}

	public void setTimeDriven_Hundred_night(int timeDriven_Hundred_night) {
		this.timeDriven_Hundred_night = timeDriven_Hundred_night;
	}

	public int getTimeDriven_Miscellaneous_day() {
		return timeDriven_Miscellaneous_day;
	}

	public void setTimeDriven_Miscellaneous_day(int timeDriven_Miscellaneous_day) {
		this.timeDriven_Miscellaneous_day = timeDriven_Miscellaneous_day;
	}

	public int getTimeDriven_Miscellaneous_night() {
		return timeDriven_Miscellaneous_night;
	}

	public void setTimeDriven_Miscellaneous_night(int timeDriven_Miscellaneous_night) {
		this.timeDriven_Miscellaneous_night = timeDriven_Miscellaneous_night;
	}

	public int getDistanceTravelled_Twenty_total() {
		return distanceTravelled_Twenty_total;
	}

	public void setDistanceTravelled_Twenty_total(int distanceTravelled_Twenty_total) {
		this.distanceTravelled_Twenty_total = distanceTravelled_Twenty_total;
	}

	public int getDistanceTravelled_Fifty_total() {
		return distanceTravelled_Fifty_total;
	}

	public void setDistanceTravelled_Fifty_total(int distanceTravelled_Fifty_total) {
		this.distanceTravelled_Fifty_total = distanceTravelled_Fifty_total;
	}

	public int getDistanceTravelled_Sixty_total() {
		return distanceTravelled_Sixty_total;
	}

	public void setDistanceTravelled_Sixty_total(int distanceTravelled_Sixty_total) {
		this.distanceTravelled_Sixty_total = distanceTravelled_Sixty_total;
	}

	public int getDistanceTravelled_Eighty_total() {
		return distanceTravelled_Eighty_total;
	}

	public void setDistanceTravelled_Eighty_total(int distanceTravelled_Eighty_total) {
		this.distanceTravelled_Eighty_total = distanceTravelled_Eighty_total;
	}

	public int getDistanceTravelled_Hundred_total() {
		return distanceTravelled_Hundred_total;
	}

	public void setDistanceTravelled_Hundred_total(
			int distanceTravelled_Hundred_total) {
		this.distanceTravelled_Hundred_total = distanceTravelled_Hundred_total;
	}

	public int getDistanceTravelled_Miscellaneous_total() {
		return distanceTravelled_Miscellaneous_total;
	}

	public void setDistanceTravelled_Miscellaneous_total(
			int distanceTravelled_Miscellaneous_total) {
		this.distanceTravelled_Miscellaneous_total = distanceTravelled_Miscellaneous_total;
	}

	public int getDistanceTravelled_Twenty_day() {
		return distanceTravelled_Twenty_day;
	}

	public void setDistanceTravelled_Twenty_day(int distanceTravelled_Twenty_day) {
		this.distanceTravelled_Twenty_day = distanceTravelled_Twenty_day;
	}

	public int getDistanceTravelled_Twenty_night() {
		return distanceTravelled_Twenty_night;
	}

	public void setDistanceTravelled_Twenty_night(int distanceTravelled_Twenty_night) {
		this.distanceTravelled_Twenty_night = distanceTravelled_Twenty_night;
	}

	public int getDistanceTravelled_Fifty_day() {
		return distanceTravelled_Fifty_day;
	}

	public void setDistanceTravelled_Fifty_day(int distanceTravelled_Fifty_day) {
		this.distanceTravelled_Fifty_day = distanceTravelled_Fifty_day;
	}

	public int getDistanceTravelled_Fifty_night() {
		return distanceTravelled_Fifty_night;
	}

	public void setDistanceTravelled_Fifty_night(int distanceTravelled_Fifty_night) {
		this.distanceTravelled_Fifty_night = distanceTravelled_Fifty_night;
	}

	public int getDistanceTravelled_Sixty_day() {
		return distanceTravelled_Sixty_day;
	}

	public void setDistanceTravelled_Sixty_day(int distanceTravelled_Sixty_day) {
		this.distanceTravelled_Sixty_day = distanceTravelled_Sixty_day;
	}

	public int getDistanceTravelled_Sixty_night() {
		return distanceTravelled_Sixty_night;
	}

	public void setDistanceTravelled_Sixty_night(int distanceTravelled_Sixty_night) {
		this.distanceTravelled_Sixty_night = distanceTravelled_Sixty_night;
	}

	public int getDistanceTravelled_Eighty_day() {
		return distanceTravelled_Eighty_day;
	}

	public void setDistanceTravelled_Eighty_day(int distanceTravelled_Eighty_day) {
		this.distanceTravelled_Eighty_day = distanceTravelled_Eighty_day;
	}

	public int getDistanceTravelled_Eighty_night() {
		return distanceTravelled_Eighty_night;
	}

	public void setDistanceTravelled_Eighty_night(int distanceTravelled_Eighty_night) {
		this.distanceTravelled_Eighty_night = distanceTravelled_Eighty_night;
	}

	public int getDistanceTravelled_Hundred_day() {
		return distanceTravelled_Hundred_day;
	}

	public void setDistanceTravelled_Hundred_day(int distanceTravelled_Hundred_day) {
		this.distanceTravelled_Hundred_day = distanceTravelled_Hundred_day;
	}

	public int getDistanceTravelled_Hundred_night() {
		return distanceTravelled_Hundred_night;
	}

	public void setDistanceTravelled_Hundred_night(
			int distanceTravelled_Hundred_night) {
		this.distanceTravelled_Hundred_night = distanceTravelled_Hundred_night;
	}

	public int getDistanceTravelled_Miscellaneous_day() {
		return distanceTravelled_Miscellaneous_day;
	}

	public void setDistanceTravelled_Miscellaneous_day(
			int distanceTravelled_Miscellaneous_day) {
		this.distanceTravelled_Miscellaneous_day = distanceTravelled_Miscellaneous_day;
	}

	public int getDistanceTravelled_Miscellaneous_night() {
		return distanceTravelled_Miscellaneous_night;
	}

	public void setDistanceTravelled_Miscellaneous_night(
			int distanceTravelled_Miscellaneous_night) {
		this.distanceTravelled_Miscellaneous_night = distanceTravelled_Miscellaneous_night;
	}

	public int getAverageSpeed_Twenty() {
		return averageSpeed_Twenty;
	}

	public void setAverageSpeed_Twenty(int averageSpeed_Twenty) {
		this.averageSpeed_Twenty = averageSpeed_Twenty;
	}

	public int getAverageSpeed_Fifty() {
		return averageSpeed_Fifty;
	}

	public void setAverageSpeed_Fifty(int averageSpeed_Fifty) {
		this.averageSpeed_Fifty = averageSpeed_Fifty;
	}

	public int getAverageSpeed_Sixty() {
		return averageSpeed_Sixty;
	}

	public void setAverageSpeed_Sixty(int averageSpeed_Sixty) {
		this.averageSpeed_Sixty = averageSpeed_Sixty;
	}

	public int getAverageSpeed_Eighty() {
		return averageSpeed_Eighty;
	}

	public void setAverageSpeed_Eighty(int averageSpeed_Eighty) {
		this.averageSpeed_Eighty = averageSpeed_Eighty;
	}

	public int getAverageSpeed_Hundred() {
		return averageSpeed_Hundred;
	}

	public void setAverageSpeed_Hundred(int averageSpeed_Hundred) {
		this.averageSpeed_Hundred = averageSpeed_Hundred;
	}

	public int getAverageSpeed_Miscellaneous() {
		return averageSpeed_Miscellaneous;
	}

	public void setAverageSpeed_Miscellaneous(int averageSpeed_Miscellaneous) {
		this.averageSpeed_Miscellaneous = averageSpeed_Miscellaneous;
	}

	public int getTimeDriven_rural_total() {
		return timeDriven_rural_total;
	}

	public void setTimeDriven_rural_total(int timeDriven_rural_total) {
		this.timeDriven_rural_total = timeDriven_rural_total;
	}

	public int getTimeDriven_urban_total() {
		return timeDriven_urban_total;
	}

	public void setTimeDriven_urban_total(int timeDriven_urban_total) {
		this.timeDriven_urban_total = timeDriven_urban_total;
	}

	public int getTimeDriven_motorway_total() {
		return timeDriven_motorway_total;
	}

	public void setTimeDriven_motorway_total(int timeDriven_motorway_total) {
		this.timeDriven_motorway_total = timeDriven_motorway_total;
	}

	public int getTimeDriven_day_total() {
		return timeDriven_day_total;
	}

	public void setTimeDriven_day_total(int timeDriven_day_total) {
		this.timeDriven_day_total = timeDriven_day_total;
	}

	public int getTimeDriven_night_total() {
		return timeDriven_night_total;
	}

	public void setTimeDriven_night_total(int timeDriven_night_total) {
		this.timeDriven_night_total = timeDriven_night_total;
	}



	public int getTimeDriven_rural_day() {
		return timeDriven_rural_day;
	}

	public void setTimeDriven_rural_day(int timeDriven_rural_day) {
		this.timeDriven_rural_day = timeDriven_rural_day;
	}

	public int getTimeDriven_rural_night() {
		return timeDriven_rural_night;
	}

	public void setTimeDriven_rural_night(int timeDriven_rural_night) {
		this.timeDriven_rural_night = timeDriven_rural_night;
	}

	public int getTimeDriven_urban_day() {
		return timeDriven_urban_day;
	}

	public void setTimeDriven_urban_day(int timeDriven_urban_day) {
		this.timeDriven_urban_day = timeDriven_urban_day;
	}

	public int getTimeDriven_urban_night() {
		return timeDriven_urban_night;
	}

	public void setTimeDriven_urban_night(int timeDriven_urban_night) {
		this.timeDriven_urban_night = timeDriven_urban_night;
	}

	public int getTimeDriven_motorway_day() {
		return timeDriven_motorway_day;
	}

	public void setTimeDriven_motorway_day(int timeDriven_motorway_day) {
		this.timeDriven_motorway_day = timeDriven_motorway_day;
	}

	public int getTimeDriven_motorway_night() {
		return timeDriven_motorway_night;
	}

	public void setTimeDriven_motorway_night(int timeDriven_motorway_night) {
		this.timeDriven_motorway_night = timeDriven_motorway_night;
	}



	public int getDistanceTravelled_rural_total() {
		return distanceTravelled_rural_total;
	}

	public void setDistanceTravelled_rural_total(int distanceTravelled_rural_total) {
		this.distanceTravelled_rural_total = distanceTravelled_rural_total;
	}

	public int getDistanceTravelled_urban_total() {
		return distanceTravelled_urban_total;
	}

	public void setDistanceTravelled_urban_total(int distanceTravelled_urban_total) {
		this.distanceTravelled_urban_total = distanceTravelled_urban_total;
	}

	public int getDistanceTravelled_motorway_total() {
		return distanceTravelled_motorway_total;
	}

	public void setDistanceTravelled_motorway_total(
			int distanceTravelled_motorway_total) {
		this.distanceTravelled_motorway_total = distanceTravelled_motorway_total;
	}

	public int getDistanceTravelled_day_total() {
		return distanceTravelled_day_total;
	}

	public void setDistanceTravelled_day_total(int distanceTravelled_day_total) {
		this.distanceTravelled_day_total = distanceTravelled_day_total;
	}

	public int getDistanceTravelled_night_total() {
		return distanceTravelled_night_total;
	}

	public void setDistanceTravelled_night_total(int distanceTravelled_night_total) {
		this.distanceTravelled_night_total = distanceTravelled_night_total;
	}



	public int getDistanceTravelled_rural_day() {
		return distanceTravelled_rural_day;
	}

	public void setDistanceTravelled_rural_day(int distanceTravelled_rural_day) {
		this.distanceTravelled_rural_day = distanceTravelled_rural_day;
	}

	public int getDistanceTravelled_rural_night() {
		return distanceTravelled_rural_night;
	}

	public void setDistanceTravelled_rural_night(int distanceTravelled_rural_night) {
		this.distanceTravelled_rural_night = distanceTravelled_rural_night;
	}

	public int getDistanceTravelled_urban_day() {
		return distanceTravelled_urban_day;
	}

	public void setDistanceTravelled_urban_day(int distanceTravelled_urban_day) {
		this.distanceTravelled_urban_day = distanceTravelled_urban_day;
	}

	public int getDistanceTravelled_urban_night() {
		return distanceTravelled_urban_night;
	}

	public void setDistanceTravelled_urban_night(int distanceTravelled_urban_night) {
		this.distanceTravelled_urban_night = distanceTravelled_urban_night;
	}

	public int getDistanceTravelled_motorway_day() {
		return distanceTravelled_motorway_day;
	}

	public void setDistanceTravelled_motorway_day(int distanceTravelled_motorway_day) {
		this.distanceTravelled_motorway_day = distanceTravelled_motorway_day;
	}

	public int getDistanceTravelled_motorway_night() {
		return distanceTravelled_motorway_night;
	}

	public void setDistanceTravelled_motorway_night(
			int distanceTravelled_motorway_night) {
		this.distanceTravelled_motorway_night = distanceTravelled_motorway_night;
	}



	public int getAverageSpeed_rural() {
		return averageSpeed_rural;
	}

	public void setAverageSpeed_rural(int averageSpeed_rural) {
		this.averageSpeed_rural = averageSpeed_rural;
	}

	public int getAverageSpeed_urban() {
		return averageSpeed_urban;
	}

	public void setAverageSpeed_urban(int averageSpeed_urban) {
		this.averageSpeed_urban = averageSpeed_urban;
	}

	public int getAverageSpeed_motorway() {
		return averageSpeed_motorway;
	}

	public void setAverageSpeed_motorway(int averageSpeed_motorway) {
		this.averageSpeed_motorway = averageSpeed_motorway;
	}

	
	public int getStatisticId() {
		return statisticId;
	}

	public void setStatisticId(int statisticId) {
		this.statisticId = statisticId;
	}
	
	@XmlTransient
	public Journey getJourney() {
		return journey;
	}

	public void setJourney(Journey journey) {
		this.journey = journey;
	}
	
	public WeatherConditionClassification getWeather() {
		return weather;
	}

	public void setWeather(WeatherConditionClassification weather) {
		this.weather = weather;
	}

	


	
	
}
