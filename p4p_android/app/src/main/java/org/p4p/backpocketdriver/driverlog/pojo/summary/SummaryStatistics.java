package org.p4p.backpocketdriver.driverlog.pojo.summary;

import org.p4p.backpocketdriver.driverlog.pojo.summary.sections.AverageSpeed;
import org.p4p.backpocketdriver.driverlog.pojo.summary.sections.Distance;
import org.p4p.backpocketdriver.driverlog.pojo.summary.sections.Duration;

/**
 * Created by user on 23/07/2015.
 */
public class SummaryStatistics {
    private AverageSpeed averageSpeed;
    private Duration duration;
    private Distance distance;

    public SummaryStatistics(AverageSpeed averageSpeed, Duration duration, Distance distance) {
        this.averageSpeed = averageSpeed;
        this.duration = duration;
        this.distance = distance;
    }

    public SummaryStatistics() {
    }

    public AverageSpeed getAverageSpeed() {
        return averageSpeed;
    }

    public void setAverageSpeed(AverageSpeed averageSpeed) {
        this.averageSpeed = averageSpeed;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public Distance getDistance() {
        return distance;
    }

    public void setDistance(Distance distance) {
        this.distance = distance;
    }
}
