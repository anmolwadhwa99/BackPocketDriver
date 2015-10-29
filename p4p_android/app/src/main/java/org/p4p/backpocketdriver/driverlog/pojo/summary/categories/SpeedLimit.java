package org.p4p.backpocketdriver.driverlog.pojo.summary.categories;

/**
 * Created by Anmol on 23/07/2015.
 */
public class SpeedLimit {
    private int Twenty;
    private int Fifty;
    private int Sixty;
    private int Eighty;
    private int Hundred;
    private int Miscellaneous;

    public SpeedLimit(){

    }
    public SpeedLimit(int Twenty, int Fifty, int Sixty, int Eighty, int Hundred, int Miscellaneous) {
        this.Twenty = Twenty;
        this.Fifty = Fifty;
        this.Eighty = Eighty;
        this.Hundred = Hundred;
        this.Miscellaneous = Miscellaneous;
    }

    public int getTwenty() {
        return Twenty;
    }

    public void setTwenty(int twenty) {
        Twenty = twenty;
    }

    public int getFifty() {
        return Fifty;
    }

    public void setFifty(int fifty) {
        Fifty = fifty;
    }

    public int getEighty() {
        return Eighty;
    }

    public void setEighty(int eighty) {
        Eighty = eighty;
    }

    public int getSixty() {
        return Sixty;
    }

    public void setSixty(int sixty) {
        Sixty = sixty;
    }

    public int getHundred() {
        return Hundred;
    }

    public void setHundred(int hundred) {
        Hundred = hundred;
    }

    public int getMiscellaneous() {
        return Miscellaneous;
    }

    public void setMiscellaneous(int miscellaneous) {
        Miscellaneous = miscellaneous;
    }
}
