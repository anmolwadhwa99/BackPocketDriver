package org.p4p.backpocketdriver.driverlog.importdata;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
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
import org.p4p.backpocketdriver.driverlog.model.WeatherDetails.WeatherConditionClassification;
import org.p4p.backpocketdriver.driverlog.service.JourneyService;
import org.p4p.backpocketdriver.driverlog.webservice.TomTomService;
import org.p4p.backpocketdriver.driverlog.webservice.WeatherService;

/**
 * Reads the generated CSV file and transfers data to Java objects to calculate Journey Statistics
 * @author Dheeraj
 *
 */
public class ReadResults {
	private Long timeStamp;
	private String coordinates;
	private JourneyClassifications.RoadTypeClassification roadType;
	private JourneyClassifications.RoadSpeedClassification roadSpeed;
	private JourneyClassifications.TimeOfDay timeOfDay;
	private double driverSpeed;
	private double accuracy;
	
	private Journey journey;
	private JourneyStatistics journeyStatistics;
	int count = 1;
	
	private String resultFile = "Tamaki_to_City-results.csv";
		

	private List<JourneyLog> jl = new ArrayList<JourneyLog>();

	public static void main(String[] args) {
		ReadResults rr = new ReadResults();
		rr.readResults();
	}
	
	public void readResults(){

		URL csvFile = getClass().getClassLoader().getResource("Data" + "/" + "Results" + "/" + resultFile);
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";

		try {

			br = new BufferedReader(new FileReader(csvFile.getPath()));
			while ((line = br.readLine()) != null) {
				
				// use comma as separator
				String[] results = line.split(cvsSplitBy);

		
					timeStamp = Long.parseLong(results[0]);
					coordinates = convertCoordinates(results[1]);
					roadType = findRoadType(results[2]);
					roadSpeed = findSpeedLimit(results[3]);
					timeOfDay = findTimeofDay(results[4]);
					driverSpeed = Double.parseDouble(results[5]);
					accuracy = Double.parseDouble(results[6]);
					
						
					JourneyLog log = new JourneyLog(timeStamp, coordinates, driverSpeed, roadSpeed, roadType, timeOfDay, accuracy);
					jl.add(log);
					
					System.out.println(count + " " + timeStamp + " " + coordinates + " " + roadType + " " + roadSpeed + " " + timeOfDay + " " + driverSpeed + " " + accuracy);
					count++;
				
					
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		
		
		CalculateJourneyStatistics stats = new CalculateJourneyStatistics(jl);
		ReturnResults rr = stats.run();
		Journey journey = rr.getJourney();
		JourneyStatistics js  = rr.getJourneyStatistics();
		

		journey.setJourneyName("Tamaki to City");
		journey.setWeather(WeatherConditionClassification.DRIZZLE);	
		js.setWeather(WeatherConditionClassification.DRIZZLE);
		JourneyService journeyService = new JourneyService();

		

		journeyService.addJourneyStatistic(5, js);
		System.out.println("DONE!");
		

	}
	
	
	public JourneyClassifications.RoadTypeClassification findRoadType (String input){
		switch(input){
		case ("URBAN"):
			return JourneyClassifications.RoadTypeClassification.URBAN;
		case ("RURAL"):
			return JourneyClassifications.RoadTypeClassification.RURAL;
		case ("MOTORWAY"):
			return JourneyClassifications.RoadTypeClassification.MOTORWAY;
				
		}
		return null;
	}
	
	public JourneyClassifications.RoadSpeedClassification findSpeedLimit (String input){
		switch(input){
		case ("TWENTY"):
			return JourneyClassifications.RoadSpeedClassification.TWENTY;
		case ("FIFTY"):
			return JourneyClassifications.RoadSpeedClassification.FIFTY;
		case ("SIXTY"):
			return JourneyClassifications.RoadSpeedClassification.SIXTY;
		case ("EIGHTY"):
			return JourneyClassifications.RoadSpeedClassification.EIGHTY;
		case ("HUNDRED"):
			return JourneyClassifications.RoadSpeedClassification.HUNDRED;		
		case ("MISCELLANEOUS"):
			return JourneyClassifications.RoadSpeedClassification.MISCELLANEOUS;
		}
		return null;
	}
	
	public JourneyClassifications.TimeOfDay findTimeofDay (String input){
		switch(input){
		case ("DAY"):
			return JourneyClassifications.TimeOfDay.DAY;
		case ("NIGHT"):
			return JourneyClassifications.TimeOfDay.NIGHT;		
		}
		return null;
	}
	
	public String convertCoordinates(String coordinates){
		return coordinates = coordinates.replaceAll(" ",",");
	}
}
