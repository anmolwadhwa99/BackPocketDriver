package org.p4p.backpocketdriver.driverlog.server;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.p4p.backpocketdriver.driverlog.interfaces.GetAllJourneysCallback;
import org.p4p.backpocketdriver.driverlog.interfaces.GetAllStatsCallback;
import org.p4p.backpocketdriver.driverlog.interfaces.GetJourneyCallback;
import org.p4p.backpocketdriver.driverlog.interfaces.GetStatsCallback;
import org.p4p.backpocketdriver.driverlog.models.Journey;
import org.p4p.backpocketdriver.driverlog.models.JourneyStatistics;
import org.p4p.backpocketdriver.driverlog.models.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dheeraj on 4/07/15.
 */
public class JourneyRequests extends Server {

    private static String JOURNEY_STATS_ID = "statisticId";
    // ** DURATION **
    private static String DURATION_TWENTY_TOTAL = "timeDriven_Twenty_total";
    private static String DURATION_FIFTY_TOTAL = "timeDriven_Fifty_total";
    private static String DURATION_SIXTY_TOTAL = "timeDriven_Sixty_total";
    private static String DURATION_EIGHTY_TOTAL = "timeDriven_Eighty_total";
    private static String DURATION_HUNDRED_TOTAL = "timeDriven_Hundred_total";
    private static String DURATION_MISCELLANEOUS_TOTAL = "timeDriven_Miscellaneous_total";

    private static String DURATION_RURAL_TOTAL = "timeDriven_rural_total";
    private static String DURATION_URBAN_TOTAL = "timeDriven_urban_total";
    private static String DURATION_MOTORWAY_TOTAL = "timeDriven_motorway_total";

    private static String DURATION_DAY_TOTAL = "timeDriven_day_total";
    private static String DURATION_NIGHT_TOTAL = "timeDriven_night_total";

    private static String DURATION_TWENTY_DAY =  "timeDriven_Twenty_day";
    private static String DURATION_TWENTY_NIGHT = "timeDriven_Twenty_night";
    private static String DURATION_FIFTY_DAY = "timeDriven_Fifty_day";
    private static String DURATION_FIFTY_NIGHT = "timeDriven_Fifty_night";
    private static String DURATION_SIXTY_DAY = "timeDriven_Sixty_day";
    private static String DURATION_SIXTY_NIGHT = "timeDriven_Sixty_night";
    private static String DURATION_EIGHTY_DAY = "timeDriven_Eighty_day";
    private static String DURATION_EIGHTY_NIGHT = "timeDriven_Eighty_night";
    private static String DURATION_HUNDRED_DAY = "timeDriven_Hundred_day";
    private static String DURATION_HUNDRED_NIGHT = "timeDriven_Hundred_night";
    private static String DURATION_MISCELLANEOUS_DAY = "timeDriven_Miscellaneous_day";
    private static String DURATION_MISCELLANEOUS_NIGHT = "timeDriven_Miscellaneous_night";

    private static String DURATION_RURAL_DAY = "timeDriven_rural_day";
    private static String DURATION_RURAL_NIGHT = "timeDriven_rural_night";
    private static String DURATION_URBAN_DAY = "timeDriven_urban_day";
    private static String DURATION_URBAN_NIGHT = "timeDriven_urban_night";
    private static String DURATION_MOTORWAY_DAY = "timeDriven_motorway_day";
    private static String DURATION_MOTORWAY_NIGHT = "timeDriven_motorway_night";

    // ** DISTANCE **
    private static String DISTANCE_TWENTY_TOTAL = "distanceTravelled_Twenty_total";
    private static String DISTANCE_FIFTY_TOTAL = "distanceTravelled_Fifty_total";
    private static String DISTANCE_SIXTY_TOTAL = "distanceTravelled_Sixty_total";
    private static String DISTANCE_EIGHTY_TOTAL = "distanceTravelled_Eighty_total";
    private static String DISTANCE_HUNDRED_TOTAL = "distanceTravelled_Hundred_total";
    private static String DISTANCE_MISCELLANEOUS_TOTAL = "distanceTravelled_Miscellaneous_total";

    private static String DISTANCE_RURAL_TOTAL = "distanceTravelled_rural_total";
    private static String DISTANCE_URBAN_TOTAL = "distanceTravelled_urban_total";
    private static String DISTANCE_MOTORWAY_TOTAL = "distanceTravelled_motorway_total";

    private static String DISTANCE_DAY_TOTAL = "distanceTravelled_day_total";
    private static String DISTANCE_NIGHT_TOTAL = "distanceTravelled_night_total";

    private static String DISTANCE_TWENTY_DAY =  "distanceTravelled_Twenty_day";
    private static String DISTANCE_TWENTY_NIGHT = "distanceTravelled_Twenty_night";
    private static String DISTANCE_FIFTY_DAY = "distanceTravelled_Fifty_day";
    private static String DISTANCE_FIFTY_NIGHT = "distanceTravelled_Fifty_night";
    private static String DISTANCE_SIXTY_DAY = "distanceTravelled_Sixty_day";
    private static String DISTANCE_SIXTY_NIGHT = "distanceTravelled_Sixty_night";
    private static String DISTANCE_EIGHTY_DAY = "distanceTravelled_Eighty_day";
    private static String DISTANCE_EIGHTY_NIGHT = "distanceTravelled_Eighty_night";
    private static String DISTANCE_HUNDRED_DAY = "distanceTravelled_Hundred_day";
    private static String DISTANCE_HUNDRED_NIGHT = "distanceTravelled_Hundred_night";
    private static String DISTANCE_MISCELLANEOUS_DAY = "distanceTravelled_Miscellaneous_day";
    private static String DISTANCE_MISCELLANEOUS_NIGHT = "distanceTravelled_Miscellaneous_night";

