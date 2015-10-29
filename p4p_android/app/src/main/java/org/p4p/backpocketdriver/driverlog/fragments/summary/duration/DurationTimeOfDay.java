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
import org.p4p.backpocketdriver.driverlog.pojo.summary.categories.TimeOfDay;
import org.p4p.backpocketdriver.driverlog.pojo.summary.sections.Duration;

import java.util.ArrayList;


public class DurationTimeOfDay extends Fragment {
    private String[] timeOfDays;
    private SummaryStatisticsStorage summaryStatsStorage;
    private SummaryStatistics summaryStatistics;
    private Duration duration;
    private TimeOfDay timeOfDay;
    private Conversion conversion;

    public DurationTimeOfDay() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_duration_time_of_day, container, false);
        conversion = new Conversion();
        summaryStatsStorage = new SummaryStatisticsStorage(getActivity());
        summaryStatistics = summaryStatsStorage.getSummaryStatsData();
        duration = summaryStatistics.getDuration();
        timeOfDay = duration.getTimeOfDay();

        if(summaryStatsStorage != null) {
            timeOfDays = getResources().getStringArray(R.array.time_of_day_array);
            BarChart chart = (BarChart) view.findViewById(R.id.barchart_timeofday_duration);
            BarData data = new BarData(getXAxisValues(), getDataSet(timeOfDay));
            chart.setData(data);
            chart.setDescription("");
            chart.animateXY(2000, 2000);
            chart.invalidate();
        }
        return view;
    }


    private ArrayList<BarDataSet> getDataSet(TimeOfDay timeOfDay) {

        ArrayList<BarDataSet> dataSets = null;

        ArrayList<BarEntry> valueSet1 = new ArrayList<>();
        BarEntry v1e1 = new BarEntry(timeOfDay.getDay()/60, 0); // Day
        valueSet1.add(v1e1);
        BarEntry v1e2 = new BarEntry(timeOfDay.getNight()/60, 1); // Night
        valueSet1.add(v1e2);


        BarDataSet barDataSet1 = new BarDataSet(valueSet1, "Number of mins travelled");
        barDataSet1.setColors(ColorTemplate.COLORFUL_COLORS);


        dataSets = new ArrayList<>();
        dataSets.add(barDataSet1);
        return dataSets;
    }

    private ArrayList<String> getXAxisValues() {
        ArrayList<String> xAxis = new ArrayList<>();
        xAxis.add(timeOfDays[1]);
        xAxis.add(timeOfDays[2]);


        return xAxis;
    }


}
