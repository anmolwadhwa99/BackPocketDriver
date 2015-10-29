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

/**
 * A simple {@link Fragment} subclass.
 */
public class AverageSpeedFragmentIndividual extends Fragment {

    private Spinner roadTypeSpinner;
    private Spinner speedLimitSpinner;
    private Journey journey;
    public AverageSpeedFragmentIndividual() {
        // Required empty public constructor
    }

    public enum IndividualAverageSpeedCategory{
        RURAL, URBAN, MOTORWAY, TWENTY, FIFTY, SIXTY, EIGHTY, HUNDRED, MISCELLANEOUS
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        final View view = inflater.inflate(R.layout.fragment_average_speed_fragment_individual, container, false);
        journey = (Journey) getArguments().getSerializable("journey");

        setSpinnerContent(view);

        final Button result = (Button) view.findViewById(R.id.button_individual_average_speed);
        final Button reset = (Button) view.findViewById(R.id.button_individual_average_speed_reset);

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
                getSpinnerContent(view);
            }
        });

        return view;
    }

    private void setSpinnerContent(View view) {
        //Spinner spinner1;
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter1;
        ArrayAdapter<CharSequence> adapter2;


        //road type spinner
        roadTypeSpinner = (Spinner) view.findViewById(R.id.individual_average_speed_road_type);
        roadTypeSpinner.getBackground().setColorFilter(getResources().getColor(R.color.background_blue), PorterDuff.Mode.SRC_ATOP);
        adapter1 = ArrayAdapter.createFromResource(getActivity().getBaseContext(),
                R.array.road_type_array, R.layout.spinner_item);
        adapter1.setDropDownViewResource(R.layout.spinner_item);
        roadTypeSpinner.setAdapter(adapter1);

        //Spinner spinner2;

        //speed limit spinner
        speedLimitSpinner = (Spinner) view.findViewById(R.id.individual_average_speed_speed_limit);
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

    public int calculateAverageSpeed(IndividualAverageSpeedCategory category, JourneyStatistics stats){
        int speed = 0;
        switch (category){
            case TWENTY:
                speed = stats.getAverageSpeed_Twenty();
                break;
            case FIFTY:
                speed = stats.getAverageSpeed_Fifty();
                break;
            case SIXTY:
                speed = stats.getAverageSpeed_Sixty();
                break;
            case EIGHTY:
                speed = stats.getAverageSpeed_Eighty();
                break;
            case HUNDRED:
                speed = stats.getAverageSpeed_Hundred();
                break;
            case MISCELLANEOUS:
                speed = stats.getAverageSpeed_Miscellaneous();
                break;
            case RURAL:
                speed = stats.getAverageSpeed_rural();
                break;
            case URBAN:
                speed = stats.getAverageSpeed_urban();
                break;
            case MOTORWAY:
                speed = stats.getAverageSpeed_motorway();
        }
        return speed;
    }

    private void getSpinnerContent(View view){
        IndividualAverageSpeedCategory category = null;
        String selectedRoadType = "";
        String selectedSpeedLimit = "";

        selectedRoadType = roadTypeSpinner.getSelectedItem().toString();
        selectedSpeedLimit = speedLimitSpinner.getSelectedItem().toString();

        if(selectedRoadType.equals("Any Road Type") && selectedSpeedLimit.equals("Any Speed")){
            showErrorMessage("Please be more specific with your selection");
            return;
        }
        category = calculateCategory(selectedRoadType, selectedSpeedLimit);
        findSpeed(journey, category);
    }

    public void showErrorMessage(String message) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
        dialogBuilder.setMessage(message);
        dialogBuilder.setPositiveButton("Ok", null);
        dialogBuilder.show();
    }

    private IndividualAverageSpeedCategory calculateCategory(String selectedRoadType, String selectedSpeedLimit) {
        IndividualAverageSpeedCategory averageSpeedCategory;
        switch(selectedRoadType + "|" + selectedSpeedLimit){
            case "Urban|Any Speed":
                averageSpeedCategory = IndividualAverageSpeedCategory.URBAN;
                break;
            case "Rural|Any Speed":
                averageSpeedCategory = IndividualAverageSpeedCategory.RURAL;
                break;
            case "Motorway|Any Speed":
                averageSpeedCategory = IndividualAverageSpeedCategory.MOTORWAY;
                break;
            case "Any Road Type|20 km/h":
                averageSpeedCategory = IndividualAverageSpeedCategory.TWENTY;
                break;
            case "Any Road Type|50 km/h":
                averageSpeedCategory = IndividualAverageSpeedCategory.FIFTY;
                break;
            case "Any Road Type|60 km/h":
                averageSpeedCategory = IndividualAverageSpeedCategory.SIXTY;
                break;
            case "Any Road Type|80 km/h":
                averageSpeedCategory = IndividualAverageSpeedCategory.EIGHTY;
                break;
            case "Any Road Type|100 km/h":
                averageSpeedCategory = IndividualAverageSpeedCategory.HUNDRED;
                break;
            case "Any Road Type|Miscellaneous":
                averageSpeedCategory = IndividualAverageSpeedCategory.MISCELLANEOUS;
                break;
            default:
                averageSpeedCategory = null;
                break;
        }
        return averageSpeedCategory;
    }

    public void findSpeed(Journey journey, final IndividualAverageSpeedCategory averageSpeedCategory){
        calculateSpeed(averageSpeedCategory);
    }

    public void calculateSpeed(IndividualAverageSpeedCategory averageSpeedCategory){
        int result = calculateAverageSpeed(averageSpeedCategory, journey.getJourneyStatistics());
        TextView textView = (TextView) getActivity().findViewById(R.id.result_individual_average_speed);
        textView.setText(result + " km/h");
        textView.setPadding(30,0,0,0);
    }

}