    private static String DISTANCE_RURAL_DAY = "distanceTravelled_rural_day";
    private static String DISTANCE_RURAL_NIGHT = "distanceTravelled_rural_night";
    private static String DISTANCE_URBAN_DAY = "distanceTravelled_urban_day";
    private static String DISTANCE_URBAN_NIGHT = "distanceTravelled_urban_night";
    private static String DISTANCE_MOTORWAY_DAY = "distanceTravelled_motorway_day";
    private static String DISTANCE_MOTORWAY_NIGHT = "distanceTravelled_motorway_night";

    // ** AVERAGE SPEED **
    private static String SPEED_TWENTY = "averageSpeed_Twenty";
    private static String SPEED_FIFTY = "averageSpeed_Fifty";
    private static String SPEED_SIXTY = "averageSpeed_Sixty";
    private static String SPEED_EIGHTY = "averageSpeed_Eighty";
    private static String SPEED_HUNDRED = "averageSpeed_Hundred";
    private static String SPEED_MISCELLANEOUS = "averageSpeed_Miscellaneous";

    private static String SPEED_RURAL = "averageSpeed_rural";
    private static String SPEED_URBAN = "averageSpeed_urban";
    private static String SPEED_MOTORWAY = "averageSpeed_motorway";

    private static String JOURNEY_ID = "journeyId";
    private static String JOURNEY_DATE = "date";
    private static String JOURNEY_DISTANCE = "distanceTravelled";
    private static String JOURNEY_DURATION = "duration";
    private static String JOURNEY_NAME = "journeyName";
    private static String JOURNEY_COORDINATES = "coordinates";
    private static String JOURNEY_WEATHER = "weather";






    private ProgressDialog progressDialog;

    public JourneyRequests() {
    }

    public JourneyRequests(Context context, String message) {
        progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(false);
        progressDialog.setTitle(message + "...");
        progressDialog.setMessage("Please wait...");
    }

    public void getJourneyDataInBackground(Journey journey, GetJourneyCallback journeyCallback) {
        progressDialog.show();
        new GetJourneyDataAsyncTask(journey, journeyCallback).execute();

    }

    public void getAllUserJourneyDataInBackground(User user, GetAllJourneysCallback journeyCallback) {
        progressDialog.show();
        new GetAllUserJourneysDataAsyncTask(user, journeyCallback).execute();

    }

    public void getStatsDataInBackground(Journey journey, GetStatsCallback statsCallback) {
        progressDialog.show();
        new GetStatsDataAsyncTask(journey, statsCallback).execute();

    }

    public void getAllUserStatsDataInBackground(User user, GetAllStatsCallback statsCallback) {
        progressDialog.show();
        new GetAllUserStatsDataAsyncTask(user, statsCallback).execute();

    }


    public Journey getJourney(int journeyId){
        String journeyRequest =  HTTPGetMethod(SERVER_ADDRESS + JOURNEY_URL + "journeys/" + journeyId);
        Journey journey = new Journey();
        try{
            JSONObject jsonJourney = new JSONObject(journeyRequest);
            String date = jsonJourney.getString(JOURNEY_NAME);
            int duration = Integer.parseInt(jsonJourney.getString(JOURNEY_DURATION));
            int distanceTravelled = Integer.parseInt(jsonJourney.getString(JOURNEY_DISTANCE));
            int journeyID = Integer.parseInt(jsonJourney.getString(JOURNEY_ID));
            String weather = jsonJourney.getString(JOURNEY_WEATHER);
            String journeyName = jsonJourney.getString(JOURNEY_NAME);
            String coordinates = jsonJourney.getString(JOURNEY_COORDINATES);


            journey.setDistanceTravelled(distanceTravelled);
            journey.setDuration(duration);
            journey.setDate(date);
            journey.setJourneyId(journeyID);
            journey.setWeather(weather);
            journey.setName(journeyName);
            journey.setCoordinates(coordinates);


        }catch(JSONException e){

        }
        return journey;
    }

    public List<Journey> getAllUserJourneys(String userName){
        String allJourneys =  HTTPGetMethod(SERVER_ADDRESS + JOURNEY_URL + userName);
        List<Journey> returnedJourneys = new ArrayList<Journey>();

        try{
            JSONArray array = new JSONArray(allJourneys);
            for(int i=0; i<array.length();i++) {
                JSONObject newJourney = array.getJSONObject(i);
                String date = newJourney.getString(JOURNEY_DATE);
                int journeyId = Integer.parseInt(newJourney.getString(JOURNEY_ID));
                int distanceTravelled = Integer.parseInt(newJourney.getString(JOURNEY_DISTANCE));
                int duration = Integer.parseInt(newJourney.getString(JOURNEY_DURATION));
                String weather = newJourney.getString(JOURNEY_WEATHER);
                String journeyName = newJourney.getString(JOURNEY_NAME);
                String coordinates = newJourney.getString(JOURNEY_COORDINATES);

                Journey journey = new Journey();
                journey.setDistanceTravelled(distanceTravelled);
                journey.setDuration(duration);
                journey.setDate(date);
                journey.setJourneyId(journeyId);
                journey.setWeather(weather);
                journey.setName(journeyName);
                journey.setCoordinates(coordinates);

                JourneyStatistics journeyStatistics = getStatsForJourney(journeyId);
                journey.setJourneyStatistics(journeyStatistics);

                returnedJourneys.add(journey);


            }

        }catch (JSONException e){
            e.printStackTrace();
        }
        return returnedJourneys;
    }

