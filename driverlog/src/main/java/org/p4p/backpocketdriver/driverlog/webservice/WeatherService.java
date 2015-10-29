package org.p4p.backpocketdriver.driverlog.webservice;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.bitpipeline.lib.owm.OwmClient;
import org.bitpipeline.lib.owm.WeatherData;
import org.bitpipeline.lib.owm.WeatherHistoryCityResponse;
import org.bitpipeline.lib.owm.OwmClient.HistoryType;
import org.bitpipeline.lib.owm.WeatherData.WeatherCondition;
import org.json.JSONException;
import org.p4p.backpocketdriver.driverlog.model.WeatherDetails;
import org.p4p.backpocketdriver.driverlog.model.WeatherDetails.WeatherConditionClassification;

public class WeatherService {
	
	public WeatherService() {

	}

	private Collection<WeatherDetails> weatherDetails = new ArrayList<WeatherDetails>();

	public Collection<WeatherDetails> getWeatherDetails() {
		return weatherDetails;
	}

	public void setWeatherDetails(Collection<WeatherDetails> weatherDetails) {
		this.weatherDetails = weatherDetails;
	}
	
	public Collection<WeatherDetails> findWeather(int cityCode) throws JSONException, IOException{
		OwmClient owm = new OwmClient();
		WeatherHistoryCityResponse weatherHistory = owm.historyWeatherAtCity(cityCode, HistoryType.HOUR);
		if (weatherHistory.hasHistory()){
			List<WeatherData> historyData = weatherHistory.getHistory();
			for (WeatherData data : historyData){
				WeatherCondition weatherCondition = data.getWeatherConditions().get(0);
				
				long unixTimeStamp = data.getDateTime();	
				Date date = convertToDate(unixTimeStamp);
			
				int conditionCode = weatherCondition.getCode().getId();
				WeatherConditionClassification classifcation = classifyCode(conditionCode);
				WeatherDetails weather = new WeatherDetails(date, classifcation);
				weatherDetails.add(weather);
			}
			
		}else{
			System.out.println("No history available");
		}
		return weatherDetails;
	}

	public Date convertToDate(Long unixTimeStamp) {
		Date date = new Date ();
		date.setTime((long)unixTimeStamp*1000);
		return date;
	}
	
	public WeatherConditionClassification classifyCode(int conditionCode){
		//THUNDERSTORM
		if (conditionCode >= 200 && conditionCode < 300){
			return WeatherConditionClassification.RAIN;
		//DRIZZLE
		}else if (conditionCode >= 300 && conditionCode < 400){
			return WeatherConditionClassification.DRIZZLE;
		//RAIN
		}else if (conditionCode >= 500 && conditionCode < 600){
			return WeatherConditionClassification.RAIN;
		//SNOW
		}else if (conditionCode >= 600 && conditionCode < 700){
			return WeatherConditionClassification.OTHER;
		//ATMOSPHERE
		}else if (conditionCode >= 700 && conditionCode < 800){
			return WeatherConditionClassification.FOG;
		//FINE
		}else if (conditionCode >= 800 && conditionCode < 900){
			return WeatherConditionClassification.FINE;
		}else if (conditionCode >= 900 && conditionCode < 1000){
		//EXTREME
			return WeatherConditionClassification.OTHER;
		//UNKOWN
		}else if (conditionCode == Integer.MIN_VALUE){
			return WeatherConditionClassification.OTHER;
		}
		//shouldn't come here
		return null;
	}

}
