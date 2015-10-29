package org.p4p.backpocketdriver.driverlog.importdata;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.p4p.backpocketdriver.driverlog.model.Journey;
import org.p4p.backpocketdriver.driverlog.model.JourneyClassifications;
import org.p4p.backpocketdriver.driverlog.model.JourneyLog;
import org.p4p.backpocketdriver.driverlog.model.JourneyStatistics;
import org.p4p.backpocketdriver.driverlog.model.JourneyClassifications.RoadSpeedClassification;
import org.p4p.backpocketdriver.driverlog.model.JourneyClassifications.RoadTypeClassification;
import org.p4p.backpocketdriver.driverlog.model.JourneyClassifications.TimeOfDay;
import org.p4p.backpocketdriver.driverlog.webservice.Location;
import org.p4p.backpocketdriver.driverlog.webservice.TomTomService;

/**
 * Calculates statistics relating to the journey
 * @author Dheeraj
 *
 */
public class CalculateJourneyStatistics {

	private List<JourneyLog> journeyLogs = new ArrayList<JourneyLog>();
	private JourneyStatistics journeyStatistics = new JourneyStatistics();
	private Journey journey = new Journey();
	private TomTomService tomtom = new TomTomService();
	private Location location = new Location("Android App");
	private float[] distanceArray = new float[3];
	



	public CalculateJourneyStatistics(List<JourneyLog> journeyLogs) {
		this.journeyLogs = journeyLogs;
	}

	/**
	 * Calculates total duration of the journey
	 * @return
	 */
	public int calculateJourneyDuration(){
		long timeStamp1 = journeyLogs.get(0).getTimeStamp();
		long timeStamp2 = journeyLogs.get(journeyLogs.size()-1).getTimeStamp(); 		
		Date date1 = convertToDate(timeStamp1);
		Date date2 = convertToDate(timeStamp2);
		int totalDuration = calculateTimeDuration(date1, date2);
		journey.setDuration(totalDuration);
		return totalDuration;

	}

	/**
	 * Method that performs all the computations necessary to return back the Journey object with
	 * Statistics
	 * @return
	 */
	public ReturnResults run(){
		setJourneyDate();
		calculateJourneyDuration();
		calculateTotalJourneyDistance();

		calculateRoadSpeed(RoadSpeedClassification.TWENTY);
		calculateRoadSpeed(RoadSpeedClassification.FIFTY);
		calculateRoadSpeed(RoadSpeedClassification.SIXTY);
		calculateRoadSpeed(RoadSpeedClassification.EIGHTY);
		calculateRoadSpeed(RoadSpeedClassification.HUNDRED);
		calculateRoadSpeed(RoadSpeedClassification.MISCELLANEOUS);

		calculateRoadSpeedAndTimeOfDay(RoadSpeedClassification.TWENTY, TimeOfDay.DAY);
		calculateRoadSpeedAndTimeOfDay(RoadSpeedClassification.TWENTY, TimeOfDay.NIGHT);
		calculateRoadSpeedAndTimeOfDay(RoadSpeedClassification.FIFTY, TimeOfDay.DAY);
		calculateRoadSpeedAndTimeOfDay(RoadSpeedClassification.FIFTY, TimeOfDay.NIGHT);
		calculateRoadSpeedAndTimeOfDay(RoadSpeedClassification.SIXTY, TimeOfDay.DAY);
		calculateRoadSpeedAndTimeOfDay(RoadSpeedClassification.SIXTY, TimeOfDay.NIGHT);
		calculateRoadSpeedAndTimeOfDay(RoadSpeedClassification.EIGHTY, TimeOfDay.DAY);
		calculateRoadSpeedAndTimeOfDay(RoadSpeedClassification.EIGHTY, TimeOfDay.NIGHT);
		calculateRoadSpeedAndTimeOfDay(RoadSpeedClassification.HUNDRED, TimeOfDay.DAY);
		calculateRoadSpeedAndTimeOfDay(RoadSpeedClassification.HUNDRED, TimeOfDay.NIGHT);
		calculateRoadSpeedAndTimeOfDay(RoadSpeedClassification.MISCELLANEOUS, TimeOfDay.DAY);
		calculateRoadSpeedAndTimeOfDay(RoadSpeedClassification.MISCELLANEOUS, TimeOfDay.NIGHT);

		calculateRoadType(RoadTypeClassification.RURAL);
		calculateRoadType(RoadTypeClassification.URBAN);
		calculateRoadType(RoadTypeClassification.MOTORWAY);

		calculateRoadTypeAndTimeOfDay(RoadTypeClassification.MOTORWAY, TimeOfDay.DAY);
		calculateRoadTypeAndTimeOfDay(RoadTypeClassification.MOTORWAY, TimeOfDay.NIGHT);
		calculateRoadTypeAndTimeOfDay(RoadTypeClassification.URBAN, TimeOfDay.DAY);
		calculateRoadTypeAndTimeOfDay(RoadTypeClassification.URBAN, TimeOfDay.NIGHT);
		calculateRoadTypeAndTimeOfDay(RoadTypeClassification.RURAL, TimeOfDay.DAY);
		calculateRoadTypeAndTimeOfDay(RoadTypeClassification.RURAL, TimeOfDay.NIGHT);

		calculateTimeOfDay(TimeOfDay.DAY);
		calculateTimeOfDay(TimeOfDay.NIGHT);
		returnCoordinates();

		ReturnResults rr = new ReturnResults(journey, journeyStatistics);

		return rr;


	}

