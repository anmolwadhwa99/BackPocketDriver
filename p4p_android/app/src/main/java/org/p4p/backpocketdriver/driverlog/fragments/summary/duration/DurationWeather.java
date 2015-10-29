package org.p4p.backpocketdriver.driverlog.fragments.summary.duration;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.p4p.backpocketdriver.driverlog.R;
import org.p4p.backpocketdriver.driverlog.pojo.Conversion;
import org.p4p.backpocketdriver.driverlog.pojo.storage.SummaryStatisticsStorage;
import org.p4p.backpocketdriver.driverlog.pojo.summary.SummaryStatistics;
import org.p4p.backpocketdriver.driverlog.pojo.summary.categories.Weather;
import org.p4p.backpocketdriver.driverlog.pojo.summary.sections.Duration;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class DurationWeather extends Fragment {
    private String[] weathers;
    private Conversion conversion;
    private SummaryStatisticsStorage summaryStatsStorage;
    private SummaryStatistics summaryStatistics;
    private Duration duration;
    private Weather weather;

    public DurationWeather() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_duration_weather, container, false);
        conversion = new Conversion();
        summaryStatsStorage = new SummaryStatisticsStorage(getActivity());
        summaryStatistics = summaryStatsStorage.getSummaryStatsData();
        duration = summaryStatistics.getDuration();
        weather = duration.getWeather();

        if(summaryStatsStorage != null) {
            weathers = getResources().getStringArray(R.array.weather_array);

            BarChart chart = (BarChart) view.findViewById(R.id.barchart_weather_duration);
            BarData data = new BarData(getXAxisValues(), getDataSet(weather));
            XAxis xAxis = chart.getXAxis();
            chart.setData(data);
            chart.setDescription("");
            chart.animateXY(2000, 2000);
            chart.invalidate();
        }
        return view;
    }

    private ArrayList<BarDataSet> getDataSet(Weather weather) {

        ArrayList<BarDataSet> dataSets = null;

        ArrayList<BarEntry> valueSet1 = new ArrayList<>();
        BarEntry v1e1 = new BarEntry(weather.getFine()/60, 0); // Fine
        valueSet1.add(v1e1);
        BarEntry v1e2 = new BarEntry(weather.getRain()/60, 1); // Rain
        valueSet1.add(v1e2);
        BarEntry v1e3 = new BarEntry(weather.getDrizzle()/60, 2); // Drizzle
        valueSet1.add(v1e3);
        BarEntry v1e4 = new BarEntry(weather.getFog()/60, 3); // Fog
        valueSet1.add(v1e4);
        BarEntry v1e5 = new BarEntry(weather.getOther()/60, 4); //Other
        valueSet1.add(v1e5);

        BarDataSet barDataSet1 = new BarDataSet(valueSet1, "Number of mins travelled");
        barDataSet1.setColors(ColorTemplate.COLORFUL_COLORS);


        dataSets = new ArrayList<>();
        dataSets.add(barDataSet1);
        return dataSets;
    }

    private ArrayList<String> getXAxisValues() {
        ArrayList<String> xAxis = new ArrayList<>();
        xAxis.add(weathers[1]);
        xAxis.add(weathers[2]);
        xAxis.add(weathers[3]);
        xAxis.add(weathers[4]);
        xAxis.add(weathers[5]);


        return xAxis;
    }


}
