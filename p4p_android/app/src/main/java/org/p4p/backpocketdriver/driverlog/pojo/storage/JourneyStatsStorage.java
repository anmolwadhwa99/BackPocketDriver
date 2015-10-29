package org.p4p.backpocketdriver.driverlog.pojo.storage;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.p4p.backpocketdriver.driverlog.models.JourneyStatistics;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by Dheeraj on 28/07/15.
 */
public class JourneyStatsStorage {

    public static final String SP_NAME = "journeyStats";

    SharedPreferences journeyStatsLocalDatabase;

    public JourneyStatsStorage(Context context) {
        journeyStatsLocalDatabase = context.getSharedPreferences(SP_NAME, 0);
    }

    public void storeJourneysData(List<JourneyStatistics> journeyStats) {
        SharedPreferences.Editor journeyStatsLocalDatabaseEditor = journeyStatsLocalDatabase.edit();
        String json = new Gson().toJson(journeyStats);
        journeyStatsLocalDatabaseEditor.putString("journeyStats", json);
        journeyStatsLocalDatabaseEditor.commit();
    }

    public void setJourneyStatsSet(boolean journeyStatsSet) {
        SharedPreferences.Editor journeyStatsLocalDatabaseEditor = journeyStatsLocalDatabase.edit();
        journeyStatsLocalDatabaseEditor.putBoolean("journeyStatsSet", journeyStatsSet);
        journeyStatsLocalDatabaseEditor.commit();
    }

    public boolean getJourneyStatsSet() {
        return journeyStatsLocalDatabase.getBoolean("journeyStatsSet", false);

    }

    public void clearJourneyData() {
        SharedPreferences.Editor journeyStatsLocalDatabaseEditor = journeyStatsLocalDatabase.edit();
        journeyStatsLocalDatabaseEditor.clear();
        journeyStatsLocalDatabaseEditor.commit();
    }

    public List<JourneyStatistics> getJourneyData() {

        Type type = new TypeToken<List<JourneyStatistics>>(){}.getType();
        List<JourneyStatistics> journeyStatsList = new Gson().fromJson(journeyStatsLocalDatabase.getString("journeyStats", ""), type);
        for (int i=0;i<journeyStatsList.size();i++) {
            JourneyStatistics x = journeyStatsList.get(i);
            System.out.println(x);
        }

        return journeyStatsList;

    }
}
