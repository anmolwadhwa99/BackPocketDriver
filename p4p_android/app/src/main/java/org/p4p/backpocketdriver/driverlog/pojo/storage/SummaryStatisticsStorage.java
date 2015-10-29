package org.p4p.backpocketdriver.driverlog.pojo.storage;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import org.p4p.backpocketdriver.driverlog.pojo.summary.SummaryStatistics;

/**
 * Created by Dheeraj on 25/07/15.
 */
public class SummaryStatisticsStorage {
    public static final String SP_NAME = "summaryStatistics";

    private String SUMMARY_STATS_DATA = "summaryStatisticsData";
    private String CHECK_STATS = "summaryStatsCheck";

    SharedPreferences summaryStatsLocalDatabase;

    public SummaryStatisticsStorage(Context context) {
        summaryStatsLocalDatabase = context.getSharedPreferences(SP_NAME, 0);
    }

    public void setSummaryStatsSet(boolean summaryStatsSet) {
        SharedPreferences.Editor summaryStatsLocalDatabaseEditor = summaryStatsLocalDatabase.edit();
        summaryStatsLocalDatabaseEditor.putBoolean(CHECK_STATS, summaryStatsSet);
        summaryStatsLocalDatabaseEditor.commit();
    }

    public boolean getSummaryStatsSet(){
        return summaryStatsLocalDatabase.getBoolean(CHECK_STATS, false);
    }

    public void clearSummaryStatsData() {
        SharedPreferences.Editor summaryStatsLocalDatabaseEditor = summaryStatsLocalDatabase.edit();
        summaryStatsLocalDatabaseEditor.clear();
        summaryStatsLocalDatabaseEditor.commit();
    }

    public void storeSummaryStatsData(SummaryStatistics summaryStatistics){
        SharedPreferences.Editor summaryStatsLocalDatabaseEditor = summaryStatsLocalDatabase.edit();
        String json = new Gson().toJson(summaryStatistics);
        summaryStatsLocalDatabaseEditor.putString(SUMMARY_STATS_DATA, json);
        summaryStatsLocalDatabaseEditor.commit();
    }

    public SummaryStatistics getSummaryStatsData(){
        Gson gson = new Gson();
        String json = summaryStatsLocalDatabase.getString(SUMMARY_STATS_DATA, "");
        SummaryStatistics summaryStatistics = gson.fromJson(json, SummaryStatistics.class);

        return summaryStatistics;
    }

}
