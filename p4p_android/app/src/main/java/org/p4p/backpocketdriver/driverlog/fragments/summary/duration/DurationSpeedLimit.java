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
import org.p4p.backpocketdriver.driverlog.pojo.summary.categories.SpeedLimit;
import org.p4p.backpocketdriver.driverlog.pojo.summary.sections.Duration;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class DurationSpeedLimit extends Fragment {
    private String[] speedLimits;
    private Conversion conversion;
    private SummaryStatisticsStorage summaryStatsStorage;
    private SummaryStatistics summaryStatistics;
    private Duration duration;
    private SpeedLimit speedLimit;

    public DurationSpeedLimit() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_duration_speed_limit, container, false);
        conversion = new Conversion();
        summaryStatsStorage = new SummaryStatisticsStorage(getActivity());
        summaryStatistics = summaryStatsStorage.getSummaryStatsData();
        duration = summaryStatistics.getDuration();
        speedLimit = duration.getSpeedLimit();

        if(summaryStatsStorage != null) {

            speedLimits = getResources().getStringArray(R.array.speed_limit_array);

            BarChart chart = (BarChart) view.findViewById(R.id.barchart_speedlimit_duration);
            BarData data = new BarData(getXAxisValues(), getDataSet(speedLimit));
            chart.setData(data);
            chart.animateXY(2000, 2000);
            chart.invalidate();
            chart.setDescription("");

        }
        return view;
    }

    private ArrayList<BarDataSet> getDataSet(SpeedLimit speedLimit) {

        ArrayList<BarDataSet> dataSets = null;

        ArrayList<BarEntry> valueSet1 = new ArrayList<>();
        BarEntry v1e1 = new BarEntry(speedLimit.getTwenty()/60, 0); // 20
        valueSet1.add(v1e1);
        BarEntry v1e2 = new BarEntry(speedLimit.getFifty()/60, 1); // 50
        valueSet1.add(v1e2);
        BarEntry v1e3 = new BarEntry(speedLimit.getSixty()/60, 2); // 60
        valueSet1.add(v1e3);
        BarEntry v1e4 = new BarEntry(speedLimit.getEighty()/60, 3); // 80
        valueSet1.add(v1e4);
        BarEntry v1e5 = new BarEntry(speedLimit.getHundred()/60, 4); //100
        valueSet1.add(v1e5);
        BarEntry v1e6 = new BarEntry(speedLimit.getMiscellaneous()/60, 5); //M
        valueSet1.add(v1e6);

        BarDataSet barDataSet1 = new BarDataSet(valueSet1, "Number of mins travelled");
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
