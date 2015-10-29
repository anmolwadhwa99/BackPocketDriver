package org.p4p.backpocketdriver.driverlog.pojo.summary.sections;

import org.p4p.backpocketdriver.driverlog.pojo.summary.categories.RoadType;
import org.p4p.backpocketdriver.driverlog.pojo.summary.categories.SpeedLimit;
import org.p4p.backpocketdriver.driverlog.pojo.summary.categories.TimeOfDay;
import org.p4p.backpocketdriver.driverlog.pojo.summary.categories.Weather;

/**
 * Created by user on 23/07/2015.
 */
public class Duration {
    private RoadType roadType;
    private SpeedLimit speedLimit;
    private Weather weather;
    private TimeOfDay timeOfDay;

    public Duration(){

    }

    public Duration(RoadType roadType, SpeedLimit speedLimit, Weather weather, TimeOfDay timeOfDay) {
        this.roadType = roadType;
        this.speedLimit = speedLimit;
        this.weather = weather;
        this.timeOfDay = timeOfDay;
    }

    public RoadType getRoadType() {
        return roadType;
    }

    public void setRoadType(RoadType roadType) {
        this.roadType = roadType;
    }

    public SpeedLimit getSpeedLimit() {
        return speedLimit;
    }

    public void setSpeedLimit(SpeedLimit speedLimit) {
        this.speedLimit = speedLimit;
    }

    public Weather getWeather() {
        return weather;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }

    public TimeOfDay getTimeOfDay() {
        return timeOfDay;
    }

    public void setTimeOfDay(TimeOfDay timeOfDay) {
        this.timeOfDay = timeOfDay;
    }
}
