package org.p4p.backpocketdriver.driverlog.interfaces;

import org.p4p.backpocketdriver.driverlog.models.Journey;

import java.util.List;

/**
 * Created by Dheeraj on 9/07/15.
 */
public interface GetAllJourneysCallback {

    public abstract void done(List<Journey> returnedResult);
}
