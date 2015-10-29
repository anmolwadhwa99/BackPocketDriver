package org.p4p.backpocketdriver.driverlog.pojo.summary.categories;

/**
 * Created by Anmol on 23/07/2015.
 */
public class Weather {
    private int fine;
    private int rain;
    private int drizzle;
    private int fog;
    private int other;

    public Weather(int fine, int rain, int drizzle, int fog, int other) {
        this.fine = fine;
        this.rain = rain;
        this.drizzle = drizzle;
        this.fog = fog;
        this.other = other;
    }

    public Weather(){

    }

    public int getFine() {
        return fine;
    }

    public void setFine(int fine) {
        this.fine = fine;
    }

    public int getRain() {
        return rain;
    }

    public void setRain(int rain) {
        this.rain = rain;
    }

    public int getDrizzle() {
        return drizzle;
    }

    public void setDrizzle(int drizzle) {
        this.drizzle = drizzle;
    }

    public int getFog() {
        return fog;
    }

    public void setFog(int fog) {
        this.fog = fog;
    }

    public int getOther() {
        return other;
    }

    public void setOther(int other) {
        this.other = other;
    }
}
