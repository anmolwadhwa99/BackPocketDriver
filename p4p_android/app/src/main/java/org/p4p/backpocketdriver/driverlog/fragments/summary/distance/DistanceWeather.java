package org.p4p.backpocketdriver.driverlog.fragments.summary.distance;


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
import org.p4p.backpocketdriver.driverlog.pojo.summary.categories.Weather;
import org.p4p.backpocketdriver.driverlog.pojo.summary.sections.Distance;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class DistanceWeather extends Fragment {
    private String[] weathers;
    private SummaryStatisticsStorage summaryStatsStorage;
    private SummaryStatistics summaryStatistics;
    private Distance distance;
    private Weather weather;
    private Conversion conversion;

    public DistanceWeather() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_distance_weather, container, false);

        conversion = new Conversion();
        summaryStatsStorage = new SummaryStatisticsStorage(getActivity());
        summaryStatistics = summaryStatsStorage.getSummaryStatsData();
        distance = summaryStatistics.getDistance();
        weather = distance.getWeather();

        if(summaryStatsStorage != null) {

            weathers = getResources().getStringArray(R.array.weather_array);

            BarChart chart = (BarChart) view.findViewById(R.id.barchart_weather_distance);
            BarData data = new BarData(getXAxisValues(), getDataSet(weather));
            chart.setData(data);
            chart.animateXY(2000, 2000);
            chart.invalidate();
            chart.setDescription("");
        }

        return view;
    }

    private ArrayList<BarDataSet> getDataSet(Weather weather) {

        ArrayList<BarDataSet> dataSets = null;

        ArrayList<BarEntry> valueSet1 = new ArrayList<>();
        BarEntry v1e1 = new BarEntry(conversion.convertIntToFloat(weather.getFine()), 0); // Fine
        valueSet1.add(v1e1);
        BarEntry v1e2 = new BarEntry(conversion.convertIntToFloat(weather.getRain()), 1); // Rain
        valueSet1.add(v1e2);
        BarEntry v1e3 = new BarEntry(conversion.convertIntToFloat(weather.getDrizzle()), 2); // Drizzle
        valueSet1.add(v1e3);
        BarEntry v1e4 = new BarEntry(conversion.convertIntToFloat(weather.getFog()), 3); // Fog
        valueSet1.add(v1e4);
        BarEntry v1e5 = new BarEntry(conversion.convertIntToFloat(weather.getOther()), 4); //Other
        valueSet1.add(v1e5);

        BarDataSet barDataSet1 = new BarDataSet(valueSet1, "Distance travelled in km");
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