    public JourneyStatistics getStatsForJourney(int journeyId){
        String statsRequest = HTTPGetMethod(SERVER_ADDRESS + JOURNEY_URL + "statistics/" + journeyId);
        JourneyStatistics journeyStatistics = new JourneyStatistics();
        try{
            JSONObject jsonStats = new JSONObject(statsRequest);

            int journeyStatisticsId = Integer.parseInt(jsonStats.getString(JOURNEY_STATS_ID));
            String weather = jsonStats.getString(JOURNEY_WEATHER);


            int timeDriven_Twenty_total = Integer.parseInt(jsonStats.getString(DURATION_TWENTY_TOTAL));
            int timeDriven_Fifty_total = Integer.parseInt(jsonStats.getString(DURATION_FIFTY_TOTAL));
            int timeDriven_Sixty_total = Integer.parseInt(jsonStats.getString(DURATION_SIXTY_TOTAL));
            int timeDriven_Eighty_total= Integer.parseInt(jsonStats.getString(DURATION_EIGHTY_TOTAL));
            int timeDriven_Hundred_total= Integer.parseInt(jsonStats.getString(DURATION_HUNDRED_TOTAL));
            int timeDriven_Miscellaneous_total= Integer.parseInt(jsonStats.getString(DURATION_MISCELLANEOUS_TOTAL));
            //Road Types
            int timeDriven_rural_total = Integer.parseInt(jsonStats.getString(DURATION_RURAL_TOTAL));
            int timeDriven_urban_total = Integer.parseInt(jsonStats.getString(DURATION_URBAN_TOTAL));
            int timeDriven_motorway_total = Integer.parseInt(jsonStats.getString(DURATION_MOTORWAY_TOTAL));
            //Time of Day
            int timeDriven_day_total = Integer.parseInt(jsonStats.getString(DURATION_DAY_TOTAL));
            int timeDriven_night_total= Integer.parseInt(jsonStats.getString(DURATION_NIGHT_TOTAL));
            //Speed Zones and Time of Day
            int timeDriven_Twenty_day= Integer.parseInt(jsonStats.getString(DURATION_TWENTY_DAY));
            int timeDriven_Twenty_night = Integer.parseInt(jsonStats.getString(DURATION_TWENTY_NIGHT));
            int timeDriven_Fifty_day = Integer.parseInt(jsonStats.getString(DURATION_FIFTY_DAY));
            int timeDriven_Fifty_night = Integer.parseInt(jsonStats.getString(DURATION_FIFTY_NIGHT));
            int timeDriven_Sixty_day = Integer.parseInt(jsonStats.getString(DURATION_SIXTY_DAY));
            int timeDriven_Sixty_night = Integer.parseInt(jsonStats.getString(DURATION_SIXTY_NIGHT));
            int timeDriven_Eighty_day = Integer.parseInt(jsonStats.getString(DURATION_EIGHTY_DAY));
            int timeDriven_Eighty_night= Integer.parseInt(jsonStats.getString(DURATION_EIGHTY_DAY));
            int timeDriven_Hundred_day = Integer.parseInt(jsonStats.getString(DURATION_HUNDRED_DAY));
            int timeDriven_Hundred_night = Integer.parseInt(jsonStats.getString(DURATION_HUNDRED_NIGHT));
            int timeDriven_Miscellaneous_day = Integer.parseInt(jsonStats.getString(DURATION_MISCELLANEOUS_DAY));
            int timeDriven_Miscellaneous_night = Integer.parseInt(jsonStats.getString(DURATION_MISCELLANEOUS_NIGHT));
            //Road Types and Time of Day
            int timeDriven_rural_day = Integer.parseInt(jsonStats.getString(DURATION_RURAL_DAY));
            int timeDriven_rural_night= Integer.parseInt(jsonStats.getString(DURATION_RURAL_NIGHT));
            int timeDriven_urban_day = Integer.parseInt(jsonStats.getString(DURATION_URBAN_DAY));
            int timeDriven_urban_night = Integer.parseInt(jsonStats.getString(DURATION_URBAN_NIGHT));
            int timeDriven_motorway_day = Integer.parseInt(jsonStats.getString(DURATION_MOTORWAY_DAY));
            int timeDriven_motorway_night = Integer.parseInt(jsonStats.getString(DURATION_MOTORWAY_NIGHT));


            //**DISTANCE**
            int distanceTravelled_Twenty_total = Integer.parseInt(jsonStats.getString(DISTANCE_TWENTY_TOTAL));
            int distanceTravelled_Fifty_total = Integer.parseInt(jsonStats.getString(DISTANCE_FIFTY_TOTAL));
            int distanceTravelled_Sixty_total = Integer.parseInt(jsonStats.getString(DISTANCE_SIXTY_TOTAL));
            int distanceTravelled_Eighty_total= Integer.parseInt(jsonStats.getString(DISTANCE_EIGHTY_TOTAL));
            int distanceTravelled_Hundred_total= Integer.parseInt(jsonStats.getString(DISTANCE_HUNDRED_TOTAL));
            int distanceTravelled_Miscellaneous_total= Integer.parseInt(jsonStats.getString(DISTANCE_MISCELLANEOUS_TOTAL));
            //Road Types
            int distanceTravelled_rural_total = Integer.parseInt(jsonStats.getString(DISTANCE_RURAL_TOTAL));
            int distanceTravelled_urban_total = Integer.parseInt(jsonStats.getString(DISTANCE_URBAN_TOTAL));
            int distanceTravelled_motorway_total = Integer.parseInt(jsonStats.getString(DISTANCE_MOTORWAY_TOTAL));
            //Time of Day
            int distanceTravelled_day_total = Integer.parseInt(jsonStats.getString(DISTANCE_DAY_TOTAL));
            int distanceTravelled_night_total= Integer.parseInt(jsonStats.getString(DISTANCE_NIGHT_TOTAL));
            //Speed Zones and Time of Day
            int distanceTravelled_Twenty_day= Integer.parseInt(jsonStats.getString(DISTANCE_TWENTY_DAY));
            int distanceTravelled_Twenty_night = Integer.parseInt(jsonStats.getString(DISTANCE_TWENTY_NIGHT));
            int distanceTravelled_Fifty_day = Integer.parseInt(jsonStats.getString(DISTANCE_FIFTY_DAY));
            int distanceTravelled_Fifty_night = Integer.parseInt(jsonStats.getString(DISTANCE_FIFTY_NIGHT));
            int distanceTravelled_Sixty_day = Integer.parseInt(jsonStats.getString(DISTANCE_SIXTY_DAY));
            int distanceTravelled_Sixty_night = Integer.parseInt(jsonStats.getString(DISTANCE_SIXTY_NIGHT));
            int distanceTravelled_Eighty_day = Integer.parseInt(jsonStats.getString(DISTANCE_EIGHTY_DAY));
            int distanceTravelled_Eighty_night= Integer.parseInt(jsonStats.getString(DISTANCE_EIGHTY_NIGHT));
            int distanceTravelled_Hundred_day = Integer.parseInt(jsonStats.getString(DISTANCE_HUNDRED_DAY));
            int distanceTravelled_Hundred_night = Integer.parseInt(jsonStats.getString(DISTANCE_HUNDRED_NIGHT));
            int distanceTravelled_Miscellaneous_day = Integer.parseInt(jsonStats.getString(DISTANCE_MISCELLANEOUS_DAY));
            int distanceTravelled_Miscellaneous_night = Integer.parseInt(jsonStats.getString(DISTANCE_MISCELLANEOUS_NIGHT));
            //Road Types and Time of Day
            int distanceTravelled_rural_day = Integer.parseInt(jsonStats.getString(DISTANCE_RURAL_DAY));
            int distanceTravelled_rural_night= Integer.parseInt(jsonStats.getString(DISTANCE_RURAL_NIGHT));
            int distanceTravelled_urban_day = Integer.parseInt(jsonStats.getString(DISTANCE_URBAN_DAY));
            int distanceTravelled_urban_night = Integer.parseInt(jsonStats.getString(DISTANCE_URBAN_NIGHT));
            int distanceTravelled_motorway_day = Integer.parseInt(jsonStats.getString(DISTANCE_MOTORWAY_DAY));
            int distanceTravelled_motorway_night = Integer.parseInt(jsonStats.getString(DISTANCE_MOTORWAY_NIGHT));



            //**AVERAGE SPEED**
            //Speed Zones
            int averageSpeed_Twenty = Integer.parseInt(jsonStats.getString(SPEED_TWENTY));
            int averageSpeed_Fifty = Integer.parseInt(jsonStats.getString(SPEED_FIFTY));
            int averageSpeed_Sixty = Integer.parseInt(jsonStats.getString(SPEED_SIXTY));
            int averageSpeed_Eighty = Integer.parseInt(jsonStats.getString(SPEED_EIGHTY));
            int averageSpeed_Hundred = Integer.parseInt(jsonStats.getString(SPEED_HUNDRED));
            int averageSpeed_Miscellaneous = Integer.parseInt(jsonStats.getString(SPEED_MISCELLANEOUS));
            //Road Types
            int averageSpeed_rural = Integer.parseInt(jsonStats.getString(SPEED_RURAL));
            int averageSpeed_urban = Integer.parseInt(jsonStats.getString(SPEED_URBAN));
            int averageSpeed_motorway = Integer.parseInt(jsonStats.getString(SPEED_MOTORWAY));

            //Each Journey will be associated with one particular weather condition
//            String weather = jsonStats.getString("weather");

            journeyStatistics.setAverageSpeed_Miscellaneous(averageSpeed_Miscellaneous);
            journeyStatistics.setAverageSpeed_Hundred(averageSpeed_Hundred);
            journeyStatistics.setAverageSpeed_Eighty(averageSpeed_Eighty);
            journeyStatistics.setAverageSpeed_Sixty(averageSpeed_Sixty);
            journeyStatistics.setAverageSpeed_Fifty(averageSpeed_Fifty);
            journeyStatistics.setAverageSpeed_Twenty(averageSpeed_Twenty);
            journeyStatistics.setAverageSpeed_rural(averageSpeed_rural);
            journeyStatistics.setAverageSpeed_urban(averageSpeed_urban);
            journeyStatistics.setAverageSpeed_motorway(averageSpeed_motorway);

            journeyStatistics.setDistanceTravelled_day_total(distanceTravelled_day_total);
            journeyStatistics.setDistanceTravelled_night_total(distanceTravelled_night_total);
            journeyStatistics.setDistanceTravelled_Miscellaneous_day(distanceTravelled_Miscellaneous_day);
            journeyStatistics.setDistanceTravelled_Miscellaneous_night(distanceTravelled_Miscellaneous_night);
            journeyStatistics.setDistanceTravelled_Miscellaneous_total(distanceTravelled_Miscellaneous_total);
            journeyStatistics.setDistanceTravelled_Hundred_day(distanceTravelled_Hundred_day);
            journeyStatistics.setDistanceTravelled_Hundred_night(distanceTravelled_Hundred_night);
            journeyStatistics.setDistanceTravelled_Hundred_total(distanceTravelled_Hundred_total);
            journeyStatistics.setDistanceTravelled_Eighty_day(distanceTravelled_Eighty_day);
            journeyStatistics.setDistanceTravelled_Eighty_night(distanceTravelled_Eighty_night);
            journeyStatistics.setDistanceTravelled_Eighty_total(distanceTravelled_Eighty_total);
            journeyStatistics.setDistanceTravelled_Sixty_day(distanceTravelled_Sixty_day);
            journeyStatistics.setDistanceTravelled_Sixty_night(distanceTravelled_Sixty_night);
            journeyStatistics.setDistanceTravelled_Sixty_total(distanceTravelled_Sixty_total);
            journeyStatistics.setDistanceTravelled_Fifty_day(distanceTravelled_Fifty_day);
            journeyStatistics.setDistanceTravelled_Fifty_night(distanceTravelled_Fifty_night);
            journeyStatistics.setDistanceTravelled_Fifty_total(distanceTravelled_Fifty_total);
            journeyStatistics.setDistanceTravelled_Twenty_day(distanceTravelled_Twenty_day);
            journeyStatistics.setDistanceTravelled_Twenty_night(distanceTravelled_Twenty_night);
            journeyStatistics.setDistanceTravelled_Twenty_total(distanceTravelled_Twenty_total);
            journeyStatistics.setDistanceTravelled_rural_day(distanceTravelled_rural_day);
            journeyStatistics.setDistanceTravelled_rural_night(distanceTravelled_rural_night);
            journeyStatistics.setDistanceTravelled_rural_total(distanceTravelled_rural_total);
            journeyStatistics.setDistanceTravelled_urban_day(distanceTravelled_urban_day);
            journeyStatistics.setDistanceTravelled_urban_night(distanceTravelled_urban_night);
            journeyStatistics.setDistanceTravelled_urban_total(distanceTravelled_urban_total);
            journeyStatistics.setDistanceTravelled_motorway_day(distanceTravelled_motorway_day);
            journeyStatistics.setDistanceTravelled_motorway_night(distanceTravelled_motorway_night);
            journeyStatistics.setDistanceTravelled_motorway_total(distanceTravelled_motorway_total);

            journeyStatistics.setTimeDriven_day_total(timeDriven_day_total);
            journeyStatistics.setTimeDriven_night_total(timeDriven_night_total);
            journeyStatistics.setTimeDriven_Miscellaneous_day(timeDriven_Miscellaneous_day);
            journeyStatistics.setTimeDriven_Miscellaneous_night(timeDriven_Miscellaneous_night);
            journeyStatistics.setTimeDriven_Miscellaneous_total(timeDriven_Miscellaneous_total);
            journeyStatistics.setTimeDriven_Hundred_day(timeDriven_Hundred_day);
            journeyStatistics.setTimeDriven_Hundred_night(timeDriven_Hundred_night);
            journeyStatistics.setTimeDriven_Hundred_total(timeDriven_Hundred_total);
            journeyStatistics.setTimeDriven_Eighty_day(timeDriven_Eighty_day);
            journeyStatistics.setTimeDriven_Eighty_night(timeDriven_Eighty_night);
            journeyStatistics.setTimeDriven_Eighty_total(timeDriven_Eighty_total);
            journeyStatistics.setTimeDriven_Sixty_day(timeDriven_Sixty_day);
            journeyStatistics.setTimeDriven_Sixty_night(timeDriven_Sixty_night);
            journeyStatistics.setTimeDriven_Sixty_total(timeDriven_Sixty_total);
            journeyStatistics.setTimeDriven_Fifty_day(timeDriven_Fifty_day);
            journeyStatistics.setTimeDriven_Fifty_night(timeDriven_Fifty_night);
            journeyStatistics.setTimeDriven_Fifty_total(timeDriven_Fifty_total);
            journeyStatistics.setTimeDriven_Twenty_day(timeDriven_Twenty_day);
            journeyStatistics.setTimeDriven_Twenty_night(timeDriven_Twenty_night);
            journeyStatistics.setTimeDriven_Twenty_total(timeDriven_Twenty_total);
            journeyStatistics.setTimeDriven_rural_day(timeDriven_rural_day);
            journeyStatistics.setTimeDriven_rural_night(timeDriven_rural_night);
            journeyStatistics.setTimeDriven_rural_total(timeDriven_rural_total);
            journeyStatistics.setTimeDriven_urban_day(timeDriven_urban_day);
            journeyStatistics.setTimeDriven_urban_night(timeDriven_urban_night);
            journeyStatistics.setTimeDriven_urban_total(timeDriven_urban_total);
            journeyStatistics.setTimeDriven_motorway_day(timeDriven_motorway_day);
            journeyStatistics.setTimeDriven_motorway_night(timeDriven_motorway_night);
            journeyStatistics.setTimeDriven_motorway_total(timeDriven_motorway_total);

            journeyStatistics.setJourneyStatisticsId(journeyStatisticsId);
            journeyStatistics.setWeather(weather);


        }catch(JSONException e){

        }
        return journeyStatistics;
    }

