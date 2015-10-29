package org.p4p.backpocketdriver.driverlog.google_maps;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import org.p4p.backpocketdriver.driverlog.R;

public class MapsActivity extends ActionBarActivity {

    private GoogleMap googleMap; // Might be null if Google Play services APK is not available.
    private double originLatitude;
    private double originLongitude;
    private double destLatitude;
    private double destLongitude;


    private String[] coordinatesArray;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        Intent i = getIntent();
        Bundle bundle = i.getExtras();
        final ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        String coordinates = "";

        if(bundle != null){
            coordinates = bundle.getString("coordinates");
        }


        coordinatesArray = convertToArray(coordinates);
        String originCoordinates = coordinatesArray[0];
        String destCoordinates = coordinatesArray[coordinatesArray.length -1];

        originLatitude = Double.parseDouble(originCoordinates.split(",")[0]);
        originLongitude = Double.parseDouble(originCoordinates.split(",")[1]);

        destLatitude = Double.parseDouble(destCoordinates.split(",")[0]);
        destLongitude = Double.parseDouble(destCoordinates.split(",")[1]);

        try {
            initializeMap();
            addMarkers();
            addRoute();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private String[] convertToArray(String coordinates){
        String[] coordinatesArray = coordinates.split("\\|");
        return coordinatesArray;
    }


    private void initializeMap(){
        if(googleMap == null){
            googleMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();

            //check if map is created successfully or not
            if(googleMap == null){
                Toast.makeText(this, "Sorry was unable to create maps !", Toast.LENGTH_SHORT).show();
            }
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(originLatitude,originLongitude), 11.0f));
        }
    }

    private void addMarkers(){
        MarkerOptions originMarker = new MarkerOptions().position(new LatLng(originLatitude, originLongitude)).title("Origin");
        originMarker.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        googleMap.addMarker(originMarker);

        MarkerOptions destMarker = new MarkerOptions().position(new LatLng(destLatitude, destLongitude)).title("Destination");
        destMarker.icon((BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
        googleMap.addMarker(destMarker);
    }

    private void addRoute(){
        if(googleMap == null){
            return;
        }
        if(coordinatesArray.length < 2){
            return;
        }

        PolylineOptions options = new PolylineOptions();
        options.color(Color.parseColor("#0099FF"));
        options.width(8);
        options.visible(true);

        for(int i=0; i<coordinatesArray.length; i++){
            Double latitude = Double.parseDouble(coordinatesArray[i].split(",")[0]);
            Double longitude = Double.parseDouble(coordinatesArray[i].split(",")[1]);
            options.add(new LatLng(latitude,longitude));

        }

        googleMap.addPolyline(options);
    }

    @Override
    protected void onResume(){
        super.onResume();
        initializeMap();
        addMarkers();
        addRoute();
    }

}
