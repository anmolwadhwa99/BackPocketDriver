package org.p4p.backpocketdriver.driverlog.interfaces;

import org.p4p.backpocketdriver.driverlog.models.JourneyStatistics;

import java.util.List;

/**
 * Created by Dheeraj on 9/07/15.
 */
public interface GetAllStatsCallback {
    public abstract void done(List<JourneyStatistics> returnedResult);
}