    public List<JourneyStatistics> getTotalJourneyStats(String userName) {

        List<JourneyStatistics> journeyStatisticsList = new ArrayList<JourneyStatistics>();
        try{
            String allStats = HTTPGetMethod(SERVER_ADDRESS + JOURNEY_URL + "totalStatistics/" + userName);
            JSONArray array = new JSONArray(allStats);


            for (int i =0; i<array.length(); i++) {
                JourneyStatistics journeyStatistics = new JourneyStatistics();

                JSONObject jsonStats = array.getJSONObject(i);

                int journeyStatisticsId = Integer.parseInt(jsonStats.getString(JOURNEY_STATS_ID));
                String weather = jsonStats.getString(JOURNEY_WEATHER);

                int timeDriven_Twenty_total = Integer.parseInt(jsonStats.getString(DURATION_TWENTY_TOTAL));
                int timeDriven_Fifty_total = Integer.parseInt(jsonStats.getString(DURATION_FIFTY_TOTAL));
                int timeDriven_Sixty_total = Integer.parseInt(jsonStats.getString(DURATION_SIXTY_TOTAL));
                int timeDriven_Eighty_total= Integer.parseInt(jsonStats.getString(DURATION_EIGHTY_TOTAL));
                int timeDriven_Hundred_total= Integer.parseInt(jsonStats.getString(DURATION_HUNDRED_TOTAL));
                int timeDriven_Miscellaneous_total= Integer.parseInt(jsonStats.getString(DURATION_MISCELLANEOUS_TOTAL));
                //Road Types
                int timeDriven_rural_total = Integer.parseInt(jsonStats.getString(DURATION_RURAL_TOTAL));
                int timeDriven_urban_total = Integer.parseInt(jsonStats.getString(DURATION_URBAN_TOTAL));
                int timeDriven_motorway_total = Integer.parseInt(jsonStats.getString(DURATION_MOTORWAY_TOTAL));
                //Time of Day
                int timeDriven_day_total = Integer.parseInt(jsonStats.getString(DURATION_DAY_TOTAL));
                int timeDriven_night_total= Integer.parseInt(jsonStats.getString(DURATION_NIGHT_TOTAL));
                //Speed Zones and Time of Day
                int timeDriven_Twenty_day= Integer.parseInt(jsonStats.getString(DURATION_TWENTY_DAY));
                int timeDriven_Twenty_night = Integer.parseInt(jsonStats.getString(DURATION_TWENTY_NIGHT));
                int timeDriven_Fifty_day = Integer.parseInt(jsonStats.getString(DURATION_FIFTY_DAY));
                int timeDriven_Fifty_night = Integer.parseInt(jsonStats.getString(DURATION_FIFTY_NIGHT));
                int timeDriven_Sixty_day = Integer.parseInt(jsonStats.getString(DURATION_SIXTY_DAY));
                int timeDriven_Sixty_night = Integer.parseInt(jsonStats.getString(DURATION_SIXTY_NIGHT));
                int timeDriven_Eighty_day = Integer.parseInt(jsonStats.getString(DURATION_EIGHTY_DAY));
                int timeDriven_Eighty_night= Integer.parseInt(jsonStats.getString(DURATION_EIGHTY_DAY));
                int timeDriven_Hundred_day = Integer.parseInt(jsonStats.getString(DURATION_HUNDRED_DAY));
                int timeDriven_Hundred_night = Integer.parseInt(jsonStats.getString(DURATION_HUNDRED_NIGHT));
                int timeDriven_Miscellaneous_day = Integer.parseInt(jsonStats.getString(DURATION_MISCELLANEOUS_DAY));
                int timeDriven_Miscellaneous_night = Integer.parseInt(jsonStats.getString(DURATION_MISCELLANEOUS_NIGHT));
                //Road Types and Time of Day
                int timeDriven_rural_day = Integer.parseInt(jsonStats.getString(DURATION_RURAL_DAY));
                int timeDriven_rural_night= Integer.parseInt(jsonStats.getString(DURATION_RURAL_NIGHT));
                int timeDriven_urban_day = Integer.parseInt(jsonStats.getString(DURATION_URBAN_DAY));
                int timeDriven_urban_night = Integer.parseInt(jsonStats.getString(DURATION_URBAN_NIGHT));
                int timeDriven_motorway_day = Integer.parseInt(jsonStats.getString(DURATION_MOTORWAY_DAY));
                int timeDriven_motorway_night = Integer.parseInt(jsonStats.getString(DURATION_MOTORWAY_NIGHT));


                //**DISTANCE**
                int distanceTravelled_Twenty_total = Integer.parseInt(jsonStats.getString(DISTANCE_TWENTY_TOTAL));
                int distanceTravelled_Fifty_total = Integer.parseInt(jsonStats.getString(DISTANCE_FIFTY_TOTAL));
                int distanceTravelled_Sixty_total = Integer.parseInt(jsonStats.getString(DISTANCE_SIXTY_TOTAL));
                int distanceTravelled_Eighty_total= Integer.parseInt(jsonStats.getString(DISTANCE_EIGHTY_TOTAL));
                int distanceTravelled_Hundred_total= Integer.parseInt(jsonStats.getString(DISTANCE_HUNDRED_TOTAL));
                int distanceTravelled_Miscellaneous_total= Integer.parseInt(jsonStats.getString(DISTANCE_MISCELLANEOUS_TOTAL));
                //Road Types
                int distanceTravelled_rural_total = Integer.parseInt(jsonStats.getString(DISTANCE_RURAL_TOTAL));
                int distanceTravelled_urban_total = Integer.parseInt(jsonStats.getString(DISTANCE_URBAN_TOTAL));
                int distanceTravelled_motorway_total = Integer.parseInt(jsonStats.getString(DISTANCE_MOTORWAY_TOTAL));
                //Time of Day
                int distanceTravelled_day_total = Integer.parseInt(jsonStats.getString(DISTANCE_DAY_TOTAL));
                int distanceTravelled_night_total= Integer.parseInt(jsonStats.getString(DISTANCE_NIGHT_TOTAL));
                //Speed Zones and Time of Day
                int distanceTravelled_Twenty_day= Integer.parseInt(jsonStats.getString(DISTANCE_TWENTY_DAY));
                int distanceTravelled_Twenty_night = Integer.parseInt(jsonStats.getString(DISTANCE_TWENTY_NIGHT));
                int distanceTravelled_Fifty_day = Integer.parseInt(jsonStats.getString(DISTANCE_FIFTY_DAY));
                int distanceTravelled_Fifty_night = Integer.parseInt(jsonStats.getString(DISTANCE_FIFTY_NIGHT));
                int distanceTravelled_Sixty_day = Integer.parseInt(jsonStats.getString(DISTANCE_SIXTY_DAY));
                int distanceTravelled_Sixty_night = Integer.parseInt(jsonStats.getString(DISTANCE_SIXTY_NIGHT));
                int distanceTravelled_Eighty_day = Integer.parseInt(jsonStats.getString(DISTANCE_EIGHTY_DAY));
                int distanceTravelled_Eighty_night= Integer.parseInt(jsonStats.getString(DISTANCE_EIGHTY_NIGHT));
                int distanceTravelled_Hundred_day = Integer.parseInt(jsonStats.getString(DISTANCE_HUNDRED_DAY));
                int distanceTravelled_Hundred_night = Integer.parseInt(jsonStats.getString(DISTANCE_HUNDRED_NIGHT));
                int distanceTravelled_Miscellaneous_day = Integer.parseInt(jsonStats.getString(DISTANCE_MISCELLANEOUS_DAY));
                int distanceTravelled_Miscellaneous_night = Integer.parseInt(jsonStats.getString(DISTANCE_MISCELLANEOUS_NIGHT));
                //Road Types and Time of Day
                int distanceTravelled_rural_day = Integer.parseInt(jsonStats.getString(DISTANCE_RURAL_DAY));
                int distanceTravelled_rural_night= Integer.parseInt(jsonStats.getString(DISTANCE_RURAL_NIGHT));
                int distanceTravelled_urban_day = Integer.parseInt(jsonStats.getString(DISTANCE_URBAN_DAY));
                int distanceTravelled_urban_night = Integer.parseInt(jsonStats.getString(DISTANCE_URBAN_NIGHT));
                int distanceTravelled_motorway_day = Integer.parseInt(jsonStats.getString(DISTANCE_MOTORWAY_DAY));
                int distanceTravelled_motorway_night = Integer.parseInt(jsonStats.getString(DISTANCE_MOTORWAY_NIGHT));



                //**AVERAGE SPEED**
                //Speed Zones
                int averageSpeed_Twenty = Integer.parseInt(jsonStats.getString(SPEED_TWENTY));
                int averageSpeed_Fifty = Integer.parseInt(jsonStats.getString(SPEED_FIFTY));
                int averageSpeed_Sixty = Integer.parseInt(jsonStats.getString(SPEED_SIXTY));
                int averageSpeed_Eighty = Integer.parseInt(jsonStats.getString(SPEED_EIGHTY));
                int averageSpeed_Hundred = Integer.parseInt(jsonStats.getString(SPEED_HUNDRED));
                int averageSpeed_Miscellaneous = Integer.parseInt(jsonStats.getString(SPEED_MISCELLANEOUS));
                //Road Types
                int averageSpeed_rural = Integer.parseInt(jsonStats.getString(SPEED_RURAL));
                int averageSpeed_urban = Integer.parseInt(jsonStats.getString(SPEED_URBAN));
                int averageSpeed_motorway = Integer.parseInt(jsonStats.getString(SPEED_MOTORWAY));

                //Each Journey will be associated with one particular weather condition
//            String weather = jsonStats.getString("weather");

                journeyStatistics.setAverageSpeed_Miscellaneous(averageSpeed_Miscellaneous);
                journeyStatistics.setAverageSpeed_Hundred(averageSpeed_Hundred);
                journeyStatistics.setAverageSpeed_Eighty(averageSpeed_Eighty);
                journeyStatistics.setAverageSpeed_Sixty(averageSpeed_Sixty);
                journeyStatistics.setAverageSpeed_Fifty(averageSpeed_Fifty);
                journeyStatistics.setAverageSpeed_Twenty(averageSpeed_Twenty);
                journeyStatistics.setAverageSpeed_rural(averageSpeed_rural);
                journeyStatistics.setAverageSpeed_urban(averageSpeed_urban);
                journeyStatistics.setAverageSpeed_motorway(averageSpeed_motorway);

                journeyStatistics.setDistanceTravelled_day_total(distanceTravelled_day_total);
                journeyStatistics.setDistanceTravelled_night_total(distanceTravelled_night_total);
                journeyStatistics.setDistanceTravelled_Miscellaneous_day(distanceTravelled_Miscellaneous_day);
                journeyStatistics.setDistanceTravelled_Miscellaneous_night(distanceTravelled_Miscellaneous_night);
                journeyStatistics.setDistanceTravelled_Miscellaneous_total(distanceTravelled_Miscellaneous_total);
                journeyStatistics.setDistanceTravelled_Hundred_day(distanceTravelled_Hundred_day);
                journeyStatistics.setDistanceTravelled_Hundred_night(distanceTravelled_Hundred_night);
                journeyStatistics.setDistanceTravelled_Hundred_total(distanceTravelled_Hundred_total);
                journeyStatistics.setDistanceTravelled_Eighty_day(distanceTravelled_Eighty_day);
                journeyStatistics.setDistanceTravelled_Eighty_night(distanceTravelled_Eighty_night);
                journeyStatistics.setDistanceTravelled_Eighty_total(distanceTravelled_Eighty_total);
                journeyStatistics.setDistanceTravelled_Sixty_day(distanceTravelled_Sixty_day);
                journeyStatistics.setDistanceTravelled_Sixty_night(distanceTravelled_Sixty_night);
                journeyStatistics.setDistanceTravelled_Sixty_total(distanceTravelled_Sixty_total);
                journeyStatistics.setDistanceTravelled_Fifty_day(distanceTravelled_Fifty_day);
                journeyStatistics.setDistanceTravelled_Fifty_night(distanceTravelled_Fifty_night);
                journeyStatistics.setDistanceTravelled_Fifty_total(distanceTravelled_Fifty_total);
                journeyStatistics.setDistanceTravelled_Twenty_day(distanceTravelled_Twenty_day);
                journeyStatistics.setDistanceTravelled_Twenty_night(distanceTravelled_Twenty_night);
                journeyStatistics.setDistanceTravelled_Twenty_total(distanceTravelled_Twenty_total);
                journeyStatistics.setDistanceTravelled_rural_day(distanceTravelled_rural_day);
                journeyStatistics.setDistanceTravelled_rural_night(distanceTravelled_rural_night);
                journeyStatistics.setDistanceTravelled_rural_total(distanceTravelled_rural_total);
                journeyStatistics.setDistanceTravelled_urban_day(distanceTravelled_urban_day);
                journeyStatistics.setDistanceTravelled_urban_night(distanceTravelled_urban_night);
                journeyStatistics.setDistanceTravelled_urban_total(distanceTravelled_urban_total);
                journeyStatistics.setDistanceTravelled_motorway_day(distanceTravelled_motorway_day);
                journeyStatistics.setDistanceTravelled_motorway_night(distanceTravelled_motorway_night);
                journeyStatistics.setDistanceTravelled_motorway_total(distanceTravelled_motorway_total);

                journeyStatistics.setTimeDriven_day_total(timeDriven_day_total);
                journeyStatistics.setTimeDriven_night_total(timeDriven_night_total);
                journeyStatistics.setTimeDriven_Miscellaneous_day(timeDriven_Miscellaneous_day);
                journeyStatistics.setTimeDriven_Miscellaneous_night(timeDriven_Miscellaneous_night);
                journeyStatistics.setTimeDriven_Miscellaneous_total(timeDriven_Miscellaneous_total);
                journeyStatistics.setTimeDriven_Hundred_day(timeDriven_Hundred_day);
                journeyStatistics.setTimeDriven_Hundred_night(timeDriven_Hundred_night);
                journeyStatistics.setTimeDriven_Hundred_total(timeDriven_Hundred_total);
                journeyStatistics.setTimeDriven_Eighty_day(timeDriven_Eighty_day);
                journeyStatistics.setTimeDriven_Eighty_night(timeDriven_Eighty_night);
                journeyStatistics.setTimeDriven_Eighty_total(timeDriven_Eighty_total);
                journeyStatistics.setTimeDriven_Sixty_day(timeDriven_Sixty_day);
                journeyStatistics.setTimeDriven_Sixty_night(timeDriven_Sixty_night);
                journeyStatistics.setTimeDriven_Sixty_total(timeDriven_Sixty_total);
                journeyStatistics.setTimeDriven_Fifty_day(timeDriven_Fifty_day);
                journeyStatistics.setTimeDriven_Fifty_night(timeDriven_Fifty_night);
                journeyStatistics.setTimeDriven_Fifty_total(timeDriven_Fifty_total);
                journeyStatistics.setTimeDriven_Twenty_day(timeDriven_Twenty_day);
                journeyStatistics.setTimeDriven_Twenty_night(timeDriven_Twenty_night);
                journeyStatistics.setTimeDriven_Twenty_total(timeDriven_Twenty_total);
                journeyStatistics.setTimeDriven_rural_day(timeDriven_rural_day);
                journeyStatistics.setTimeDriven_rural_night(timeDriven_rural_night);
                journeyStatistics.setTimeDriven_rural_total(timeDriven_rural_total);
                journeyStatistics.setTimeDriven_urban_day(timeDriven_urban_day);
                journeyStatistics.setTimeDriven_urban_night(timeDriven_urban_night);
                journeyStatistics.setTimeDriven_urban_total(timeDriven_urban_total);
                journeyStatistics.setTimeDriven_motorway_day(timeDriven_motorway_day);
                journeyStatistics.setTimeDriven_motorway_night(timeDriven_motorway_night);
                journeyStatistics.setTimeDriven_motorway_total(timeDriven_motorway_total);
                journeyStatistics.setJourneyStatisticsId(journeyStatisticsId);
                journeyStatistics.setJourneyStatisticsId(journeyStatisticsId);
                journeyStatistics.setWeather(weather);

                journeyStatisticsList.add(journeyStatistics);


            }
        }catch (JSONException e){
            e.printStackTrace();
        }
        return  journeyStatisticsList;
    }


