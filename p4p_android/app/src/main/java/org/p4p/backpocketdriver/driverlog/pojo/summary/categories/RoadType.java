package org.p4p.backpocketdriver.driverlog.pojo.summary.categories;


/**
 * Created by Anmol on 23/07/2015.
 */
public class RoadType{
    private int urban;
    private int rural;
    private int motorway;

    public RoadType(){

    }

    public RoadType(int urban, int rural, int motorway) {
        this.urban = urban;
        this.rural = rural;
        this.motorway = motorway;
    }

    public int getUrban() {
        return urban;
    }

    public void setUrban(int urban) {
        this.urban = urban;
    }

    public int getRural() {
        return rural;
    }

    public void setRural(int rural) {
        this.rural = rural;
    }

    public int getMotorway() {
        return motorway;
    }

    public void setMotorway(int motorway) {
        this.motorway = motorway;
    }
}