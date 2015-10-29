package org.p4p.backpocketdriver.driverlog.interfaces;

/**
 * Created by Dheeraj on 1/07/15.
 */
public interface GetUserCallback {

    /**
     * Invoked when background task is completed
     */

    public abstract void done(Boolean returnedResult);
}