    public class GetJourneyDataAsyncTask extends AsyncTask<Void, Void, Journey> {
        Journey journey;
        GetJourneyCallback journeyCallback;

        public GetJourneyDataAsyncTask(Journey journey, GetJourneyCallback journeyCallback) {
            this.journey = journey;
            this.journeyCallback = journeyCallback;
        }

        @Override
        protected Journey doInBackground(Void... params) {
            Journey returnedJourney = getJourney(journey.getJourneyId());
            return returnedJourney;
        }

        @Override
        protected void onPostExecute(Journey result) {
            super.onPostExecute(result);
            progressDialog.dismiss();
            journeyCallback.done(result);

        }

    }

    public class GetAllUserJourneysDataAsyncTask extends AsyncTask<Void, Void, List<Journey>> {
        User user;
        GetAllJourneysCallback journeyCallback;

        public GetAllUserJourneysDataAsyncTask(User user, GetAllJourneysCallback journeyCallback) {
            this.user = user;
            this.journeyCallback = journeyCallback;
        }

        @Override
        protected List<Journey> doInBackground(Void... params) {
            List<Journey> journeyList = getAllUserJourneys(user.getUserName());


            return journeyList;
        }

        @Override
        protected void onPostExecute(List<Journey> result) {
            super.onPostExecute(result);
            progressDialog.dismiss();
            journeyCallback.done(result);

        }

    }

