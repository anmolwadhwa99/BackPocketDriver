package org.p4p.backpocketdriver.driverlog.pojo.summary.sections;

import org.p4p.backpocketdriver.driverlog.pojo.summary.categories.RoadType;
import org.p4p.backpocketdriver.driverlog.pojo.summary.categories.SpeedLimit;

/**
 * Created by user on 23/07/2015.
 */
public class AverageSpeed {
    private RoadType roadType;
    private SpeedLimit speedLimit;

    public AverageSpeed(){

    }

    public AverageSpeed(RoadType roadType, SpeedLimit speedLimit) {
        this.roadType = roadType;
        this.speedLimit = speedLimit;
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
}
