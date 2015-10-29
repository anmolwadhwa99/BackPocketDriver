package org.p4p.backpocketdriver.driverlog.model;

import java.util.Calendar;
import java.util.Date;

public class JourneyClassifications {
	
	public enum TimeOfDay{
		DAY, NIGHT
	}
	
	public enum RoadTypeClassification{
		URBAN, RURAL, MOTORWAY
	}
	
	public enum RoadSpeedClassification{
		TWENTY, FIFTY, SIXTY, EIGHTY, HUNDRED, MISCELLANEOUS
	}
	
	public TimeOfDay getTimeOfDay(Date date){
		
		
		Calendar now = Calendar.getInstance();
		now.set(Calendar.HOUR_OF_DAY, 18);
		now.set(Calendar.MINUTE, 00);
		Calendar givenDate = Calendar.getInstance();
		givenDate.setTime(date);

		boolean isBefore = now.before(givenDate);
		if (isBefore){
			return TimeOfDay.NIGHT;
		}else{
			return TimeOfDay.DAY;
		}
	}


}
