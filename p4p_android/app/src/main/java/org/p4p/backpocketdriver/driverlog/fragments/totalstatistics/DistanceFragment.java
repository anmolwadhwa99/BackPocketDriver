package org.p4p.backpocketdriver.driverlog.fragments.totalstatistics;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import org.p4p.backpocketdriver.driverlog.R;
import org.p4p.backpocketdriver.driverlog.activities.DistanceSummary;
import org.p4p.backpocketdriver.driverlog.models.JourneyStatistics;
import org.p4p.backpocketdriver.driverlog.models.User;
import org.p4p.backpocketdriver.driverlog.pojo.Conversion;
import org.p4p.backpocketdriver.driverlog.pojo.storage.JourneyStatsStorage;
import org.p4p.backpocketdriver.driverlog.pojo.storage.JourneysStorage;
import org.p4p.backpocketdriver.driverlog.pojo.storage.UserDetailsStorage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 05/07/2015
 * By Anmol
 */
public class DistanceFragment extends Fragment {
    private Spinner weatherSpinner;
    private Spinner timeOfDaySpinner;
    private Spinner roadTypeSpinner;
    private Spinner speedLimitSpinner;
    private UserDetailsStorage currentUser;
    private JourneysStorage journeys;
    private JourneyStatsStorage journeyStatsStorage;
    private Conversion conversion;
    private List<JourneyStatistics> journeyStatistics = new ArrayList<JourneyStatistics>();
    private boolean done = false;


    public DistanceFragment(){
        conversion = new Conversion();

    }

    public enum DistanceCategory{
        TWENTY, FIFTY, SIXTY, EIGHTY, HUNDRED, MISCELLANEOUS, URBAN, RURAL, MOTORWAY, DAY, NIGHT,
        TWENTY_DAY, TWENTY_NIGHT,
        FIFTY_DAY, FIFTY_NIGHT,
        SIXTY_DAY, SIXTY_NIGHT,
        EIGHTY_DAY, EIGHTY_NIGHT,
        HUNDRED_DAY, HUNDRED_NIGHT,
        MISCELLANEOUS_DAY, MISCELLANEOUS_NIGHT,
        URBAN_DAY, URBAN_NIGHT,
        RURAL_DAY, RURAL_NIGHT,
        MOTORWAY_DAY, MOTORWAY_NIGHT,
        ANY_TYPE
    }

    public enum WeatherCategory{
        FINE, RAIN, DRIZZLE, FOG, OTHER, ANY_CONDITION
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        final View rootView = inflater.inflate(R.layout.fragment_distance, container, false);
        currentUser = new UserDetailsStorage(getActivity());
        journeys = new JourneysStorage(getActivity());
        journeyStatsStorage = new JourneyStatsStorage(getActivity());