	/**
	 * Sets the journey's date to an appropriate Date format
	 */
	public void setJourneyDate(){
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		Date date = convertToDate(journeyLogs.get(0).getTimeStamp());
		String datestring = dateFormat.format(date); 
		journey.setDate(datestring);
	}

	/**
	 * Calculates the total distance of the journey
	 * @return
	 */
	public int calculateTotalJourneyDistance(){
		int totalDistance = computeDistance(0, journeyLogs.size()-1);
		journey.setDistanceTravelled(totalDistance);
		System.out.println(totalDistance);
		return totalDistance;

	}

	/**
	 * This method goes through the JourneyLogs object and calculates the distance, average speed and 
	 * duration for a particular Road Type
	 * @param roadType
	 */
	public void calculateRoadType(JourneyClassifications.RoadTypeClassification roadType){
		int distance = 0;
		int duration = 0;
		double averageSpeed = 0.0;

		int firstIndex = 0;
		boolean started = false;

		long startTime = 0;
		long endTime = 0;
		for (int i = 0; i<journeyLogs.size(); i++){
			if (journeyLogs.get(i).getRoadType().equals(roadType)){
				//System.out.println("into 1");

				if (started == false){
					firstIndex = i;
					started = true;
				}


				if (i == journeyLogs.size() -1){
					startTime = journeyLogs.get(firstIndex).getTimeStamp();
					endTime = journeyLogs.get(i).getTimeStamp();
					Date startDate = convertToDate(startTime);
					Date endDate = convertToDate(endTime);
					duration = duration + calculateTimeDuration(startDate, endDate);
					String[] coordinates = getCoordinates(firstIndex, journeyLogs.size() - 1);

					
					distance = distance + computeDistance(firstIndex, journeyLogs.size() - 1);

					averageSpeed = averageSpeed + calculateAverageSpeed(firstIndex, journeyLogs.size());


					


				}
			}else if (!(journeyLogs.get(i).getRoadType().equals(roadType))){
				if (i == 0){
					continue;
				}
				if (journeyLogs.get(i-1).getRoadType().equals(roadType)){
					startTime = journeyLogs.get(firstIndex).getTimeStamp();
					endTime = journeyLogs.get(i-1).getTimeStamp();
					Date startDate = convertToDate(startTime);
					Date endDate = convertToDate(endTime);
					duration = duration + calculateTimeDuration(startDate, endDate);

					String[] coordinates = getCoordinates(firstIndex, i);
					
					
					distance = distance + computeDistance(firstIndex, i);

					averageSpeed = averageSpeed + calculateAverageSpeed(firstIndex, i);
					started = false;

					

				}

			}else{
				continue;
			}
		}

		//set JourneyStats object Urban values
		if (roadType.equals(JourneyClassifications.RoadTypeClassification.URBAN)){
			journeyStatistics.setDistanceTravelled_urban_total(distance);	
			journeyStatistics.setTimeDriven_urban_total(duration);
			journeyStatistics.setAverageSpeed_urban((int)averageSpeed);
		}else if (roadType.equals(JourneyClassifications.RoadTypeClassification.RURAL)){
			journeyStatistics.setDistanceTravelled_rural_total(distance);	
			journeyStatistics.setTimeDriven_rural_total(duration);
			journeyStatistics.setAverageSpeed_rural((int)averageSpeed);
		}else{
			journeyStatistics.setDistanceTravelled_motorway_total(distance);
			journeyStatistics.setTimeDriven_motorway_total(duration);
			journeyStatistics.setAverageSpeed_motorway((int)averageSpeed);

		}
		System.out.println("Total Duration: " + (duration) + " sec");
		System.out.println("Total Distance: " + (distance) + " m");
		System.out.println("Avg Speed: " + averageSpeed + " km/h");
	}

