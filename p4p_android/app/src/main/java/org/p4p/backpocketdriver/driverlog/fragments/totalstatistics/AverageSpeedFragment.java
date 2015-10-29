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
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import org.p4p.backpocketdriver.driverlog.R;
import org.p4p.backpocketdriver.driverlog.activities.AverageSpeedSummary;
import org.p4p.backpocketdriver.driverlog.models.JourneyStatistics;
import org.p4p.backpocketdriver.driverlog.models.User;
import org.p4p.backpocketdriver.driverlog.pojo.storage.JourneyStatsStorage;
import org.p4p.backpocketdriver.driverlog.pojo.storage.UserDetailsStorage;

import java.util.List;


/**
 * Created on 05/07/2015
 * By Anmol
 */
public class AverageSpeedFragment extends Fragment {
    private Spinner roadType;
    private Spinner speedLimit;
    private int averageSpeedResult;
    private UserDetailsStorage currentUser;
    private JourneyStatsStorage journeyStatsStorage;

    public enum SpeedCategory{
        TWENTY, FIFTY, SIXTY,EIGHTY, HUNDRED, MISCELLANEOUS, URBAN, RURAL, MOTORWAY
    }

    public AverageSpeedFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View myView = inflater.inflate(R.layout.fragment_average_speed, container, false);
        currentUser = new UserDetailsStorage(getActivity().getBaseContext());
        journeyStatsStorage = new JourneyStatsStorage(getActivity().getBaseContext());

