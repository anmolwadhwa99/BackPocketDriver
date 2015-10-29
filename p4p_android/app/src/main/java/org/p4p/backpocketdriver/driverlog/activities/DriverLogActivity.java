package org.p4p.backpocketdriver.driverlog.activities;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;

import org.p4p.backpocketdriver.driverlog.R;
import org.p4p.backpocketdriver.driverlog.adapter.Header;
import org.p4p.backpocketdriver.driverlog.adapter.Item;
import org.p4p.backpocketdriver.driverlog.adapter.ListItem;
import org.p4p.backpocketdriver.driverlog.adapter.RowAdapter;
import org.p4p.backpocketdriver.driverlog.interfaces.GetAllJourneysCallback;
import org.p4p.backpocketdriver.driverlog.models.Journey;
import org.p4p.backpocketdriver.driverlog.models.User;
import org.p4p.backpocketdriver.driverlog.pojo.storage.JourneysStorage;
import org.p4p.backpocketdriver.driverlog.pojo.storage.UserDetailsStorage;
import org.p4p.backpocketdriver.driverlog.server.JourneyRequests;

import java.util.ArrayList;
import java.util.List;

public class DriverLogActivity extends ActionBarActivity {
    private UserDetailsStorage currentUser;
    private JourneysStorage journeysStorage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_log);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#0099FF")));
        actionBar.setDisplayHomeAsUpEnabled(true);

        currentUser = new UserDetailsStorage(this);
        journeysStorage = new JourneysStorage(this);

        loadJourneys(currentUser.getLoggedInUser());

        ListView driverLog  = (ListView) findViewById(R.id.journey_list_view);


        driverLog.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                ListItem listItem = (ListItem) adapterView.getItemAtPosition(position);
                Journey journey = listItem.getJourney();

                Intent intent = new Intent(DriverLogActivity.this, JourneyDetailsActivity.class);
                intent.putExtra("journey", new Gson().toJson(journey));
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_driver_log, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.


        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, DriverMenuActivity.class);
        startActivity(intent);
    }

    public void loadJourneys(User user) {

        if (journeysStorage.getJourneySet() == true) {
            List<Journey> journeys = journeysStorage.getJourneyData();
            List<Item> items = new ArrayList<Item>();
            ListView driverLog = (ListView) findViewById(R.id.journey_list_view);
            items.add(new Header("Journeys"));
            for (Journey j : journeys) {
                items.add(new ListItem(j));
            }
            RowAdapter adapter = new RowAdapter(DriverLogActivity.this, items);
            driverLog.setAdapter(adapter);

        }
        else {
            JourneyRequests journeyRequests = new JourneyRequests(this, "Generating Journey Data");
            journeyRequests.getAllUserJourneyDataInBackground(user, new GetAllJourneysCallback() {
                @Override
                public void done(List<Journey> returnedResult) {
                    List<Item> items = new ArrayList<Item>();
                    ListView driverLog = (ListView) findViewById(R.id.journey_list_view);
                    items.add(new Header("Journeys"));
                    for (Journey j : returnedResult) {
                        items.add(new ListItem(j));
                    }

                    RowAdapter adapter = new RowAdapter(DriverLogActivity.this, items);
                    driverLog.setAdapter(adapter);
                    journeysStorage.storeJourneysData(returnedResult);
                    journeysStorage.setJourneySet(true);

                }
            });

        }
    }
}
