package org.p4p.backpocketdriver.driverlog.pojo.summary.categories;

/**
 * Created by Anmol on 23/07/2015.
 */
public class TimeOfDay {
    private int day;
    private int night;

    public TimeOfDay(){

    }
    public TimeOfDay(int day, int night) {
        this.day = day;
        this.night = night;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getNight() {
        return night;
    }

    public void setNight(int night) {
        this.night = night;
    }
}
