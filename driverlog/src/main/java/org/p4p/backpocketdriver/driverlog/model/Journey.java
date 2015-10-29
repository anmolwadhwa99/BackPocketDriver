package org.p4p.backpocketdriver.driverlog.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;







import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.p4p.backpocketdriver.driverlog.model.WeatherDetails.WeatherConditionClassification;

@XmlRootElement
@Entity
public class Journey {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int journeyId;
	private String date;
	
	private int distanceTravelled;
	private int duration;
	private String journeyName;
	private WeatherConditionClassification weather;
	
	@Column(columnDefinition="text")
	private String coordinates;

	public WeatherConditionClassification getWeather() {
		return weather;
	}

	public void setWeather(WeatherConditionClassification weather) {
		this.weather = weather;
	}


	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="USER_NAME")
	private UserDetails user;
	
	
	@OneToOne(mappedBy="journey", cascade = CascadeType.ALL)
	private JourneyStatistics journeyStatistics;
	
	
	@XmlTransient
	public JourneyStatistics getJourneyStatistics() {
		return journeyStatistics;
	}

	public void setJourneyStatistics(JourneyStatistics journeyStatistics) {
		this.journeyStatistics = journeyStatistics;
	}

	public Journey(){
		
	}

	
	public int getJourneyId() {
		return journeyId;
	}
	public void setJourneyId(int journeyId) {
		this.journeyId = journeyId;
	}

	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getJourneyName() {
		return journeyName;
	}

	public void setJourneyName(String journeyName) {
		this.journeyName = journeyName;
	}

	@XmlTransient
	public UserDetails getUser() {
		return user;
	}
	
	public void setUser(UserDetails user) {
		this.user = user;
	}
	
	public int getDistanceTravelled() {
		return distanceTravelled;
	}

	public void setDistanceTravelled(int distanceTravelled) {
		this.distanceTravelled = distanceTravelled;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}
	
	public String getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(String coordinates) {
		this.coordinates = coordinates;
	}
	
}