	/**
	 * Calculates the distance, duration and average speed for a particular road type AND a particular time in the day
	 * @param roadType
	 * @param timeOfDay
	 */
	public void calculateRoadTypeAndTimeOfDay(JourneyClassifications.RoadTypeClassification roadType, 
			JourneyClassifications.TimeOfDay timeOfDay){
		int distance = 0;
		int duration = 0;

		int firstIndex = 0;
		boolean started = false;

		long startTime = 0;
		long endTime = 0;
		for (int i = 0; i<journeyLogs.size(); i++){
			if ((journeyLogs.get(i).getRoadType().equals(roadType)) 
					&& (journeyLogs.get(i).getTimeOfDay().equals(timeOfDay))){
				

				if (started == false){
					firstIndex = i;
					started = true;
				}


				if (i == journeyLogs.size() -1){
					startTime = journeyLogs.get(firstIndex).getTimeStamp();
					endTime = journeyLogs.get(i).getTimeStamp();
					Date startDate = convertToDate(startTime);
					Date endDate = convertToDate(endTime);
					duration = duration + calculateTimeDuration(startDate, endDate);
					String[] coordinates = getCoordinates(firstIndex, journeyLogs.size() - 1);

					distance = distance + computeDistance(firstIndex, journeyLogs.size() - 1);
					System.out.println("Duration: " + duration);


				}
			}else if ((!(journeyLogs.get(i).getRoadType().equals(roadType))) 
					&& (journeyLogs.get(i).getTimeOfDay().equals(timeOfDay))){
				if (i == 0){
					continue;
				}
				if ((journeyLogs.get(i-1).getRoadType().equals(roadType)) 
						&& (journeyLogs.get(i).getTimeOfDay().equals(timeOfDay))){
					startTime = journeyLogs.get(firstIndex).getTimeStamp();
					endTime = journeyLogs.get(i-1).getTimeStamp();
					Date startDate = convertToDate(startTime);
					Date endDate = convertToDate(endTime);
					duration = duration + calculateTimeDuration(startDate, endDate);
					String[] coordinates = getCoordinates(firstIndex, i);

					distance = distance + computeDistance(firstIndex, i);

					started = false;

					System.out.println("Duration: " + duration);

				}

			}else{
				continue;
			}
		}

		//set JourneyStats object Urban values
		if (roadType.equals(JourneyClassifications.RoadTypeClassification.URBAN)){
			if (timeOfDay.equals(JourneyClassifications.TimeOfDay.DAY)){
				journeyStatistics.setDistanceTravelled_urban_day(distance);	
				journeyStatistics.setTimeDriven_urban_day(duration);
			}else{
				journeyStatistics.setDistanceTravelled_urban_night(distance);	
				journeyStatistics.setTimeDriven_urban_night(duration);
			}

		}else if (roadType.equals(JourneyClassifications.RoadTypeClassification.RURAL)){
			if (timeOfDay.equals(JourneyClassifications.TimeOfDay.DAY)){
				journeyStatistics.setDistanceTravelled_rural_night(distance);	
				journeyStatistics.setTimeDriven_rural_night(duration);
			}else{
				journeyStatistics.setDistanceTravelled_rural_night(distance);	
				journeyStatistics.setTimeDriven_rural_night(duration);
			}

		}else{
			if (timeOfDay.equals(JourneyClassifications.TimeOfDay.DAY)){
				journeyStatistics.setDistanceTravelled_motorway_day(distance);	
				journeyStatistics.setTimeDriven_motorway_day(duration);
			}else{
				journeyStatistics.setDistanceTravelled_motorway_night(distance);	
				journeyStatistics.setTimeDriven_motorway_night(duration);
			}

		}
		

	}