        Button resultButton  = (Button) rootView.findViewById(R.id.result_button_distance);
        Button summaryButton = (Button) rootView.findViewById(R.id.summary_button_distance);
        Button resetButton = (Button) rootView.findViewById(R.id.button_distance_reset);

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                weatherSpinner.setSelection(0);
                roadTypeSpinner.setSelection(0);
                speedLimitSpinner.setSelection(0);
                timeOfDaySpinner.setSelection(0);
            }
        });
        resultButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                getSpinnerContent(view);
            }
        });

        summaryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DistanceSummary.class);
                startActivity(intent);
            }
        });

        setSpinnerContent(rootView);
        return rootView;
    }


    private void setSpinnerContent(View view){
        weatherSpinner = (Spinner) view.findViewById(R.id.weather_spinner_distance);
        weatherSpinner.getBackground().setColorFilter(getResources().getColor(R.color.background_blue), PorterDuff.Mode.SRC_ATOP);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity().getBaseContext(),
                R.array.weather_array, R.layout.spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(R.layout.spinner_item);
        // Apply the adapter to the spinner
        weatherSpinner.setAdapter(adapter);


        //time of day spinner
        timeOfDaySpinner = (Spinner) view.findViewById(R.id.time_of_day_spinner_distance);
        timeOfDaySpinner.getBackground().setColorFilter(getResources().getColor(R.color.background_blue), PorterDuff.Mode.SRC_ATOP);
        adapter = ArrayAdapter.createFromResource(getActivity().getBaseContext(),
                R.array.time_of_day_array, R.layout.spinner_item);
        adapter.setDropDownViewResource(R.layout.spinner_item);
        timeOfDaySpinner.setAdapter(adapter);

        //road type spinner
        roadTypeSpinner = (Spinner) view.findViewById(R.id.road_type_spinner_distance);
        roadTypeSpinner.getBackground().setColorFilter(getResources().getColor(R.color.background_blue), PorterDuff.Mode.SRC_ATOP);
        adapter = ArrayAdapter.createFromResource(getActivity().getBaseContext(),
                R.array.road_type_array, R.layout.spinner_item);
        adapter.setDropDownViewResource(R.layout.spinner_item);
        roadTypeSpinner.setAdapter(adapter);

        //speed limit spinner
        speedLimitSpinner = (Spinner) view.findViewById(R.id.speed_limit_spinner_distance);
        speedLimitSpinner.getBackground().setColorFilter(getResources().getColor(R.color.background_blue), PorterDuff.Mode.SRC_ATOP);
        adapter = ArrayAdapter.createFromResource(getActivity().getBaseContext(),
                R.array.speed_limit_array, R.layout.spinner_item);
        adapter.setDropDownViewResource(R.layout.spinner_item);
        speedLimitSpinner.setAdapter(adapter);


        timeOfDaySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int pos, long arg3) {
                // TODO Auto-generated method stub
                if(pos == 0){


                    weatherSpinner.setEnabled(true);
                    weatherSpinner.getBackground().setColorFilter(getResources().getColor(R.color.background_blue), PorterDuff.Mode.SRC_ATOP);

                    if(!(roadTypeSpinner.getSelectedItem().toString().equals("Any Road Type"))){
                        speedLimitSpinner.setEnabled(false);
                        // weatherSpinner.setEnabled(false);
                    }else{
                        speedLimitSpinner.setEnabled(true);
                        speedLimitSpinner.getBackground().setColorFilter(getResources().getColor(R.color.background_blue), PorterDuff.Mode.SRC_ATOP);
                    }

                    if (!(speedLimitSpinner.getSelectedItem().toString().equals("Any Speed"))){
                        roadTypeSpinner.setEnabled(false);
                    }else{
                        roadTypeSpinner.setEnabled(true);
                        roadTypeSpinner.getBackground().setColorFilter(getResources().getColor(R.color.background_blue), PorterDuff.Mode.SRC_ATOP);

                    }

                }
                else{
                    // do something
                    if(!(roadTypeSpinner.getSelectedItem().toString().equals("Any Road Type"))){
                        speedLimitSpinner.setEnabled(false);
                        // weatherSpinner.setEnabled(false);
                    }
                    else if(!(speedLimitSpinner.getSelectedItem().toString().equals("Any Speed"))){
                        roadTypeSpinner.setEnabled(false);
                        //weatherSpinner.setEnabled(false);
                    }else{
                        //weatherSpinner.setEnabled(false);
                        roadTypeSpinner.setEnabled(true);
                        speedLimitSpinner.setEnabled(true);
                    }



                }
            }


            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }



        });




        weatherSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int pos, long arg3) {
                // TODO Auto-generated method stub
                if(pos == 0){
                    timeOfDaySpinner.setEnabled(true);
                    timeOfDaySpinner.getBackground().setColorFilter(getResources().getColor(R.color.background_blue), PorterDuff.Mode.SRC_ATOP);
                    if(!(roadTypeSpinner.getSelectedItem().toString().equals("Any Road Type"))){
                        speedLimitSpinner.setEnabled(false);
                        // weatherSpinner.setEnabled(false);
                    }else{
                        speedLimitSpinner.setEnabled(true);
                        speedLimitSpinner.getBackground().setColorFilter(getResources().getColor(R.color.background_blue), PorterDuff.Mode.SRC_ATOP);
                    }

                    if (!(speedLimitSpinner.getSelectedItem().toString().equals("Any Speed"))){
                        roadTypeSpinner.setEnabled(false);
                    }else{
                        roadTypeSpinner.setEnabled(true);
                        roadTypeSpinner.getBackground().setColorFilter(getResources().getColor(R.color.background_blue), PorterDuff.Mode.SRC_ATOP);

                    }
                }
                else{
                    // do something


                }
            }


            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }



        });

        roadTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int pos, long arg3) {
                // TODO Auto-generated method stub
                if(pos == 0){
                    timeOfDaySpinner.setEnabled(true);
                    weatherSpinner.setEnabled(true);
                    weatherSpinner.getBackground().setColorFilter(getResources().getColor(R.color.background_blue), PorterDuff.Mode.SRC_ATOP);
                    speedLimitSpinner.setEnabled(true);
                    speedLimitSpinner.getBackground().setColorFilter(getResources().getColor(R.color.background_blue), PorterDuff.Mode.SRC_ATOP);
                }
                else{
                    // do something

                    timeOfDaySpinner.setEnabled(true);
                    //weatherSpinner.setEnabled(false);
                    speedLimitSpinner.setEnabled(false);

                }
            }


            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }



        });

        speedLimitSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int pos, long arg3) {
                // TODO Auto-generated method stub
                if(pos == 0){
                    timeOfDaySpinner.setEnabled(true);
                    weatherSpinner.setEnabled(true);
                    weatherSpinner.getBackground().setColorFilter(getResources().getColor(R.color.background_blue), PorterDuff.Mode.SRC_ATOP);
                    roadTypeSpinner.setEnabled(true);
                    roadTypeSpinner.getBackground().setColorFilter(getResources().getColor(R.color.background_blue), PorterDuff.Mode.SRC_ATOP);
                }
                else{
                    // do something

                    timeOfDaySpinner.setEnabled(true);
                    //weatherSpinner.setEnabled(false);
                    roadTypeSpinner.setEnabled(false);

                }
            }


            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }

        });




    }

    public void showErrorMessage(String message) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
        dialogBuilder.setMessage(message);
        dialogBuilder.setPositiveButton("Ok", null);
        dialogBuilder.show();
    }

    private void getTotalDistance(List<JourneyStatistics> statsList){
        int sum =0;
        for(JourneyStatistics js : statsList){
            sum += js.getDistanceTravelled_day_total() + js.getDistanceTravelled_night_total();
        }
        TextView textView = (TextView) getActivity().findViewById(R.id.result_distance);
        String km = conversion.convertMetres(sum);
        textView.setText(km + " km");
        textView.setPadding(30, 0, 0, 0);
    }


    private void getSpinnerContent(View view){
        DistanceCategory distanceCategory = null;
        WeatherCategory weatherCategory = null;
        String selectedRoadType = "";
        String selectedTimeOfDay = "";
        String selectedWeather = "";
        String selectedSpeedLimit = "";


        selectedRoadType = roadTypeSpinner.getSelectedItem().toString();
        selectedTimeOfDay = timeOfDaySpinner.getSelectedItem().toString();
        selectedWeather = weatherSpinner.getSelectedItem().toString();
        selectedSpeedLimit = speedLimitSpinner.getSelectedItem().toString();


        if(selectedRoadType.equalsIgnoreCase("Any Road Type") && selectedWeather.equalsIgnoreCase("Any Condition") &&
                selectedTimeOfDay.equalsIgnoreCase("Anytime") && selectedSpeedLimit.equalsIgnoreCase("Any Speed")){
            getTotalDistance(journeyStatsStorage.getJourneyData());
            return;
        }

        if(!(weatherSpinner.getSelectedItem().toString().equals("Any Condition"))){
            weatherCategory = weatherType(selectedWeather);
        }else{
            weatherCategory = WeatherCategory.ANY_CONDITION;
        }

        if(!(roadTypeSpinner.getSelectedItem().equals("Any Road Type"))){
            distanceCategory  = roadTypeTimeOfDay(selectedRoadType, selectedTimeOfDay);

        }
        else if(!(speedLimitSpinner.getSelectedItem().equals("Any Speed"))){
            distanceCategory = speedLimitTimeOfDay(selectedSpeedLimit, selectedTimeOfDay);

        }else if(!(timeOfDaySpinner.getSelectedItem().toString().equals("Anytime") &&
                roadTypeSpinner.getSelectedItem().toString().equals("Any Road Type") &&
                speedLimitSpinner.getSelectedItem().toString().equals("Any Speed"))){

            distanceCategory = timeOfDay(selectedTimeOfDay);

        }else if(timeOfDaySpinner.getSelectedItem().toString().equals("Anytime") &&
                roadTypeSpinner.getSelectedItem().toString().equals("Any Road Type") &&
                speedLimitSpinner.getSelectedItem().toString().equals("Any Speed") && (!weatherCategory.equals(WeatherCategory.ANY_CONDITION))){
            distanceCategory = DistanceCategory.ANY_TYPE;
        }
        else{
            showErrorMessage("Please select something else :/");
            return;
        }
        findAverageDistance(currentUser.getLoggedInUser(), distanceCategory, weatherCategory);
    }


    private DistanceCategory roadTypeTimeOfDay(String selectedRoadType, String selectedTimeOfDay) {
        DistanceCategory distanceCategory;
        switch(selectedRoadType + "|" + selectedTimeOfDay){
            case "Urban|Anytime":
                distanceCategory = DistanceCategory.URBAN;
                break;
            case "Rural|Anytime":
                distanceCategory = DistanceCategory.RURAL;
                break;
            case "Motorway|Anytime":
                distanceCategory = DistanceCategory.MOTORWAY;
                break;
            case "Urban|Day":
                distanceCategory = DistanceCategory.URBAN_DAY;
                break;
            case "Urban|Night":
                distanceCategory = DistanceCategory.URBAN_NIGHT;
                break;

            case "Rural|Day":
                distanceCategory = DistanceCategory.RURAL_DAY;
                break;

            case "Rural|Night":
                distanceCategory = DistanceCategory.RURAL_NIGHT;
                break;

            case "Motorway|Day":
                distanceCategory = DistanceCategory.MOTORWAY_DAY;
                break;

            case "Motorway|Night":
                distanceCategory = DistanceCategory.MOTORWAY_NIGHT;
                break;

            default:
                distanceCategory = null;
                break;
        }
        return  distanceCategory;
    }

    private WeatherCategory weatherType(String selectedWeather){
        WeatherCategory weatherCategory;
        switch(selectedWeather){
            case "Fine":
                weatherCategory = WeatherCategory.FINE;
                break;
            case "Rain":
                weatherCategory = WeatherCategory.RAIN;
                break;
            case "Drizzle":
                weatherCategory = WeatherCategory.DRIZZLE;
                break;
            case "Fog":
                weatherCategory = WeatherCategory.FOG;
                break;
            case "Other":
                weatherCategory = WeatherCategory.OTHER;
                break;
            default:
                weatherCategory = null;
                break;
        }
        return  weatherCategory;
    }


    public DistanceCategory speedLimitTimeOfDay(String selectedSpeedLimit, String selectedTimeOfDay){
        DistanceCategory distanceCategory = null;
        switch(selectedSpeedLimit + "|" + selectedTimeOfDay){
            case "20 km/h|Anytime":
                distanceCategory = DistanceCategory.TWENTY;
                break;
            case "50 km/h|Anytime":
                distanceCategory = DistanceCategory.FIFTY;
                break;
            case "60 km/h|Anytime":
                distanceCategory = DistanceCategory.SIXTY;
                break;
            case "80 km/h|Anytime":
                distanceCategory = DistanceCategory.EIGHTY;
                break;
            case "100 km/h|Anytime":
                distanceCategory = DistanceCategory.HUNDRED;
                break;
            case "Miscellaneous|Anytime":
                distanceCategory = DistanceCategory.MISCELLANEOUS;
                break;
            case "0-20 km/h|Night":
                distanceCategory = DistanceCategory.TWENTY_NIGHT;
                break;

            case "50 km/h|Night":
                distanceCategory = DistanceCategory.FIFTY_NIGHT;
                break;
            case "60 km/h|Night":
                distanceCategory = DistanceCategory.SIXTY_NIGHT;
                break;

            case "80 km/h|Night":
                distanceCategory = DistanceCategory.EIGHTY_NIGHT;
                break;

            case "100 km/h|Night":
                distanceCategory = DistanceCategory.HUNDRED_NIGHT;
                break;
            case "Miscellaneous|Night":
                distanceCategory = DistanceCategory.MISCELLANEOUS_NIGHT;
                break;

            case "20km/h|Day":
                distanceCategory = DistanceCategory.TWENTY_DAY;
                break;

            case "50 km/h|Day":
                distanceCategory = DistanceCategory.FIFTY_DAY;
                break;
            case "60 km/h|Day":
                distanceCategory = DistanceCategory.SIXTY_DAY;
                break;

            case "80 km/h|Day":
                distanceCategory = DistanceCategory.EIGHTY_DAY;
                break;

            case "100 km/h|Day":
                distanceCategory = DistanceCategory.HUNDRED_DAY;
                break;

            case "Miscellaneous|Day":
                distanceCategory = DistanceCategory.MISCELLANEOUS_DAY;
                break;

            default:
                distanceCategory = null;
                break;
        }
        return  distanceCategory;

    }

    public DistanceCategory timeOfDay(String timeOfDay){
        DistanceCategory distanceCategory = null;
        switch(timeOfDay){
            case "Day":
                distanceCategory = DistanceCategory.DAY;
                break;
            case "Night":
                distanceCategory = DistanceCategory.NIGHT;
                break;
            default:
                distanceCategory = null;
                break;
        }
        return  distanceCategory;

    }


    public int findDistanceTotal(List<JourneyStatistics> journeyStatisticsList, DistanceCategory distanceCategory){


        int total = 0;

        if (journeyStatisticsList == null || journeyStatisticsList.size() < 1){
            return 0;
        }
        switch (distanceCategory){
            case TWENTY:

                for (JourneyStatistics journeyStats : journeyStatisticsList){
                    //this method changes depending on the case
                    total = total + journeyStats.getDistanceTravelled_Twenty_total();
                }
                break;

            case TWENTY_DAY:

                for (JourneyStatistics journeyStats : journeyStatisticsList){
                    //this method changes depending on the case
                    total = total + journeyStats.getDistanceTravelled_Twenty_day();
                }
                break;

            case TWENTY_NIGHT:

                for (JourneyStatistics journeyStats : journeyStatisticsList){
                    //this method changes depending on the case
                    total = total + journeyStats.getDistanceTravelled_Twenty_night();
                }
                break;

            case SIXTY:
                for (JourneyStatistics journeyStats : journeyStatisticsList){
                    //this method changes depending on the case
                    total = total + journeyStats.getDistanceTravelled_Sixty_total();
                }
                break;

            case SIXTY_DAY:
                for (JourneyStatistics journeyStats : journeyStatisticsList){
                    //this method changes depending on the case
                    total = total + journeyStats.getDistanceTravelled_Sixty_day();
                }
                break;

            case SIXTY_NIGHT:
                for (JourneyStatistics journeyStats : journeyStatisticsList){
                    //this method changes depending on the case
                    total = total + journeyStats.getDistanceTravelled_Sixty_night();
                }
                break;

            case FIFTY:
                for (JourneyStatistics journeyStats : journeyStatisticsList){
                    //this method changes depending on the case
                    total = total + journeyStats.getDistanceTravelled_Fifty_total();
                }
                break;

            case FIFTY_DAY:
                for (JourneyStatistics journeyStats : journeyStatisticsList){
                    //this method changes depending on the case
                    total = total + journeyStats.getDistanceTravelled_Fifty_day();
                }
                break;

            case FIFTY_NIGHT:
                for (JourneyStatistics journeyStats : journeyStatisticsList){
                    //this method changes depending on the case
                    total = total + journeyStats.getDistanceTravelled_Fifty_night();
                }
                break;

            case EIGHTY:
                for (JourneyStatistics journeyStats : journeyStatisticsList){
                    //this method changes depending on the case
                    total = total + journeyStats.getDistanceTravelled_Eighty_total();
                }

                break;

            case EIGHTY_DAY:
                for (JourneyStatistics journeyStats : journeyStatisticsList){
                    //this method changes depending on the case
                    total = total + journeyStats.getDistanceTravelled_Eighty_day();
                }

                break;

            case EIGHTY_NIGHT:
                for (JourneyStatistics journeyStats : journeyStatisticsList){
                    //this method changes depending on the case
                    total = total + journeyStats.getDistanceTravelled_Eighty_night();
                }

                break;

            case HUNDRED:
                for (JourneyStatistics journeyStats : journeyStatisticsList){
                    //this method changes depending on the case
                    total = total + journeyStats.getDistanceTravelled_Hundred_total();
                }

                break;

            case HUNDRED_DAY:
                for (JourneyStatistics journeyStats : journeyStatisticsList){
                    //this method changes depending on the case
                    total = total + journeyStats.getDistanceTravelled_Hundred_day();
                }

                break;
            case HUNDRED_NIGHT:
                for (JourneyStatistics journeyStats : journeyStatisticsList){
                    //this method changes depending on the case
                    total = total + journeyStats.getDistanceTravelled_Hundred_night();
                }

                break;
            case MISCELLANEOUS:
                for (JourneyStatistics journeyStats : journeyStatisticsList){
                    //this method changes depending on the case
                    total = total + journeyStats.getDistanceTravelled_Miscellaneous_total();
                }

                break;

            case MISCELLANEOUS_DAY:
                for (JourneyStatistics journeyStats : journeyStatisticsList){
                    //this method changes depending on the case
                    total = total + journeyStats.getDistanceTravelled_Miscellaneous_day();
                }

                break;
            case MISCELLANEOUS_NIGHT:
                for (JourneyStatistics journeyStats : journeyStatisticsList){
                    //this method changes depending on the case
                    total = total + journeyStats.getDistanceTravelled_Miscellaneous_night();
                }

                break;
            case RURAL:
                for (JourneyStatistics journeyStats : journeyStatisticsList){
                    //this method changes depending on the case
                    total = total + journeyStats.getDistanceTravelled_rural_total();
                }

                break;

            case RURAL_DAY:
                for (JourneyStatistics journeyStats : journeyStatisticsList){
                    //this method changes depending on the case
                    total = total + journeyStats.getDistanceTravelled_rural_day();
                }

                break;

            case RURAL_NIGHT:
                for (JourneyStatistics journeyStats : journeyStatisticsList){
                    //this method changes depending on the case
                    total = total + journeyStats.getDistanceTravelled_rural_night();
                }

                break;

            case URBAN:
                for (JourneyStatistics journeyStats : journeyStatisticsList){
                    //this method changes depending on the case
                    total = total + journeyStats.getDistanceTravelled_urban_total();
                }

                break;

            case URBAN_DAY:
                for (JourneyStatistics journeyStats : journeyStatisticsList){
                    //this method changes depending on the case
                    total = total + journeyStats.getDistanceTravelled_urban_day();
                }

                break;

            case URBAN_NIGHT:
                for (JourneyStatistics journeyStats : journeyStatisticsList){
                    //this method changes depending on the case
                    total = total + journeyStats.getDistanceTravelled_urban_night();
                }

                break;
            case MOTORWAY:
                for (JourneyStatistics journeyStats : journeyStatisticsList){
                    //this method changes depending on the case
                    total = total + journeyStats.getDistanceTravelled_motorway_total();
                }
                break;

            case MOTORWAY_DAY:
                for (JourneyStatistics journeyStats : journeyStatisticsList){
                    //this method changes depending on the case
                    total = total + journeyStats.getDistanceTravelled_motorway_day();
                }
                break;

            case MOTORWAY_NIGHT:
                for (JourneyStatistics journeyStats : journeyStatisticsList){
                    //this method changes depending on the case
                    total = total + journeyStats.getDistanceTravelled_motorway_night();
                }
                break;

            case NIGHT:
                for (JourneyStatistics journeyStats : journeyStatisticsList){
                    //this method changes depending on the case
                    total = total + journeyStats.getDistanceTravelled_night_total();
                }
                break;

            case DAY:
                for (JourneyStatistics journeyStats : journeyStatisticsList){
                    //this method changes depending on the case
                    total = total + journeyStats.getDistanceTravelled_day_total();
                }
                break;
            case ANY_TYPE:
                for (JourneyStatistics journeyStats : journeyStatisticsList){
                    //this method changes depending on the case
                    total = total + journeyStats.getDistanceTravelled_day_total();
                    total = total + journeyStats.getDistanceTravelled_night_total();
                }

        }
        return total;
    }

    public void findAverageDistance(User user, final DistanceCategory distanceCategory, final WeatherCategory weatherCategory) {


        if (weatherCategory.equals(WeatherCategory.ANY_CONDITION)) {
            calculateDistance(distanceCategory, journeyStatsStorage.getJourneyData());
        } else {
            List<JourneyStatistics> journeyStatsList = new ArrayList<JourneyStatistics>();

            for (JourneyStatistics js : journeyStatsStorage.getJourneyData()) {
                if (js.getWeather().equals(weatherCategory.toString())) {
                    journeyStatsList.add(js);
                }
            }

            calculateDistance(distanceCategory, journeyStatsList);
        }
    }

    public void calculateDistance(DistanceCategory distanceCategory, List<JourneyStatistics> journeyStatsList){

        int result = findDistanceTotal(journeyStatsList, distanceCategory);
        TextView textView = (TextView) getActivity().findViewById(R.id.result_distance);
        String km = conversion.convertMetres(result);
        textView.setText(km + " km");
        textView.setPadding(30, 0, 0, 0);


    }

}



