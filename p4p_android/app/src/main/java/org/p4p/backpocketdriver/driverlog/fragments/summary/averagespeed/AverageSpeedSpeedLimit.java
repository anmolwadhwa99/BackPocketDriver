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
import org.p4p.backpocketdriver.driverlog.pojo.summary.categories.SpeedLimit;
import org.p4p.backpocketdriver.driverlog.pojo.summary.sections.AverageSpeed;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class AverageSpeedSpeedLimit extends Fragment {

    private SummaryStatistics summaryStatistics;
    private SummaryStatisticsStorage summaryStatsStorage;
    private SpeedLimit speedLimit;
    private Conversion conversion;
    private AverageSpeed averageSpeed;

    public AverageSpeedSpeedLimit() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_averagespeed_speedlimit, container, false);
        conversion = new Conversion();
        summaryStatsStorage = new SummaryStatisticsStorage(getActivity());
        summaryStatistics = summaryStatsStorage.getSummaryStatsData();
        averageSpeed = summaryStatistics.getAverageSpeed();
        speedLimit = averageSpeed.getSpeedLimit();

        if(summaryStatsStorage != null) {

            BarChart chart = (BarChart) view.findViewById(R.id.barchart_averagespeed_speedlimit);

            BarData data = new BarData(getXAxisValues(), getDataSet(speedLimit));
            chart.setData(data);
            chart.setDescription("");
            chart.animateXY(2000, 2000);
            chart.invalidate();
        }
        return view;
    }

    private ArrayList<BarDataSet> getDataSet(SpeedLimit speedLimit) {

        ArrayList<BarDataSet> dataSets = null;

        ArrayList<BarEntry> valueSet1 = new ArrayList<>();
        BarEntry v1e1 = new BarEntry(speedLimit.getTwenty(), 0); // 20
        valueSet1.add(v1e1);
        BarEntry v1e2 = new BarEntry(speedLimit.getFifty(), 1); // 50
        valueSet1.add(v1e2);
        BarEntry v1e3 = new BarEntry(speedLimit.getSixty(), 2); // 60
        valueSet1.add(v1e3);
        BarEntry v1e4 = new BarEntry(speedLimit.getEighty(), 3); // 80
        valueSet1.add(v1e4);
        BarEntry v1e5 = new BarEntry(speedLimit.getHundred(), 4); //100
        valueSet1.add(v1e5);
        BarEntry v1e6 = new BarEntry(speedLimit.getMiscellaneous(), 5); //M
        valueSet1.add(v1e6);


        BarDataSet barDataSet1 = new BarDataSet(valueSet1, "The average speed in km/h");
        barDataSet1.setColors(ColorTemplate.COLORFUL_COLORS);

        dataSets = new ArrayList<>();
        dataSets.add(barDataSet1);
        return dataSets;
    }

    private ArrayList<String> getXAxisValues() {
        ArrayList<String> xAxis = new ArrayList<>();
        xAxis.add("20");
        xAxis.add("50");
        xAxis.add("60");
        xAxis.add("80");
        xAxis.add("100");
        xAxis.add("Miscellaneous");
        return xAxis;
    }


}
