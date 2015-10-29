package org.p4p.backpocketdriver.driverlog.importdata;

import java.util.List;

import org.p4p.backpocketdriver.driverlog.model.Journey;
import org.p4p.backpocketdriver.driverlog.model.JourneyLog;
import org.p4p.backpocketdriver.driverlog.model.JourneyStatistics;

/**
 * Java class to return the Journey Results 
 * @author Dheeraj
 *
 */
public class ReturnResults {

	private Journey journey;
	private JourneyStatistics journeyStatistics;

	public ReturnResults(Journey journey, JourneyStatistics journeyStatistics){
		this.journey = journey;
		this.journeyStatistics = journeyStatistics;

	}

	public Journey getJourney() {
		return journey;
	}

	public void setJourney(Journey journey) {
		this.journey = journey;
	}

	public JourneyStatistics getJourneyStatistics() {
		return journeyStatistics;
	}

	public void setJourneyStatistics(JourneyStatistics journeyStatistics) {
		this.journeyStatistics = journeyStatistics;
	}



}
