package org.p4p.backpocketdriver.driverlog.pojo.summary;


import org.p4p.backpocketdriver.driverlog.models.Journey;
import org.p4p.backpocketdriver.driverlog.models.JourneyStatistics;
import org.p4p.backpocketdriver.driverlog.pojo.summary.categories.RoadType;
import org.p4p.backpocketdriver.driverlog.pojo.summary.categories.SpeedLimit;
import org.p4p.backpocketdriver.driverlog.pojo.summary.categories.TimeOfDay;
import org.p4p.backpocketdriver.driverlog.pojo.summary.categories.Weather;
import org.p4p.backpocketdriver.driverlog.pojo.summary.sections.AverageSpeed;
import org.p4p.backpocketdriver.driverlog.pojo.summary.sections.Distance;
import org.p4p.backpocketdriver.driverlog.pojo.summary.sections.Duration;

import java.util.List;

/**
 * Created by user on 23/07/2015.
 */
public class LoadSummaryData {
    private List<JourneyStatistics> journeyStatistics;
    private List<Journey> journeys;
    private AverageSpeed averageSpeed;
    private Distance distance;
    private Duration duration;
    private SummaryStatistics summaryStatistics;



    public LoadSummaryData(List<JourneyStatistics> journeyStatistics, List<Journey> journeys) {
        this.journeyStatistics = journeyStatistics;
        this.journeys = journeys;
        averageSpeed = new AverageSpeed();
        duration = new Duration();
        distance = new Distance();

    }

    public SummaryStatistics setData(){
        //average speed
        averageSpeedSpeedLimit();
        averageSpeedRoadType();
        //distance
        distanceRoadType();
        distanceSpeedLimit();
        distanceTimeOfDay();
        distanceWeather();
        //duration
        durationRoadType();
        durationSpeedLimit();
        durationTimeOfDay();
        durationWeather();

        //initialise SummaryStats Object
        summaryStatistics = new SummaryStatistics(averageSpeed, duration, distance);
        return summaryStatistics;

    }

    public LoadSummaryData() {
    }


    public SummaryStatistics getSummaryStatistics() {
        return summaryStatistics;
    }

    public void setSummaryStatistics(SummaryStatistics summaryStatistics) {
        this.summaryStatistics = summaryStatistics;
    }

    public List<JourneyStatistics> getJourneyStatistics() {
        return journeyStatistics;
    }

    public void setJourneyStatistics(List<JourneyStatistics> journeyStatistics) {
        this.journeyStatistics = journeyStatistics;
    }

    public List<Journey> getJourneys() {
        return journeys;
    }

    public void setJourneys(List<Journey> journeys) {
        this.journeys = journeys;
    }

    private void averageSpeedSpeedLimit(){
        int twenty = 0;
        int fifty = 0;
        int sixty = 0;
        int eighty = 0;
        int hundred = 0;
        int miscellaneous = 0;

        int size = journeyStatistics.size();

        for(JourneyStatistics js : journeyStatistics){
            twenty += js.getAverageSpeed_Twenty();
            fifty += js.getAverageSpeed_Fifty();
            sixty += js.getAverageSpeed_Sixty();
            eighty += js.getAverageSpeed_Eighty();
            hundred += js.getAverageSpeed_Hundred();
            miscellaneous += js.getAverageSpeed_Miscellaneous();
        }

        twenty = twenty / size;
        fifty = fifty / size;
        eighty = eighty / size;
        hundred = hundred /size;

        SpeedLimit sp = new SpeedLimit(twenty, fifty, sixty, eighty, hundred, miscellaneous);
        averageSpeed.setSpeedLimit(sp);
    }

    private void averageSpeedRoadType(){
        int urban = 0;
        int rural = 0;
        int motorway = 0;

        int size = journeyStatistics.size();

        for(JourneyStatistics js : journeyStatistics){
            urban += js.getAverageSpeed_urban();
            rural += js.getAverageSpeed_rural();
            motorway += js.getAverageSpeed_motorway();
        }

        urban = urban / size;
        rural = rural / size;
        motorway = motorway / size;

        RoadType roadType = new RoadType(urban, rural, motorway);
        averageSpeed.setRoadType(roadType);
    }

