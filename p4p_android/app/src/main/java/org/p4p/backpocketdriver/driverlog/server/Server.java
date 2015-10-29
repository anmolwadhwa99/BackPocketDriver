package org.p4p.backpocketdriver.driverlog.server;


import android.app.ProgressDialog;
import android.content.Context;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Dheeraj on 30/06/15.
 */
public class Server {

    private ProgressDialog progressDialog;
    public final static String SERVER_ADDRESS = "https://safe-driver.herokuapp.com/webapi/";
    public final static String USER_URL = "user/";
    public final static String JOURNEY_URL = "journey/";


    public Server() {
    }

    public Server(Context context){
        progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(false);
        progressDialog.setTitle("Processing...");
        progressDialog.setMessage("Please wait...");
    }



    public String HTTPGetMethod(String inputURL){

        try {

            URL url = new URL(inputURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                return null;
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output = br.readLine();

            conn.disconnect();
            return output;

        } catch (MalformedURLException e) {
            return null;

        } catch (IOException e) {
            return null;
        }

    }


    public static String HTTPPostMethod(String inputURL, JSONObject body){

        try {

            URL url = new URL(inputURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");

            String input = body.toString();

            OutputStream os = conn.getOutputStream();
            os.write(input.getBytes());
            os.flush();


            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output = br.readLine();


            conn.disconnect();
            return output;

        } catch (MalformedURLException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }
        return null;
    }

    public static String HTTPDeleteMethod(String inputURL){

        try {

            URL url = new URL(inputURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("DELETE");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));


            String output = br.readLine();

            conn.disconnect();
            return output;

        } catch (MalformedURLException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }
        return null;
    }










}






