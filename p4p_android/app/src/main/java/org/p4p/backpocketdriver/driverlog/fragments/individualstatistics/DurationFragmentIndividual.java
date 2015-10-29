package org.p4p.backpocketdriver.driverlog.fragments.individualstatistics;


import android.app.AlertDialog;
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
import org.p4p.backpocketdriver.driverlog.models.Journey;
import org.p4p.backpocketdriver.driverlog.models.JourneyStatistics;
import org.p4p.backpocketdriver.driverlog.pojo.Conversion;

/**
 * A simple {@link Fragment} subclass.
 */
public class DurationFragmentIndividual extends Fragment {
    private Spinner roadTypeSpinner;
    private Spinner speedLimitSpinner;
    private Journey journey;
    private Conversion conversion;
    public DurationFragmentIndividual() {
        // Required empty public constructor
        conversion = new Conversion();
    }

    public enum IndividualDurationCategory{
        RURAL, URBAN, MOTORWAY, TWENTY, FIFTY, SIXTY, EIGHTY, HUNDRED, MISCELLANEOUS
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View myView = inflater.inflate(R.layout.fragment_duration_fragment_individual, container, false);
        journey = (Journey) getArguments().getSerializable("journey");


        setSpinnerContent(myView);

        Button result = (Button) myView.findViewById(R.id.button_individual_duration);
        Button reset = (Button) myView.findViewById(R.id.button_individual_duration_reset);

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                roadTypeSpinner.setSelection(0);
                speedLimitSpinner.setSelection(0);
            }
        });

        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSpinnerContent(myView);
            }
        });

        return myView;
    }

    private void setSpinnerContent(View view) {
        //Spinner spinner1;
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter1;
        ArrayAdapter<CharSequence> adapter2;


        roadTypeSpinner = (Spinner) view.findViewById(R.id.individual_duration_road_type);
        roadTypeSpinner.getBackground().setColorFilter(getResources().getColor(R.color.background_blue), PorterDuff.Mode.SRC_ATOP);
        adapter1 = ArrayAdapter.createFromResource(getActivity().getBaseContext(),
                R.array.road_type_array, R.layout.spinner_item);
        adapter1.setDropDownViewResource(R.layout.spinner_item);
        roadTypeSpinner.setAdapter(adapter1);



        //speed limit spinner
        speedLimitSpinner = (Spinner) view.findViewById(R.id.individual_duration_speed_limit);
        speedLimitSpinner.getBackground().setColorFilter(getResources().getColor(R.color.background_blue), PorterDuff.Mode.SRC_ATOP);
        adapter2 = ArrayAdapter.createFromResource(getActivity().getBaseContext(),
                R.array.speed_limit_array, R.layout.spinner_item);
        adapter2.setDropDownViewResource(R.layout.spinner_item);
        speedLimitSpinner.setAdapter(adapter2);

        roadTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int pos, long arg3) {
                // TODO Auto-generated method stub
                if (pos == 0) {
                    speedLimitSpinner.setEnabled(true);
                    speedLimitSpinner.getBackground().setColorFilter(getResources().getColor(R.color.background_blue), PorterDuff.Mode.SRC_ATOP);
                } else {
                    // do something

                    speedLimitSpinner.setEnabled(false);


                }
            }


            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }


        });

        speedLimitSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int pos, long arg3) {
                // TODO Auto-generated method stub
                if (pos == 0) {
                    roadTypeSpinner.setEnabled(true);
                    roadTypeSpinner.getBackground().setColorFilter(getResources().getColor(R.color.background_blue), PorterDuff.Mode.SRC_ATOP);

                } else {
                    // do something

                    roadTypeSpinner.setEnabled(false);


                }
            }


            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }


        });
    }

    public int calculateDuration(IndividualDurationCategory category, JourneyStatistics stats){
        int speed = 0;
        switch (category){
            case TWENTY:
                speed = stats.getTimeDriven_Twenty_total();
                break;
            case FIFTY:
                speed = stats.getTimeDriven_Fifty_total();
                break;
            case SIXTY:
                speed = stats.getTimeDriven_Sixty_total();
                break;
            case EIGHTY:
                speed = stats.getTimeDriven_Eighty_total();
                break;
            case HUNDRED:
                speed = stats.getTimeDriven_Hundred_total();
                break;
            case MISCELLANEOUS:
                speed = stats.getTimeDriven_Miscellaneous_total();
                break;
            case RURAL:
                speed = stats.getTimeDriven_rural_total();
                break;
            case URBAN:
                speed = stats.getTimeDriven_urban_total();
                break;
            case MOTORWAY:
                speed = stats.getTimeDriven_motorway_total();
        }
        return speed;
    }

    private void getSpinnerContent(View view){
        IndividualDurationCategory category = null;
        String selectedRoadType = "";
        String selectedSpeedLimit = "";

        selectedRoadType = roadTypeSpinner.getSelectedItem().toString();
        selectedSpeedLimit = speedLimitSpinner.getSelectedItem().toString();



        if(selectedRoadType.equals("Any Road Type") && selectedSpeedLimit.equals("Any Speed")){
            String time = conversion.convertSeconds(journey.getDuration());
            TextView textView = (TextView) getActivity().findViewById(R.id.result_individual_duration);
            textView.setText(time);
            textView.setPadding(30,0,0,0);
            return;
        }
        category = calculateCategory(selectedRoadType, selectedSpeedLimit);
        findDuration(journey, category);
    }

    public void showErrorMessage(String message) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
        dialogBuilder.setMessage(message);
        dialogBuilder.setPositiveButton("Ok", null);
        dialogBuilder.show();
    }


    private IndividualDurationCategory calculateCategory(String selectedRoadType, String selectedSpeedLimit) {
        IndividualDurationCategory durationCategory;
        switch(selectedRoadType + "|" + selectedSpeedLimit){
            case "Urban|Any Speed":
                durationCategory = IndividualDurationCategory.URBAN;
                break;
            case "Rural|Any Speed":
                durationCategory = IndividualDurationCategory.RURAL;
                break;
            case "Motorway|Any Speed":
                durationCategory = IndividualDurationCategory.MOTORWAY;
                break;
            case "Any Road Type|20 km/h":
                durationCategory = IndividualDurationCategory.TWENTY;
                break;
            case "Any Road Type|50 km/h":
                durationCategory = IndividualDurationCategory.FIFTY;
                break;
            case "Any Road Type|60 km/h":
                durationCategory = IndividualDurationCategory.SIXTY;
                break;
            case "Any Road Type|80 km/h":
                durationCategory = IndividualDurationCategory.EIGHTY;
                break;
            case "Any Road Type|100 km/h":
                durationCategory = IndividualDurationCategory.HUNDRED;
                break;
            case "Any Road Type|Miscellaneous":
                durationCategory = IndividualDurationCategory.MISCELLANEOUS;
                break;
            default:
                durationCategory = null;
                break;
        }
        return durationCategory;
    }

    public void findDuration(Journey journey, final IndividualDurationCategory durationCategory){
        calculateDuration(durationCategory);
    }

    public void calculateDuration(IndividualDurationCategory durationCategory){

        int result = calculateDuration(durationCategory, journey.getJourneyStatistics());
        String time = conversion.convertSeconds(result);
        TextView textView = (TextView) getActivity().findViewById(R.id.result_individual_duration);
        textView.setText(time);
        textView.setPadding(30,0,0,0);

    }


}