    private void distanceWeather(){
        int distanceFine = 0;
        int distanceRain = 0;
        int distanceDrizzle = 0;
        int distanceFog = 0;
        int distanceOther = 0;

        for(Journey j : journeys){
            if(j.getWeather().equalsIgnoreCase("FINE")){
                distanceFine += j.getDistanceTravelled();
            }else if(j.getWeather().equalsIgnoreCase("DRIZZLE")){
                distanceDrizzle = j.getDistanceTravelled();
            }else if(j.getWeather().equalsIgnoreCase("FOG")){
                distanceFog = j.getDistanceTravelled();
            }else if(j.getWeather().equalsIgnoreCase("OTHER")){
                distanceOther = j.getDistanceTravelled();
            }else if(j.getWeather().equalsIgnoreCase("RAIN")){
                distanceRain = j.getDistanceTravelled();
            }
        }
        Weather weather = new Weather(distanceFine, distanceRain, distanceDrizzle, distanceFog, distanceOther);
        distance.setWeather(weather);
    }

    private void distanceRoadType(){
        int urban = 0;
        int rural = 0;
        int motorway = 0;

        for(JourneyStatistics js : journeyStatistics){
            urban += js.getDistanceTravelled_urban_total();
            rural += js.getDistanceTravelled_rural_total();
            motorway += js.getDistanceTravelled_motorway_total();
        }

        RoadType roadType = new RoadType(urban, rural, motorway);
        distance.setRoadType(roadType);

    }

    private void distanceTimeOfDay(){
        int day = 0;
        int night = 0;

        for(JourneyStatistics js : journeyStatistics){
            day += js.getDistanceTravelled_day_total();
            night += js.getDistanceTravelled_night_total();
        }

        TimeOfDay timeOfDay = new TimeOfDay(day, night);
        distance.setTimeOfDay(timeOfDay);
    }

    private void distanceSpeedLimit(){
        int twenty = 0;
        int fifty = 0;
        int sixty = 0;
        int eighty = 0;
        int hundred = 0;
        int miscellaneous = 0;

        for(JourneyStatistics js : journeyStatistics){
            twenty += js.getDistanceTravelled_Twenty_total();
            fifty += js.getDistanceTravelled_Fifty_total();
            sixty += js.getDistanceTravelled_Sixty_total();
            eighty += js.getDistanceTravelled_Eighty_total();
            hundred += js.getDistanceTravelled_Hundred_total();
            miscellaneous += js.getDistanceTravelled_Miscellaneous_total();
        }
        SpeedLimit sp = new SpeedLimit(twenty, fifty, sixty, eighty, hundred,miscellaneous);
        distance.setSpeedLimit(sp);

    }

    private void durationWeather(){
        int durationFine = 0;
        int durationRain = 0;
        int durationDrizzle = 0;
        int durationFog = 0;
        int durationOther = 0;

        for(Journey j : journeys){
            if(j.getWeather().equalsIgnoreCase("FINE")){
                durationFine += j.getDuration();
            }else if(j.getWeather().equalsIgnoreCase("DRIZZLE")){
                durationDrizzle = j.getDuration();
            }else if(j.getWeather().equalsIgnoreCase("FOG")){
                durationFog = j.getDuration();
            }else if(j.getWeather().equalsIgnoreCase("OTHER")){
                durationOther = j.getDuration();
            }else if(j.getWeather().equalsIgnoreCase("RAIN")){
                durationRain = j.getDuration();
            }
        }
        Weather weather = new Weather(durationFine, durationRain, durationDrizzle, durationFog, durationOther);
        duration.setWeather(weather);
    }

    private void durationRoadType(){
        int urban = 0;
        int rural = 0;
        int motorway = 0;

        for(JourneyStatistics js : journeyStatistics){
            urban += js.getTimeDriven_urban_total();
            rural += js.getTimeDriven_rural_total();
            motorway += js.getTimeDriven_motorway_total();
        }

        RoadType roadType = new RoadType(urban, rural, motorway);
        duration.setRoadType(roadType);

    }

    private void durationTimeOfDay(){
        int day = 0;
        int night = 0;

        for(JourneyStatistics js : journeyStatistics){
            day += js.getTimeDriven_day_total();
            night += js.getTimeDriven_night_total();
        }

        TimeOfDay timeOfDay = new TimeOfDay(day, night);
        duration.setTimeOfDay(timeOfDay);
    }

    private void durationSpeedLimit(){
        int twenty = 0;
        int fifty = 0;
        int sixty = 0;
        int eighty = 0;
        int hundred = 0;
        int miscellaneous = 0;

        for(JourneyStatistics js : journeyStatistics){
            twenty += js.getTimeDriven_Twenty_total();
            fifty += js.getTimeDriven_Fifty_total();
            sixty += js.getTimeDriven_Sixty_total();
            eighty += js.getTimeDriven_Eighty_total();
            hundred += js.getTimeDriven_Hundred_total();
            miscellaneous += js.getTimeDriven_Miscellaneous_total();
        }
        SpeedLimit sp = new SpeedLimit(twenty, fifty, sixty, eighty, hundred,miscellaneous);
        duration.setSpeedLimit(sp);

    }

}