	/**
	 * Calculates distance, duration and avg speed under various speed limits
	 * @param roadSpeed
	 */
	public void calculateRoadSpeed(JourneyClassifications.RoadSpeedClassification roadSpeed){
		int distance = 0;
		int duration = 0;
		double averageSpeed = 0.0;
		int firstIndex = 0;
		boolean started = false;

		long startTime = 0;
		long endTime = 0;
		for (int i = 0; i<journeyLogs.size(); i++){
			if (journeyLogs.get(i).getRoadSpeed().equals(roadSpeed)){
				

				if (started == false){
					firstIndex = i;
					started = true;
				}


				if (i == journeyLogs.size() -1){
					startTime = journeyLogs.get(firstIndex).getTimeStamp();
					System.out.println("start time: " + startTime);
					endTime = journeyLogs.get(i).getTimeStamp();
					System.out.println("end time: " + endTime);
					Date startDate = convertToDate(startTime);
					Date endDate = convertToDate(endTime);
					duration = duration + calculateTimeDuration(startDate, endDate);
					String[] coordinates = getCoordinates(firstIndex, journeyLogs.size() -1);

					distance = distance + computeDistance(firstIndex, journeyLogs.size() - 1);

					averageSpeed = averageSpeed + calculateAverageSpeed(firstIndex, journeyLogs.size());

					System.out.println("Duration: " + duration);


				}
			}else if (!(journeyLogs.get(i).getRoadSpeed().equals(roadSpeed))){
				
				if (i == 0){
					continue;
				}
				if (journeyLogs.get(i-1).getRoadSpeed().equals(roadSpeed)){
					startTime = journeyLogs.get(firstIndex).getTimeStamp();
					endTime = journeyLogs.get(i-1).getTimeStamp();
					
					Date startDate = convertToDate(startTime);
					Date endDate = convertToDate(endTime);
					duration = duration + calculateTimeDuration(startDate, endDate);
					String[] coordinates = getCoordinates(firstIndex, i);

					distance = distance + computeDistance(firstIndex, i);

					averageSpeed = averageSpeed + calculateAverageSpeed(firstIndex, i);
					started = false;
					

				}

			}else{
				continue;
			}


		}

		if (roadSpeed.equals(JourneyClassifications.RoadSpeedClassification.TWENTY)){
			journeyStatistics.setDistanceTravelled_Twenty_total(distance);	
			journeyStatistics.setTimeDriven_Twenty_total(duration);
			journeyStatistics.setAverageSpeed_Twenty((int)averageSpeed);
		}else if (roadSpeed.equals(JourneyClassifications.RoadSpeedClassification.FIFTY)){
			journeyStatistics.setDistanceTravelled_Fifty_total(distance);	
			journeyStatistics.setTimeDriven_Fifty_total(duration);
			journeyStatistics.setAverageSpeed_Fifty((int)averageSpeed);
		}else if (roadSpeed.equals(JourneyClassifications.RoadSpeedClassification.SIXTY)){
			journeyStatistics.setDistanceTravelled_Sixty_total(distance);	
			journeyStatistics.setTimeDriven_Sixty_total(duration);
			journeyStatistics.setAverageSpeed_Sixty((int)averageSpeed);
		}else if (roadSpeed.equals(JourneyClassifications.RoadSpeedClassification.EIGHTY)){
			journeyStatistics.setDistanceTravelled_Eighty_total(distance);	
			journeyStatistics.setTimeDriven_Eighty_total(duration);
			journeyStatistics.setAverageSpeed_Eighty((int)averageSpeed);
		}else if (roadSpeed.equals(JourneyClassifications.RoadSpeedClassification.HUNDRED)){ 
			journeyStatistics.setDistanceTravelled_Hundred_total(distance);	
			journeyStatistics.setTimeDriven_Hundred_total(duration);
			journeyStatistics.setAverageSpeed_Hundred((int)averageSpeed);
		}else{
			journeyStatistics.setDistanceTravelled_Miscellaneous_total(distance);	
			journeyStatistics.setTimeDriven_Miscellaneous_total(duration);
			journeyStatistics.setAverageSpeed_Miscellaneous((int)averageSpeed);
		}

		System.out.println("Total Duration: " + (duration) + " sec");
		System.out.println("Avg Speed: " + averageSpeed + " km/h");
	}

