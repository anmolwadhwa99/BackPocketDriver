package org.p4p.backpocketdriver.driverlog.interfaces;

import org.p4p.backpocketdriver.driverlog.models.JourneyStatistics;

/**
 * Created by Dheeraj on 6/07/15.
 */
public interface GetStatsCallback {

    public abstract void done(JourneyStatistics returnedResult);


}
