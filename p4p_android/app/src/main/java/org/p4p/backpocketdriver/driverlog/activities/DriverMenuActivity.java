package org.p4p.backpocketdriver.driverlog.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import org.p4p.backpocketdriver.driverlog.R;
import org.p4p.backpocketdriver.driverlog.pojo.storage.JourneyStatsStorage;
import org.p4p.backpocketdriver.driverlog.pojo.storage.JourneysStorage;
import org.p4p.backpocketdriver.driverlog.pojo.storage.SummaryStatisticsStorage;
import org.p4p.backpocketdriver.driverlog.pojo.storage.UserDetailsStorage;


public class DriverMenuActivity extends ActionBarActivity {
    private UserDetailsStorage userDetailsStorage;
    private JourneysStorage journeysStorage;
    private JourneyStatsStorage journeyStatsStorage;
    private SummaryStatisticsStorage summaryStatsStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_driver_menu);
        userDetailsStorage = new UserDetailsStorage(this);
        journeysStorage = new JourneysStorage(this);
        journeyStatsStorage = new JourneyStatsStorage(this);
        summaryStatsStorage = new SummaryStatisticsStorage(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_driver_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return super.onOptionsItemSelected(item);
    }

    public void openDrivingStats(View view) {
        Intent intent = new Intent(this, DrivingStatisticsActivity.class);
        startActivity(intent);
    }

    public void logout(View view){
        new AlertDialog.Builder(this)
                .setTitle("Logout")
                .setMessage("Are you sure you wish to Logout?")
                .setIcon(R.drawable.warning_sign)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        userDetailsStorage.clearUserData();
                        userDetailsStorage.setUserLoggedIn(false);
                        journeysStorage.clearJourneyData();
                        journeysStorage.setJourneySet(false);
                        journeyStatsStorage.clearJourneyData();
                        summaryStatsStorage.clearSummaryStatsData();
                        summaryStatsStorage.setSummaryStatsSet(false);

                        Intent intent = new Intent(DriverMenuActivity.this, MainActivity.class);
                        startActivity(intent);
                        Toast.makeText(getApplicationContext(), "Successfully Logged Out",
                                Toast.LENGTH_LONG).show();
                    }})
                .setNegativeButton(android.R.string.no, null).show();

    }

    public void openLogBook(View view) {
        Intent intent = new Intent(this, DriverLogActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        {
            new AlertDialog.Builder(this)
                    .setTitle("Logout")
                    .setMessage("Are you sure you wish to Logout?")
                    .setIcon(R.drawable.warning_sign)
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int whichButton) {
                            userDetailsStorage.clearUserData();
                            userDetailsStorage.setUserLoggedIn(false);
                            journeysStorage.clearJourneyData();
                            journeysStorage.setJourneySet(false);
                            summaryStatsStorage.clearSummaryStatsData();
                            summaryStatsStorage.setSummaryStatsSet(false);
                            Intent intent = new Intent(DriverMenuActivity.this, MainActivity.class);
                            startActivity(intent);
                            Toast.makeText(getApplicationContext(), "Successfully Logged Out",
                                    Toast.LENGTH_LONG).show();
                        }})
                    .setNegativeButton(android.R.string.no, null).show();

        }
    }
}
