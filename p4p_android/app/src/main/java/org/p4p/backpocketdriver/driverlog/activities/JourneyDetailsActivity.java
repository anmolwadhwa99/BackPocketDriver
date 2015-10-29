package org.p4p.backpocketdriver.driverlog.activities;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;

import org.p4p.backpocketdriver.driverlog.R;
import org.p4p.backpocketdriver.driverlog.google_maps.MapsActivity;
import org.p4p.backpocketdriver.driverlog.models.Journey;
import org.p4p.backpocketdriver.driverlog.pojo.Conversion;


public class JourneyDetailsActivity extends ActionBarActivity implements View.OnClickListener{
    private TextView date;
    private TextView distance;
    private TextView duration;
    private TextView weather;
    private Button details;
    private String coordinates;
    private TextView journeyName;
    private TextView journeyActivityTitle;



    Journey journey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journey_details);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#0099FF")));
        actionBar.setDisplayHomeAsUpEnabled(true);
        Conversion conversion = new Conversion();

        String jsonJourney = "";
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            jsonJourney = extras.getString("journey");
        }

        journey = new Gson().fromJson(jsonJourney, Journey.class);
        coordinates = journey.getCoordinates();

        String totalDistance = conversion.convertMetres(journey.getDistanceTravelled());
        String totalDuration = conversion.convertSeconds(journey.getDuration());

        journeyActivityTitle = (TextView) findViewById(R.id.journey_activity_title);
        journeyActivityTitle.setText(getString(R.string.trip_overview));
        journeyActivityTitle.setGravity(Gravity.CENTER_HORIZONTAL);

        journeyName = (TextView) findViewById(R.id.journey_name);
        journeyName.setText(journey.getName());
        journeyName.setGravity(Gravity.CENTER_HORIZONTAL);

        date = (TextView) findViewById(R.id.date_journey_details);
        date.setText(journey.getDate());

        distance = (TextView) findViewById(R.id.distance_journey_details);
        distance.setText(totalDistance + " km");

        duration = (TextView) findViewById(R.id.duration_journey_details);
        duration.setText(totalDuration);

        weather = (TextView) findViewById(R.id.weather_journey_details);
        weather.setText(conversion.toDisplayCase(journey.getWeather()));

        details = (Button) findViewById(R.id.view_journey_stats);
        details.setOnClickListener(this);

    }

    @Override
    public void onClick(View view){
        switch(view.getId()){
            case R.id.view_journey_stats:
                Intent intent = new Intent(this, IndividualJourneyStatisticsActivity.class);
                intent.putExtra("journey", new Gson().toJson(journey));
                startActivity(intent);
        }
    }

    public void loadMap(View v){
        Intent intent = new Intent(this, MapsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("coordinates",coordinates);
        intent.putExtras(bundle);
        startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_journey_details, menu);
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
        Intent intent = new Intent(this, DriverLogActivity.class);
        startActivity(intent);
    }





}
