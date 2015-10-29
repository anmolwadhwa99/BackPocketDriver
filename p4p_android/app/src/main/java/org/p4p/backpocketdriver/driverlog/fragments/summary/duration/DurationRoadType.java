package org.p4p.backpocketdriver.driverlog.fragments.summary.duration;


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
import org.p4p.backpocketdriver.driverlog.pojo.summary.sections.Duration;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class DurationRoadType extends Fragment {
    private String[] roadTypes;
    private Duration duration;
    private RoadType roadType;
    private SummaryStatisticsStorage summaryStatsStorage;
    private SummaryStatistics summaryStatistics;
    private Conversion conversion;

    public DurationRoadType() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_duration_road_type, container, false);
        conversion = new Conversion();
        summaryStatsStorage = new SummaryStatisticsStorage(getActivity());
        summaryStatistics = summaryStatsStorage.getSummaryStatsData();
        duration = summaryStatistics.getDuration();
        roadType = duration.getRoadType();


        if(summaryStatsStorage != null) {

            roadTypes = getResources().getStringArray(R.array.road_type_array);
            BarChart chart = (BarChart) view.findViewById(R.id.barchart_roadtype_duration);
            BarData data = new BarData(getXAxisValues(), getDataSet(roadType));
            chart.setData(data);
            chart.animateXY(2000, 2000);
            chart.invalidate();
            chart.setDescription("");
        }
        return view;
    }

    private ArrayList<BarDataSet> getDataSet(RoadType roadType) {

        ArrayList<BarDataSet> dataSets = null;

        ArrayList<BarEntry> valueSet1 = new ArrayList<>();
        BarEntry v1e1 = new BarEntry(roadType.getUrban()/60, 0); // Urban
        valueSet1.add(v1e1);
        BarEntry v1e2 = new BarEntry(roadType.getRural()/60, 1); // Rural
        valueSet1.add(v1e2);
        BarEntry v1e3 = new BarEntry(roadType.getMotorway()/60, 2); // Motorway
        valueSet1.add(v1e3);


        BarDataSet barDataSet1 = new BarDataSet(valueSet1, "Number of mins travelled");
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
