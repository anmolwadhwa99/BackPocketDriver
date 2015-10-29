package org.p4p.backpocketdriver.driverlog.fragments.summary.averagespeed;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.p4p.backpocketdriver.driverlog.R;
import org.p4p.backpocketdriver.driverlog.pojo.Conversion;
import org.p4p.backpocketdriver.driverlog.pojo.storage.SummaryStatisticsStorage;
import org.p4p.backpocketdriver.driverlog.pojo.summary.SummaryStatistics;
import org.p4p.backpocketdriver.driverlog.pojo.summary.categories.RoadType;
import org.p4p.backpocketdriver.driverlog.pojo.summary.sections.AverageSpeed;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class AverageSpeedRoadType extends Fragment {
    private AverageSpeed averageSpeed = new AverageSpeed();
    private String[] roadTypes;
    private SummaryStatistics summaryStatistics;
    private SummaryStatisticsStorage summaryStatsStorage;
    private RoadType roadType;
    private Conversion conversion;

    public AverageSpeedRoadType() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        conversion = new Conversion();
        roadTypes = getResources().getStringArray(R.array.road_type_array);
        summaryStatsStorage = new SummaryStatisticsStorage(getActivity());
        summaryStatistics = summaryStatsStorage.getSummaryStatsData();
        averageSpeed = summaryStatistics.getAverageSpeed();
        roadType = averageSpeed.getRoadType();

        View view = inflater.inflate(R.layout.fragment_averagespeed_roadtype, container, false);
        BarChart chart = (BarChart) view.findViewById(R.id.barchart_averagespeed_roadtype);

        if(summaryStatistics != null) {

            BarData data = new BarData(getXAxisValues(), getDataSet(roadType));
            chart.setData(data);
            chart.setDescription("");
            chart.animateXY(2000, 2000);
            chart.invalidate();
        }

        return view;
    }


    private ArrayList<BarDataSet> getDataSet(RoadType roadType) {

        ArrayList<BarDataSet> dataSets = null;

        float urban = roadType.getUrban()/1000;
        float rural = roadType.getRural()/1000;
        float motorway = roadType.getMotorway()/1000;

        ArrayList<BarEntry> valueSet1 = new ArrayList<>();
        BarEntry v1e1 = new BarEntry(roadType.getUrban(), 0); // Urban
        valueSet1.add(v1e1);
        BarEntry v1e2 = new BarEntry(roadType.getRural(), 1); // Rural
        valueSet1.add(v1e2);
        BarEntry v1e3 = new BarEntry(roadType.getMotorway(), 2); // Motorway
        valueSet1.add(v1e3);


        BarDataSet barDataSet1 = new BarDataSet(valueSet1, "The average speed in km/h");
        barDataSet1.setColors(ColorTemplate.COLORFUL_COLORS);


        dataSets = new ArrayList<>();
        dataSets.add(barDataSet1);
        return dataSets;
    }

    private ArrayList<String> getXAxisValues() {
        ArrayList<String> xAxis = new ArrayList<>();
        xAxis.add(roadTypes[1]);
        xAxis.add(roadTypes[2]);
        xAxis.add(roadTypes[3]);

        return xAxis;
    }


}