        Button resultButton  = (Button) myView.findViewById(R.id.result_button_avg_speed);
        Button summaryButton = (Button) myView.findViewById(R.id.summary_button_average_speed);
        Button resetButton = (Button) myView.findViewById(R.id.button_average_speed_reset);

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                roadType.setSelection(0);
                speedLimit.setSelection(0);
            }
        });


        resultButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                TextView textView = (TextView)  myView.findViewById(R.id.result_average_speed);
                getSpinnerContent(view);
            }
        });


        summaryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AverageSpeedSummary.class);
                startActivity(intent);
            }
        });

        setSpinnerContent(myView);

        // Inflate the layout for this fragment
        return myView;
    }

    private void getSpinnerContent(View view){

        if(roadType.getSelectedItem().toString().equalsIgnoreCase("Any Road Type") && speedLimit.getSelectedItem().toString().equalsIgnoreCase("Any Speed")){
            showErrorMessage("Please be more specific with your selection");
            return;
        }

        SpeedCategory speedCategory = null;
        String selected;
        if(roadType.isEnabled() == true && speedLimit.isEnabled() == false){
            selected = roadType.getSelectedItem().toString();
            switch(selected){
                case "Urban":
                    speedCategory = SpeedCategory.URBAN;
                    break;
                case "Rural":
                    speedCategory = SpeedCategory.RURAL;
                    break;
                case "Motorway":
                    speedCategory = SpeedCategory.MOTORWAY;
                    break;
                default:
                    speedCategory = null;
            }


        }
        else if(roadType.isEnabled() == false && speedLimit.isEnabled() == true){
            selected = speedLimit.getSelectedItem().toString();
            switch(selected){
                case "20 km/h":
                    speedCategory = SpeedCategory.TWENTY;
                    break;
                case "50 km/h":
                    speedCategory = SpeedCategory.FIFTY;
                    break;
                case "60 km/h":
                    speedCategory = SpeedCategory.SIXTY;
                    break;
                case "80 km/h":
                    speedCategory = SpeedCategory.EIGHTY;
                    break;
                case "100 km/h":
                    speedCategory = SpeedCategory.HUNDRED;
                    break;
                case "Miscellaneous":
                    speedCategory = SpeedCategory.MISCELLANEOUS;
                    break;
                default:
                    speedCategory = null;
            }

        }
        findAverageSpeed(currentUser.getLoggedInUser(), speedCategory, view);
    }


    private void setSpinnerContent(View view){
        //Spinner spinner1;
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter1;

        //road type spinner
        roadType = (Spinner) view.findViewById(R.id.road_type_spinner_avg_speed);
        roadType.getBackground().setColorFilter(getResources().getColor(R.color.background_blue), PorterDuff.Mode.SRC_ATOP);
        adapter1 = ArrayAdapter.createFromResource(getActivity().getBaseContext(),
                R.array.road_type_array, R.layout.spinner_item);
        adapter1.setDropDownViewResource(R.layout.spinner_item);
        roadType.setAdapter(adapter1);

        //Spinner spinner2;
        ArrayAdapter<CharSequence> adapter2;

        //speed limit spinner
        speedLimit = (Spinner) view.findViewById(R.id.speed_limit_spinner_avg_speed);
        speedLimit.getBackground().setColorFilter(getResources().getColor(R.color.background_blue), PorterDuff.Mode.SRC_ATOP);
        adapter2 = ArrayAdapter.createFromResource(getActivity().getBaseContext(),
                R.array.speed_limit_array, R.layout.spinner_item);
        adapter2.setDropDownViewResource(R.layout.spinner_item);
        speedLimit.setAdapter(adapter2);

        roadType.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int pos, long arg3) {
                // TODO Auto-generated method stub
                if (pos == 0) {
                    speedLimit.setEnabled(true);
                    speedLimit.getBackground().setColorFilter(getResources().getColor(R.color.background_blue), PorterDuff.Mode.SRC_ATOP);
                }
                else {
                    // do something

                    speedLimit.setEnabled(false);


                }

            }


            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }


        });

        speedLimit.setOnItemSelectedListener(new OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int pos, long arg3) {
                // TODO Auto-generated method stub
                if(pos == 0){
                    roadType.setEnabled(true);
                    roadType.getBackground().setColorFilter(getResources().getColor(R.color.background_blue), PorterDuff.Mode.SRC_ATOP);

                }
                else{
                    // do something
                    roadType.setEnabled(false);
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

    public int findAverageSpeedTotal(List<JourneyStatistics> stats, SpeedCategory category){
        int total = 0;
        switch (category){
            case TWENTY:

                for (JourneyStatistics journeyStats : stats){
                    //this method changes depending on the case
                    total = total + journeyStats.getAverageSpeed_Twenty();
                }
                break;

            case FIFTY:
                for (JourneyStatistics journeyStats : stats){
                    //this method changes depending on the case
                    total = total + journeyStats.getAverageSpeed_Fifty();
                }
                break;
            case SIXTY:
                for (JourneyStatistics journeyStats : stats){
                    //this method changes depending on the case
                    total = total + journeyStats.getAverageSpeed_Sixty();
                }
                break;

            case EIGHTY:
                for (JourneyStatistics journeyStats : stats){
                    //this method changes depending on the case
                    total = total + journeyStats.getAverageSpeed_Eighty();
                }

                break;

            case HUNDRED:
                for (JourneyStatistics journeyStats : stats){
                    //this method changes depending on the case
                    total = total + journeyStats.getAverageSpeed_Hundred();
                }

                break;
            case MISCELLANEOUS:
                for (JourneyStatistics journeyStats : stats){
                    //this method changes depending on the case
                    total = total + journeyStats.getAverageSpeed_Miscellaneous();
                }

                break;

            case RURAL:
                for (JourneyStatistics journeyStats : stats){
                    //this method changes depending on the case
                    total = total + journeyStats.getAverageSpeed_rural();
                }

                break;

            case URBAN:
                for (JourneyStatistics journeyStats : stats){
                    //this method changes depending on the case
                    total = total + journeyStats.getAverageSpeed_urban();
                }


                break;

            case MOTORWAY:
                for (JourneyStatistics journeyStats : stats){
                    //this method changes depending on the case
                    total = total + journeyStats.getAverageSpeed_motorway();
                }
                break;

        }
        if (total == 0){
            return 0;
        }else{
            return total/stats.size();
        }

    }

    public void findAverageSpeed(User user, final SpeedCategory speedCategory, View view){

        List<JourneyStatistics> stats = journeyStatsStorage.getJourneyData();
        calculateAverageSpeed(speedCategory, stats);
    }

    public void calculateAverageSpeed(SpeedCategory speedCategory, List<JourneyStatistics> returnedResult){
        int result  = findAverageSpeedTotal(returnedResult, speedCategory);
        TextView textView = (TextView) getActivity().findViewById(R.id.result_average_speed);
        textView.setText(result + " km/h");
        textView.setPadding(30,0,0,0);


    }





}
