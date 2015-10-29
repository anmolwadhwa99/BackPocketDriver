package org.p4p.backpocketdriver.driverlog.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class ServerTest {
	
    private final static String SERVER_ADDRESS = "https://safe-driver.herokuapp.com/webapi/";
    private final static String USER_URL = "user/";
    private final static String JOURNEY_URL = "journey/";
    private final static String LOGINFO_URL = "log/";
    
    public static String HTTPGetMethod(String inputURL){

        try {

            URL url = new URL(inputURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
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




    public static String HTTPPostMethod(String inputURL){

        try {

            URL url = new URL(inputURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            JSONObject object = new JSONObject();
            
            try {
            	object.put("firstName", "Anmol");
				object.put("lastName", "Wadhwa");
	            object.put("password", "pass");
	            object.put("userName", "awad");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
            
            String input = object.toString();           
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

	public static void main(String[] args) {
		
		String output = HTTPGetMethod(SERVER_ADDRESS + USER_URL);
		System.out.println(output);
		
		JSONObject obj;
		try {

			JSONArray array = new JSONArray(output);
			JSONObject json = array.getJSONObject(0);
			String firstName = json.getString("firstName");
			System.out.println(firstName);
			
			System.out.println(array.toString());
		} catch (JSONException e) {
			e.printStackTrace();
		}
	

        
		
	}

}
