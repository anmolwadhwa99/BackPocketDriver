package org.p4p.backpocketdriver.driverlog.pojo;

/**
 * Created by Anmol on
 * 18/07/2015.
 */
public class Conversion {


    public String convertSeconds(int totalSecs){
        int hours = totalSecs / 3600;
        int minutes = (totalSecs % 3600) / 60;
        int seconds = totalSecs % 60;

        String timeString = String.format("%02d:%02d:%02d", hours, minutes, seconds);

        return timeString;
    }

    public String convertMetres(int metres){
        double km = metres/1000.0;
        double newKm = Math.round(km *10.0)/10.0;
        return String.valueOf(newKm);
    }

    public float convertIntToFloat(int value){
        float returnValue = (float) value/1000;
        return returnValue;
    }

    public float convertSecondsToMins(int value){
        float returnValue =  value/360;

        return returnValue;
    }

    public static String toDisplayCase(String s) {

        final String ACTIONABLE_DELIMITERS = " '-/"; // these cause the character following
        // to be capitalized

        StringBuilder sb = new StringBuilder();
        boolean capNext = true;

        for (char c : s.toCharArray()) {
            c = (capNext)
                    ? Character.toUpperCase(c)
                    : Character.toLowerCase(c);
            sb.append(c);
            capNext = (ACTIONABLE_DELIMITERS.indexOf((int) c) >= 0); // explicit cast not needed
        }
        return sb.toString();
    }




}
