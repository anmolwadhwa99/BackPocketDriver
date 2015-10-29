package org.p4p.backpocketdriver.driverlog.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.p4p.backpocketdriver.driverlog.database.JourneyDatabase;
import org.p4p.backpocketdriver.driverlog.exception.DriverLogException;
import org.p4p.backpocketdriver.driverlog.model.Journey;
import org.p4p.backpocketdriver.driverlog.model.JourneyStatistics;
import org.p4p.backpocketdriver.driverlog.model.UserDetails;

public class JourneyService {
	
	private JourneyDatabase journeyDB = new JourneyDatabase();
	
	/**
	 * Gets information for a particular Journey
	 * @param journeyId
	 * @return Journey object
	 */
	public Journey getJourney(int journeyId){
		Journey journey = journeyDB.getJourney(journeyId);
		return journey;
	}
	
	/**
	 * Adds a particular journey 
	 * @param journey
	 */
	public void addJourney(Journey journey, String userName){
		journeyDB.addJourney(journey, userName);
	}
	
	/**
	 * Deletes a particular journey 
	 * @param journeyId
	 */
	public void removeJourney(int journeyId){
		journeyDB.removeJourney(journeyId);
	}
	
	/**
	 * Takes a userName and returns back a list of different journeys of the user
	 * @param userName
	 * @return Collection<Journey>
	 */
	public Collection<Journey> getAllJourneys(String userName){
		UserService userService = new UserService();
		UserDetails userDetails = userService.getUserDetails(userName);
		
		Collection<Journey> userJourneys = userDetails.getJourneys();
		return userJourneys;
	}

	
	
	public void addJourneyStatistic(int journeyId,JourneyStatistics js){
		journeyDB.addJourneyStatistic(journeyId, js);
	}
	
	public JourneyStatistics getStatsForJourney(int journeyId){
		return journeyDB.getStatsForJourney(journeyId);
	}
	
	public JourneyStatistics getJourneyStatistic(int journeyStatisticId){
		return journeyDB.getJourneyStatistic(journeyStatisticId);
	}
	
	/**
	 * Takes a userName and returns back a list of the statistics for each of the user's journey's
	 * @param userName
	 * @return Collection<JourneyStatistics>
	 */
	public Collection<JourneyStatistics> getAllJourneyStatistics(String userName){
		UserService userService = new UserService();
		UserDetails userDetails = userService.getUserDetails(userName);
		
		Collection<Journey> userJourneys = userDetails.getJourneys();
		Collection<JourneyStatistics> journeyStats = new ArrayList<JourneyStatistics>();
		for (Journey j : userJourneys){
			
			if(j.getJourneyStatistics() == null){
				continue;
			}else{
				journeyStats.add(j.getJourneyStatistics());
			}
			
		}
		return journeyStats;
	}
	
	
	
		


}
