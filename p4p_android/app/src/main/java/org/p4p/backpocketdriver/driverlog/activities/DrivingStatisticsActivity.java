package org.p4p.backpocketdriver.driverlog.activities;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import org.p4p.backpocketdriver.driverlog.R;
import org.p4p.backpocketdriver.driverlog.fragments.totalstatistics.AverageSpeedFragment;
import org.p4p.backpocketdriver.driverlog.fragments.totalstatistics.DistanceFragment;
import org.p4p.backpocketdriver.driverlog.fragments.totalstatistics.DurationFragment;
import org.p4p.backpocketdriver.driverlog.interfaces.GetAllJourneysCallback;
import org.p4p.backpocketdriver.driverlog.interfaces.GetAllStatsCallback;
import org.p4p.backpocketdriver.driverlog.models.Journey;
import org.p4p.backpocketdriver.driverlog.models.JourneyStatistics;
import org.p4p.backpocketdriver.driverlog.pojo.storage.JourneyStatsStorage;
import org.p4p.backpocketdriver.driverlog.pojo.storage.SummaryStatisticsStorage;
import org.p4p.backpocketdriver.driverlog.pojo.storage.UserDetailsStorage;
import org.p4p.backpocketdriver.driverlog.pojo.summary.LoadSummaryData;
import org.p4p.backpocketdriver.driverlog.pojo.summary.SummaryStatistics;
import org.p4p.backpocketdriver.driverlog.server.JourneyRequests;

import java.util.List;
import java.util.Locale;

/**
 * Created on 4/07/2015
 * by Anmol
 */
public class DrivingStatisticsActivity extends ActionBarActivity implements ActionBar.TabListener {
    private List<JourneyStatistics> journeyStatistics;
    private List<Journey> journeys;
    private UserDetailsStorage userDetailsStorage;
    private SummaryStatisticsStorage summaryStatsStorage;
    private JourneyStatsStorage journeyStatsStorage;
    private boolean hasFinished = false;
    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driving_statistics);

        // Set up the action bar.
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#0099FF")));
        actionBar.setStackedBackgroundDrawable(new ColorDrawable(Color.parseColor("#0099FF")));
        actionBar.setDisplayHomeAsUpEnabled(true);

        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        // When swiping between different sections, select the corresponding
        // tab. We can also use ActionBar.Tab#select() to do this if we have
        // a reference to the Tab.
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
            }
        });

        // For each of the sections in the app, add a tab to the action bar.
        for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
            // Create a tab with text corresponding to the page title defined by
            // the adapter. Also specify this Activity object, which implements
            // the TabListener interface, as the callback (listener) for when
            // this tab is selected.
            actionBar.addTab(
                    actionBar.newTab()
                            .setText(mSectionsPagerAdapter.getPageTitle(i))
                            .setTabListener(this));
        }

        //here...
        userDetailsStorage = new UserDetailsStorage(this);
        summaryStatsStorage = new SummaryStatisticsStorage(this);
        journeyStatsStorage = new JourneyStatsStorage(this);

        if (summaryStatsStorage.getSummaryStatsSet() == false){
            getStatsDataFromServer();
        }

    }

    public void getStatsDataFromServer(){
        JourneyRequests journeyRequests = new JourneyRequests(this, "Generating Driving Statistics");
        journeyRequests.getAllUserStatsDataInBackground(userDetailsStorage.getLoggedInUser(), new GetAllStatsCallback() {
            @Override
            public void done(List<JourneyStatistics> returnedResult) {
                journeyStatistics = returnedResult;
                journeyStatsStorage.storeJourneysData(returnedResult);

            }
        });

        journeyRequests = new JourneyRequests(this, "Generating Driving Statistics");
        journeyRequests.getAllUserJourneyDataInBackground(userDetailsStorage.getLoggedInUser(), new GetAllJourneysCallback()
        {
            @Override
            public void done(List<Journey> returnedResult) {
                journeys = returnedResult;
                LoadSummaryData loadSummaryData = new LoadSummaryData(journeyStatistics, journeys);
                SummaryStatistics summaryStatistics = loadSummaryData.setData();
                summaryStatsStorage.storeSummaryStatsData(summaryStatistics);
                summaryStatsStorage.setSummaryStatsSet(true);


            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_driving_statistics, menu);
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
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        // When the given tab is selected, switch to the corresponding page in
        // the ViewPager.
        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            switch (position){
                case 0:
                    return new DurationFragment();
                case 1:
                    return new DistanceFragment();
                case 2:
                    return new AverageSpeedFragment();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            switch (position) {
                case 0:
                    return getString(R.string.duration_fragment).toUpperCase(l);
                case 1:
                    return getString(R.string.distance_fragment).toUpperCase(l);
                case 2:
                    return getString(R.string.average_speed_fragment).toUpperCase(l);
            }
            return null;
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_driving_statistics, container, false);
            return rootView;
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, DriverMenuActivity.class);
        startActivity(intent);
    }



}
