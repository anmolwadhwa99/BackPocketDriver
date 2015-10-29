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
public class DistanceFragmentIndividual extends Fragment {
    private Spinner roadTypeSpinner;
    private Spinner speedLimitSpinner;
    private Journey journey;
    private Conversion conversion;

    public DistanceFragmentIndividual() {
        // Required empty public constructor
        conversion = new Conversion();
    }

    public enum IndividualDistanceCategory{
        RURAL, URBAN, MOTORWAY, TWENTY, FIFTY, SIXTY, EIGHTY, HUNDRED, MISCELLANEOUS
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        final View myView = inflater.inflate(R.layout.fragment_distance_fragment_individual, container, false);
        journey = (Journey) getArguments().getSerializable("journey");

        setSpinnerContent(myView);

        Button result = (Button) myView.findViewById(R.id.button_individual_distance);
        Button reset = (Button) myView.findViewById(R.id.button_individual_distance_reset);

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


        roadTypeSpinner = (Spinner) view.findViewById(R.id.individual_distance_road_type);
        roadTypeSpinner.getBackground().setColorFilter(getResources().getColor(R.color.background_blue), PorterDuff.Mode.SRC_ATOP);
        adapter1 = ArrayAdapter.createFromResource(getActivity().getBaseContext(),
                R.array.road_type_array, R.layout.spinner_item);
        adapter1.setDropDownViewResource(R.layout.spinner_item);
        roadTypeSpinner.setAdapter(adapter1);



        //speed limit spinner
        speedLimitSpinner = (Spinner) view.findViewById(R.id.individual_distance_speed_limit);
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
    public int calculateDistance(IndividualDistanceCategory category, JourneyStatistics stats){
        int distance = 0;
        switch (category){
            case TWENTY:
                distance = stats.getDistanceTravelled_Twenty_total();
                break;
            case FIFTY:
                distance = stats.getDistanceTravelled_Fifty_total();
                break;
            case SIXTY:
                distance = stats.getDistanceTravelled_Sixty_total();
                break;
            case EIGHTY:
                distance = stats.getDistanceTravelled_Eighty_total();
                break;
            case HUNDRED:
                distance = stats.getDistanceTravelled_Hundred_total();
                break;
            case MISCELLANEOUS:
                distance = stats.getDistanceTravelled_Miscellaneous_total();
                break;
            case RURAL:
                distance = stats.getDistanceTravelled_rural_total();
                break;
            case URBAN:
                distance = stats.getDistanceTravelled_urban_total();
                break;
            case MOTORWAY:
                distance = stats.getDistanceTravelled_motorway_total();
                break;
        }
        return distance;
}

    private void getSpinnerContent(View view){
        IndividualDistanceCategory distanceCategory = null;
        String selectedRoadType = "";
        String selectedSpeedLimit = "";


        selectedRoadType = roadTypeSpinner.getSelectedItem().toString();
        selectedSpeedLimit = speedLimitSpinner.getSelectedItem().toString();



        if(selectedRoadType.equals("Any Road Type") && selectedSpeedLimit.equals("Any Speed")){
            String km = conversion.convertMetres(journey.getDistanceTravelled());
            TextView textView = (TextView) getActivity().findViewById(R.id.result_individual_distance);
            textView.setText(km + " km");
            textView.setPadding(30,0,0,0);
            return;
        }
        distanceCategory = calculateCategory(selectedRoadType, selectedSpeedLimit);
        findDistance(journey, distanceCategory);
    }

    public void showErrorMessage(String message) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
        dialogBuilder.setMessage(message);
        dialogBuilder.setPositiveButton("Ok", null);
        dialogBuilder.show();
    }


    private IndividualDistanceCategory calculateCategory(String selectedRoadType, String selectedSpeedLimit) {
        IndividualDistanceCategory distanceCategory;
        switch(selectedRoadType + "|" + selectedSpeedLimit){
            case "Urban|Any Speed":
                distanceCategory = IndividualDistanceCategory.URBAN;
                break;
            case "Rural|Any Speed":
                distanceCategory = IndividualDistanceCategory.RURAL;
                break;
            case "Motorway|Any Speed":
                distanceCategory = IndividualDistanceCategory.MOTORWAY;
                break;
            case "Any Road Type|20 km/h":
                distanceCategory = IndividualDistanceCategory.TWENTY;
                break;
            case "Any Road Type|50 km/h":
                distanceCategory = IndividualDistanceCategory.FIFTY;
                break;
            case "Any Road Type|60 km/h":
                distanceCategory = IndividualDistanceCategory.SIXTY;
                break;
            case "Any Road Type|80 km/h":
                distanceCategory = IndividualDistanceCategory.EIGHTY;
                break;
            case "Any Road Type|100 km/h":
                distanceCategory = IndividualDistanceCategory.HUNDRED;
                break;
            case "Any Road Type|Miscellaneous":
                distanceCategory = IndividualDistanceCategory.MISCELLANEOUS;
                break;
            default:
                distanceCategory = null;
                break;
        }
        return distanceCategory;
    }


    public void findDistance(Journey journey, final IndividualDistanceCategory distanceCategory){
          calculateDistance(distanceCategory);
    }

    public void calculateDistance(IndividualDistanceCategory distanceCategory){
        int result = calculateDistance(distanceCategory, journey.getJourneyStatistics());
        String km = conversion.convertMetres(result);
        TextView textView = (TextView) getActivity().findViewById(R.id.result_individual_distance);
        textView.setText(km + " km");
        textView.setPadding(30,0,0,0);
    }


}