    public class GetStatsDataAsyncTask extends AsyncTask<Void, Void, JourneyStatistics> {
        Journey journey;
        GetStatsCallback statsCallback;

        public GetStatsDataAsyncTask(Journey journey, GetStatsCallback statsCallback) {
            this.journey = journey;
            this.statsCallback = statsCallback;
        }

        @Override
        protected JourneyStatistics doInBackground(Void... params) {
            JourneyStatistics journeyStatistics = getStatsForJourney(journey.getJourneyId());
            return journeyStatistics;
        }

        @Override
        protected void onPostExecute(JourneyStatistics result) {
            super.onPostExecute(result);
            progressDialog.dismiss();
            statsCallback.done(result);

        }

    }

    public class GetAllUserStatsDataAsyncTask extends AsyncTask<Void, Void, List<JourneyStatistics>> {
        User user;
        GetAllStatsCallback statsCallback;

        public GetAllUserStatsDataAsyncTask(User user, GetAllStatsCallback statsCallback) {
            this.user = user;
            this.statsCallback = statsCallback;
        }

        @Override
        protected List<JourneyStatistics> doInBackground(Void... params) {

            List<JourneyStatistics> journeyStatsList = getTotalJourneyStats(user.getUserName());
            return journeyStatsList;
        }

        @Override
        protected void onPostExecute(List<JourneyStatistics> result) {
            super.onPostExecute(result);
            progressDialog.dismiss();
            statsCallback.done(result);

        }

    }

}
