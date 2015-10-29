package org.p4p.backpocketdriver.driverlog.importdata;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.p4p.backpocketdriver.driverlog.model.JourneyClassifications;
import org.p4p.backpocketdriver.driverlog.model.JourneyLog;
import org.p4p.backpocketdriver.driverlog.model.JourneyClassifications.RoadSpeedClassification;
import org.p4p.backpocketdriver.driverlog.model.JourneyClassifications.RoadTypeClassification;
import org.p4p.backpocketdriver.driverlog.model.JourneyClassifications.TimeOfDay;
import org.p4p.backpocketdriver.driverlog.webservice.TomTomService;
import org.p4p.backpocketdriver.driverlog.webservice.WeatherService;

/**
 * Reads the CSV file containing the raw journey data and writes another CSV file containing information
 * for Journey Statistics
 * @author Dheeraj
 *
 */
public class ReadCsv {

	private String inputFile = "Henderson_to_Pakuranga";

	private Long timeStamp;
	private Double time;
	private String coordinates;
	private double driverSpeed;
	private RoadSpeedClassification roadSpeed;
	private RoadTypeClassification roadType;
	private TimeOfDay timeOfDay;
	private TomTomService tomService = new TomTomService();
	private JourneyClassifications jc = new JourneyClassifications();
	private WeatherService ws = new WeatherService();
	private List<JourneyLog> jl = new ArrayList<JourneyLog>();
	private double accuracy;

	public static void main(String[] args) {
		ReadCsv csv = new ReadCsv();
		csv.run();
	}

	public void run() {
		System.out.println(getClass().getClassLoader().toString());
		URL csvFile = getClass().getClassLoader().getResource("Data" + File.separator + inputFile + ".csv");
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";

		try {

			br = new BufferedReader(new FileReader(csvFile.getPath()));
			while ((line = br.readLine()) != null) {
				
				// use comma as separator
				String[] country = line.split(cvsSplitBy);

				if(country.length == 1){
					if(country[0].equals("#Journey Start")){
						System.out.println("Journey has started");
					}else if(country[0].equals("#Journey End")){
						System.out.println("Journey has finished");
					}

				}else if(country[0].equals("# Timestamp")){
					continue;
				}
				else{
					time = Double.parseDouble(country[0])/1000;
					timeStamp = time.longValue();
					coordinates = country[1] + " " +country[2];
					driverSpeed = Double.parseDouble(country[4]) * 3.6;
					roadSpeed = tomService.getSpeedLimit(convertCoordinates(coordinates));
					accuracy = Double.parseDouble(country[3]);
					roadType = tomService.getRoadTypeFromService(convertCoordinates(coordinates));
					Date date = ws.convertToDate(timeStamp);
					timeOfDay = jc.getTimeOfDay(date);
					JourneyLog jol = new JourneyLog(timeStamp, coordinates, driverSpeed, roadSpeed,roadType,timeOfDay,accuracy);
					
					jl.add(jol);

				}
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
		
		String outputFile = inputFile + "-results.csv";
		generateCsvFile(getPath(outputFile));
		System.out.println("Done");
	}
	
	public String convertCoordinates(String coordinates){
		return coordinates = coordinates.replaceAll(" ",",");
	}
	
	private String getPath(String fileName){
		return System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "java" + File.separator + "Data" +  File.separator + "Results" + File.separator + fileName;
	}
	
	private void generateCsvFile(String sFileName)
	{
		try
		{
			FileWriter writer = new FileWriter(sFileName);
			for(JourneyLog journeyLog : jl){
				writer.append(journeyLog.toString());
				writer.append('\n');
			}


			writer.flush();
			writer.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		} 
	}

}
