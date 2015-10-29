package org.p4p.backpocketdriver.driverlog.interfaces;

import org.p4p.backpocketdriver.driverlog.models.Journey;

/**
 * Created by Dheeraj on 6/07/15.
 */
public interface GetJourneyCallback {

    public abstract void done(Journey returnedResult);


}
