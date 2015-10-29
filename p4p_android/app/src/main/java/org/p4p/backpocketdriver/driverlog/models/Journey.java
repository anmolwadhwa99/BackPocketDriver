package org.p4p.backpocketdriver.driverlog.models;

import java.io.Serializable;

/**
 * Created by Dheeraj on 6/07/15.
 */
public class Journey implements Serializable {

    private int journeyId;
    private String date;
    private int duration;
    private int distanceTravelled;
    private String weather;
    private String name;



    private JourneyStatistics journeyStatistics;



    private String coordinates;

    public Journey(){

    }


    public Journey(int journeyId, String date, int duration, int distanceTravelled, String weather, String name){
        this.journeyId = journeyId;
        this.date = date;
        this.duration = duration;
        this.distanceTravelled = distanceTravelled;
        this.weather = weather;
        this.name = name;
    }


    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getDistanceTravelled() {
        return distanceTravelled;
    }

    public void setDistanceTravelled(int distanceTravelled) {
        this.distanceTravelled = distanceTravelled;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getJourneyId() {
        return journeyId;
    }

    public void setJourneyId(int journeyId) {
        this.journeyId = journeyId;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    public JourneyStatistics getJourneyStatistics() {
        return journeyStatistics;
    }

    public void setJourneyStatistics(JourneyStatistics journeyStatistics) {
        this.journeyStatistics = journeyStatistics;
    }

    @Override
    public String toString(){
        return getName();
    }

}