	/**
	 * Calculates duration and distance based on road speed limit and time of day
	 * @param roadSpeed
	 * @param timeOfDay
	 */
	public void calculateRoadSpeedAndTimeOfDay(JourneyClassifications.RoadSpeedClassification roadSpeed,
			JourneyClassifications.TimeOfDay timeOfDay){
		int distance = 0;
		int duration = 0;
		int firstIndex = 0;
		boolean started = false;

		long startTime = 0;
		long endTime = 0;
		for (int i = 0; i<journeyLogs.size(); i++){
			if ((journeyLogs.get(i).getRoadSpeed().equals(roadSpeed))
					&& (journeyLogs.get(i).getTimeOfDay().equals(timeOfDay))){


				if (started == false){
					firstIndex = i;
					started = true;
				}


				if (i == journeyLogs.size() -1){
					
					startTime = journeyLogs.get(firstIndex).getTimeStamp();
					endTime = journeyLogs.get(i).getTimeStamp();
					
					Date startDate = convertToDate(startTime);
					Date endDate = convertToDate(endTime);
					duration = duration + calculateTimeDuration(startDate, endDate);
					String[] coordinates = getCoordinates(firstIndex, journeyLogs.size() -1);
					distance = distance + computeDistance(firstIndex, journeyLogs.size() - 1);
					//calculateDuration and add to variable

					//System.out.println("Duration: " + duration);


				}
			}else if ((!(journeyLogs.get(i).getRoadSpeed().equals(roadSpeed)))
					&& (journeyLogs.get(i).getTimeOfDay().equals(timeOfDay))){
			
				if (i == 0){
					continue;
				}
				if ((journeyLogs.get(i-1).getRoadSpeed().equals(roadSpeed))
						&& (journeyLogs.get(i).getTimeOfDay().equals(timeOfDay))){
			
					
					startTime = journeyLogs.get(firstIndex).getTimeStamp();
					endTime = journeyLogs.get(i-1).getTimeStamp();
					Date startDate = convertToDate(startTime);
					Date endDate = convertToDate(endTime);
					duration = duration + calculateTimeDuration(startDate, endDate);
					String[] coordinates = getCoordinates(firstIndex, i);

					distance = distance + computeDistance(firstIndex, i);

					started = false;


				}

			}else{
				continue;
			}


		}

		if (roadSpeed.equals(JourneyClassifications.RoadSpeedClassification.TWENTY)){
			if (timeOfDay.equals(JourneyClassifications.TimeOfDay.DAY)){
				journeyStatistics.setDistanceTravelled_Twenty_day(distance);	
				journeyStatistics.setTimeDriven_Twenty_day(duration);
			}else{
				journeyStatistics.setDistanceTravelled_Twenty_night(distance);	
				journeyStatistics.setTimeDriven_Twenty_night(duration);
			}
		}else if (roadSpeed.equals(JourneyClassifications.RoadSpeedClassification.FIFTY)){
			if (timeOfDay.equals(JourneyClassifications.TimeOfDay.DAY)){
				journeyStatistics.setDistanceTravelled_Fifty_day(distance);	
				journeyStatistics.setTimeDriven_Fifty_day(duration);
			}else{
				journeyStatistics.setDistanceTravelled_Fifty_night(distance);	
				journeyStatistics.setTimeDriven_Fifty_night(duration);
			}
		}else if (roadSpeed.equals(JourneyClassifications.RoadSpeedClassification.SIXTY)){
			if (timeOfDay.equals(JourneyClassifications.TimeOfDay.DAY)){
				journeyStatistics.setDistanceTravelled_Sixty_day(distance);	
				journeyStatistics.setTimeDriven_Sixty_day(duration);
			}else{
				journeyStatistics.setDistanceTravelled_Sixty_night(distance);	
				journeyStatistics.setTimeDriven_Sixty_night(duration);
			}		
		}else if (roadSpeed.equals(JourneyClassifications.RoadSpeedClassification.EIGHTY)){
			if (timeOfDay.equals(JourneyClassifications.TimeOfDay.DAY)){
				journeyStatistics.setDistanceTravelled_Eighty_day(distance);	
				journeyStatistics.setTimeDriven_Eighty_day(duration);
			}else{
				journeyStatistics.setDistanceTravelled_Eighty_night(distance);	
				journeyStatistics.setTimeDriven_Eighty_night(duration);
			}
		}else if (roadSpeed.equals(JourneyClassifications.RoadSpeedClassification.HUNDRED)){ 
			if (timeOfDay.equals(JourneyClassifications.TimeOfDay.DAY)){
				journeyStatistics.setDistanceTravelled_Hundred_day(distance);	
				journeyStatistics.setTimeDriven_Hundred_day(duration);
			}else{
				journeyStatistics.setDistanceTravelled_Hundred_night(distance);	
				journeyStatistics.setTimeDriven_Hundred_night(duration);
			}
		}else{
			if (timeOfDay.equals(JourneyClassifications.TimeOfDay.DAY)){
				journeyStatistics.setDistanceTravelled_Miscellaneous_day(distance);	
				journeyStatistics.setTimeDriven_Miscellaneous_day(duration);
			}else{
				journeyStatistics.setDistanceTravelled_Miscellaneous_night(distance);	
				journeyStatistics.setTimeDriven_Miscellaneous_night(duration);
			}
		}

		System.out.println("Total Duration: " + (duration) + " sec");
	}


