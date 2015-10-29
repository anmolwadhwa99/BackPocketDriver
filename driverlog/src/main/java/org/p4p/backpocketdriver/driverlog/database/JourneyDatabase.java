package org.p4p.backpocketdriver.driverlog.database;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.p4p.backpocketdriver.driverlog.exception.DriverLogException;
import org.p4p.backpocketdriver.driverlog.model.Journey;
import org.p4p.backpocketdriver.driverlog.model.JourneyStatistics;
import org.p4p.backpocketdriver.driverlog.model.UserDetails;


public class JourneyDatabase {
	
	private SessionFactory sessionFactory;
	private Session session;
	
	public JourneyDatabase(){
			
	}
	
	public void openSessionFactory(){
		//configuration to enter the database
		Configuration c = new Configuration().configure();
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().
				applySettings(c.getProperties());

		sessionFactory = c.buildSessionFactory(builder.build());


	}

	public void closeSessionFactory(){
		sessionFactory.close();

	}
	
	/**
	 * Gets information for a particular Journey
	 * @param journeyId
	 * @return Journey object
	 */
	public Journey getJourney(int journeyId){
		openSessionFactory();
		session = sessionFactory.openSession();
		Journey journey = (Journey) session.get(Journey.class, journeyId);
		
		if(journey == null){
			closeSessionFactory();
			throw new DriverLogException("Journey with id " + journeyId + " not found");
		}
		
		session.close();
		closeSessionFactory();
		return journey;
	}
	
	/**
	 * Adds a particular journey to the database
	 * @param journey
	 */
	public void addJourney(Journey journey, String userName){
		openSessionFactory();
		session = sessionFactory.openSession();
		UserDetails user = (UserDetails) session.get(UserDetails.class, userName);
		session.beginTransaction();
		journey.setUser(user);
		session.save(journey);
		session.getTransaction().commit();
		session.close();
		closeSessionFactory();
	}
	
	/**
	 * Deletes a particular journey from the database
	 * @param journeyId
	 */
	public void removeJourney(int journeyId){
		openSessionFactory();
		session = sessionFactory.openSession();
		session.beginTransaction();
		Journey journey = (Journey) session.get(Journey.class, journeyId);
		
		if(journey == null){
			closeSessionFactory();
			throw new DriverLogException("Journey with id " + journeyId + " not found so therefore journey could not be deleted.");
		}
		
		session.delete(journey);
		session.getTransaction().commit();
		session.close();
		closeSessionFactory();
	}
	
	public void addJourneyStatistic(int journeyId, JourneyStatistics js){
		openSessionFactory();
		session = sessionFactory.openSession();
		session.beginTransaction();
		Journey journey = (Journey) session.get(Journey.class, journeyId);
		
		if(journey == null){
			closeSessionFactory();
			throw new DriverLogException("Journey with id " + journeyId + " doesn't exist. Therefore unable to add journey statistic");
		}
		
		js.setJourney(journey);
		session.save(js);
		session.getTransaction().commit();
		session.close();
		closeSessionFactory();
	}
	
	public JourneyStatistics getStatsForJourney(int journeyId){
		openSessionFactory();
		session = sessionFactory.openSession();
		session.beginTransaction();
		Journey journey = (Journey) session.get(Journey.class, journeyId);
		
		if(journey == null){
			closeSessionFactory();
			throw new DriverLogException("Unable to retrieve journey statistics as journey with id " +journeyId + " not found.");
		}
		
		session.close();
		closeSessionFactory();
		return journey.getJourneyStatistics();
		
	}
	
	public JourneyStatistics getJourneyStatistic(int journeyStatisticId){
		openSessionFactory();
		session = sessionFactory.openSession();
		session.beginTransaction();
		JourneyStatistics js = (JourneyStatistics) session.get(JourneyStatistics.class, journeyStatisticId);
		
		if(js == null){
			closeSessionFactory();
			throw new DriverLogException("JourneyStatistics with id " +journeyStatisticId + " not found.");
		}
		
		session.close();
		closeSessionFactory();
		return js;
		
	}

}
