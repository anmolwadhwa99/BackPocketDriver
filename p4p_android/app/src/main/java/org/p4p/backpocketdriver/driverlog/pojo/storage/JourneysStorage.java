package org.p4p.backpocketdriver.driverlog.pojo.storage;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.p4p.backpocketdriver.driverlog.models.Journey;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by Dheeraj on 23/07/15.
 */
public class JourneysStorage {

    public static final String SP_NAME = "journeys";

    SharedPreferences journeysLocalDatabase;

    public JourneysStorage(Context context) {
        journeysLocalDatabase = context.getSharedPreferences(SP_NAME, 0);
    }

    public void storeJourneysData(List<Journey> journeys) {
        SharedPreferences.Editor journeyLocalDatabaseEditor = journeysLocalDatabase.edit();
        String json = new Gson().toJson(journeys);
        journeyLocalDatabaseEditor.putString("journeys", json);
        journeyLocalDatabaseEditor.commit();
    }

    public void setJourneySet(boolean journeySet) {
        SharedPreferences.Editor journeyLocalDatabaseEditor = journeysLocalDatabase.edit();
        journeyLocalDatabaseEditor.putBoolean("journeySet", journeySet);
        journeyLocalDatabaseEditor.commit();
    }

    public boolean getJourneySet() {
        return journeysLocalDatabase.getBoolean("journeySet", false);

    }

    public void clearJourneyData() {
        SharedPreferences.Editor journeyLocalDatabaseEditor = journeysLocalDatabase.edit();
        journeyLocalDatabaseEditor.clear();
        journeyLocalDatabaseEditor.commit();
    }

    public List<Journey> getJourneyData() {

        Type type = new TypeToken<List<Journey>>(){}.getType();
        List<Journey> journeyList = new Gson().fromJson(journeysLocalDatabase.getString("journeys", ""), type);
        for (int i=0;i<journeyList.size();i++) {
            Journey x = journeyList.get(i);
            System.out.println(x);
        }

        return journeyList;

    }
}