	/**
	 * Calculates the distance and duration at a particular time of the day
	 * @param timeOfDay
	 */
	public void calculateTimeOfDay(JourneyClassifications.TimeOfDay timeOfDay){
		int distance = 0;
		int duration = 0;
		int firstIndex = 0;
		boolean started = false;

		long startTime = 0;
		long endTime = 0;
		for (int i = 0; i<journeyLogs.size(); i++){
			if (journeyLogs.get(i).getTimeOfDay().equals(timeOfDay)){

				if (started == false){
					firstIndex = i;
					started = true;
				}


				if (i == journeyLogs.size() -1){
				
					startTime = journeyLogs.get(firstIndex).getTimeStamp();
					endTime = journeyLogs.get(i).getTimeStamp();
				
					Date startDate = convertToDate(startTime);
					Date endDate = convertToDate(endTime);
					duration = duration + calculateTimeDuration(startDate, endDate);
					String[] coordinates = getCoordinates(firstIndex, journeyLogs.size() -1);

					distance = distance + computeDistance(firstIndex, journeyLogs.size() - 1);
					//calculateDuration and add to variable

					//System.out.println("Duration: " + duration);


				}
			}else if (!(journeyLogs.get(i).getTimeOfDay().equals(timeOfDay))){
				
				if (i == 0){
					continue;
				}
				if (journeyLogs.get(i-1).getTimeOfDay().equals(timeOfDay)){
					
					startTime = journeyLogs.get(firstIndex).getTimeStamp();
					System.out.println("start time: " + startTime);
					endTime = journeyLogs.get(i-1).getTimeStamp();
					System.out.println("end time: " + endTime);
					Date startDate = convertToDate(startTime);
					Date endDate = convertToDate(endTime);
					duration = duration + calculateTimeDuration(startDate, endDate);
					String[] coordinates = getCoordinates(firstIndex, i-1);

					distance = distance + computeDistance(firstIndex, i -1);

					started = false;
					System.out.println("Duration: " + duration);

				}

			}else{
				continue;
			}

			
			System.out.println("Duration: " + (duration) + " sec");
		}

		if (timeOfDay.equals(JourneyClassifications.TimeOfDay.DAY)){
			journeyStatistics.setDistanceTravelled_day_total(distance);	
			journeyStatistics.setTimeDriven_day_total(duration);
		}else{ 
			journeyStatistics.setDistanceTravelled_night_total(distance);	
			journeyStatistics.setTimeDriven_night_total(duration);
		}

		System.out.println("Total Duration: " + (duration) + " sec");

	}

	/**
	 * Helper method that returns an array of coordinates representing the latitude and longitude for
	 * start and end indexes
	 * @param firstIndex
	 * @param lastIndex
	 * @return
	 */
	private String[] getCoordinates(int firstIndex, int lastIndex) {
		String[] coordinates = new String[2];		
		coordinates[0] = journeyLogs.get(firstIndex).getCoordinates();
		coordinates[1] = journeyLogs.get(lastIndex).getCoordinates();
		return coordinates;

	}

	/**
	 * Helper method that calculates the distance between the start and end indexes
	 * specified
	 * @param firstIndex
	 * @param lastIndex
	 * @return
	 */
	private int computeDistance(int firstIndex, int lastIndex){
		int i = firstIndex;
		int totalDistance = 0;
		int range = i + 1;

		while(i<lastIndex && range < lastIndex){
			System.out.println(i);
			String[] coordinates= new String[2];
			coordinates[0] = journeyLogs.get(i).getCoordinates();
			coordinates[1] = journeyLogs.get(range).getCoordinates();
			
			double[] result = splitCoordinates(coordinates[0], coordinates[1]);
			distanceArray = location.computeDistanceAndBearing(result[0], result[1], result[2], result[3], distanceArray);
			
		
			
			if(distanceArray[0] > 500 || distanceArray[0]<= journeyLogs.get(i).getAccuracy()){
				range++;
			}else{
				totalDistance += distanceArray[0];
				i = range;
				range++;
			}
			
		}
		distanceArray[0] = 0;
		return totalDistance;
	}
	
	/**
	 * Helper method to split the coordinates into latitide and longitude positions
	 * @param origin
	 * @param destination
	 * @return
	 */
	private double[] splitCoordinates(String origin, String destination){
		double originLatitude = 0;
		double originLongitude = 0;
		double destLatitude = 0;
		double destLongitude = 0;
		double[] returnResult = new double[4];
		
		originLatitude = Double.parseDouble(origin.split(",")[0]);
		originLongitude = Double.parseDouble(origin.split(",")[1]);
		
		destLatitude = Double.parseDouble(destination.split(",")[0]);
		destLongitude = Double.parseDouble(destination.split(",")[1]);
		
		returnResult[0] = originLatitude;
		returnResult[1] = originLongitude;
		returnResult[2] = destLatitude;
		returnResult[3] = destLongitude;
		
		return returnResult;
		
	}
	
	private void returnCoordinates(){
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.setLength(0);
		for(int i =0; i<journeyLogs.size(); i++){
			if(i == journeyLogs.size() -1){
				stringBuilder.append(journeyLogs.get(i).getCoordinates());
			}else{
				stringBuilder.append(journeyLogs.get(i).getCoordinates());
				stringBuilder.append("|");
			}
		}
		
		journey.setCoordinates(stringBuilder.toString());
		
		
	}
	
	/**
	 * Helper method to calculate the average speed from start and end indexes 
	 * specified
	 * @param firstIndex
	 * @param lastIndex
	 * @return
	 */
	private double calculateAverageSpeed(int firstIndex, int lastIndex) {
		double totalSpeed = 0.0;
		for (int j =firstIndex; j<lastIndex; j++){
			totalSpeed = totalSpeed + journeyLogs.get(j).getDriverSpeed(); 	
		}
		double avgSpeed = totalSpeed/(lastIndex -1);
		return avgSpeed;
	}

	/**
	 * Helper method to calculate the time duration from start and end indexes
	 * @param d1
	 * @param d2
	 * @return
	 */
	private int calculateTimeDuration(Date d1, Date d2){
		long diff = d2.getTime() - d1.getTime();
		long diffSeconds = diff / 1000; 

		return (int)diffSeconds;
	}

	/**
	 * Converts unix time stamp date to a Date object
	 * @param unixTimeStamp
	 * @return
	 */
	private Date convertToDate(long unixTimeStamp) {
		Date date = new Date ();
		date.setTime((long)unixTimeStamp*1000);
		return date;
	}

	


	public List<JourneyLog> getJourneyLogs() {
		return journeyLogs;
	}

	public void setJourneyLogs(List<JourneyLog> journeyLogs) {
		this.journeyLogs = journeyLogs;
	}

	public JourneyStatistics getJourneyStatistics() {
		return journeyStatistics;
	}

	public void setJourneyStatistics(JourneyStatistics journeyStatistics) {
		this.journeyStatistics = journeyStatistics;
	}

	public Journey getJourney() {
		return journey;
	}

	public void setJourney(Journey journey) {
		this.journey = journey;
	}


}
