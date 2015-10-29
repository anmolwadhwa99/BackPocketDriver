package org.p4p.backpocketdriver.driverlog.webservice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;


import javax.net.ssl.HttpsURLConnection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.p4p.backpocketdriver.driverlog.model.JourneyClassifications;



public class TomTomService {

	private StringBuilder roadUrlStringBuilder = new StringBuilder();
	private StringBuilder  speedUrlStringBuilder = new StringBuilder();
	private final String keyOne = "AIzaSyDg3oVD_rZ6NhGN1PkbDvXHK76AVu3H35A"; //dhee second account
	private final String keyTwo = "AIzaSyBNXaJkWVEDP92aChJ52OiH8bIzAOJJp-s"; //dhee first account
	private final String keyThree = "AIzaSyDvZORMlQd-QzVDLb3uEYUIF_j1c9wSFS8"; //anmol first account

	//	private String routeUrl = "-36.906783,174.896642:-36.916809,174.830958:-36.923529,174.748865";

	private static final String FLOW_SEGMENT = "flowSegmentData";
	private static final String FRC = "frc";


	//TODO: Need to classify the road types we can get 
	private static final String ROAD_CODE_ZERO = "FRC0";
	private static final String ROAD_CODE_ONE = "FRC1";
	private static final String ROAD_CODE_TWO = "FRC2";
	private static final String ROAD_CODE_THREE = "FRC3";
	private static final String ROAD_CODE_FOUR = "FRC4";
	private static final String ROAD_CODE_FIVE = "FRC5";
	private static final String ROAD_CODE_SIX = "FRC6";

	

	/**
	 * TODO: Need to do more error handling.
	 * This method uses the TomTom web service to get a description of the road  
	 * @param coordinates - Longitude and latitude coordinates of the road of interest. 
	 * @return response - A description of the type of road
	 */
	public JourneyClassifications.RoadTypeClassification getRoadTypeFromService(String coordinates){
		JourneyClassifications.RoadTypeClassification response = null;
		try {

			roadUrlStringBuilder.append("https://api.tomtom.com/lbs/services/flowSegmentData/3/absolute/10/json?key=v9dmxqkhmve3nrpg6f5nbhar&point=");
			//strip all whitespace from the string
			coordinates = coordinates.replaceAll("\\s", "");
			roadUrlStringBuilder.append(coordinates);

			String roadTypeUrl = roadUrlStringBuilder.toString();

			URL roadUrl = new URL(roadTypeUrl);
			HttpsURLConnection conn = (HttpsURLConnection) roadUrl.openConnection();
			conn.setRequestMethod("GET");

			if(conn.getResponseCode() != 200){
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String result = br.readLine();
			JSONObject jsonResponse = new JSONObject(result);
			JSONObject flowObj = jsonResponse.getJSONObject(FLOW_SEGMENT);
			String roadType = flowObj.getString(FRC);

			response = returnRoadTypeString(roadType);

			roadUrlStringBuilder.setLength(0);

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}



		return response;	
	}

	/**
	 * This method is a helper method to getRoadTypeFromService()
	 * @param roadTypeCode - The code that is returned from the TomTom web service
	 * @return roadType - A description of the road
	 */
	private JourneyClassifications.RoadTypeClassification returnRoadTypeString(String roadTypeCode){

		if(roadTypeCode.equals(ROAD_CODE_ZERO)){
			return JourneyClassifications.RoadTypeClassification.MOTORWAY;
		}
		else if(roadTypeCode.equals(ROAD_CODE_ONE)){
			return JourneyClassifications.RoadTypeClassification.URBAN;
		}
		else if(roadTypeCode.equals(ROAD_CODE_TWO)){
			return JourneyClassifications.RoadTypeClassification.URBAN;
		}
		else if(roadTypeCode.equals(ROAD_CODE_THREE)){
			return JourneyClassifications.RoadTypeClassification.URBAN;
		}
		else if(roadTypeCode.equals(ROAD_CODE_FOUR)){
			return JourneyClassifications.RoadTypeClassification.URBAN;
		}
		else if(roadTypeCode.equals(ROAD_CODE_FIVE)){
			return JourneyClassifications.RoadTypeClassification.URBAN;
		}
		else if(roadTypeCode.equals(ROAD_CODE_SIX)){
			return JourneyClassifications.RoadTypeClassification.RURAL;
		}
		return null;
	}

	public JourneyClassifications.RoadSpeedClassification getSpeedLimit(String coordinates){
		speedUrlStringBuilder.append("https://api.tomtom.com/lbs/services/reverseGeocode/3/json?point=");
		coordinates = coordinates.replaceAll("\\s+", "");
		speedUrlStringBuilder.append(coordinates);
		speedUrlStringBuilder.append("&type=Regional&projection=EPSG4326&language=en&key=v9dmxqkhmve3nrpg6f5nbhar");

		HttpsURLConnection conn;
		try {
			URL roadUrl = new URL(speedUrlStringBuilder.toString());
			conn = (HttpsURLConnection) roadUrl.openConnection();
			conn.setRequestMethod("GET");

			if(conn.getResponseCode() != 200){
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String result = br.readLine();

			JSONObject returnData = new JSONObject(result);
			JSONObject reverseGeo = returnData.getJSONObject("reverseGeoResponse");
			JSONArray  geoResult = reverseGeo.getJSONArray("reverseGeoResult");
			JSONObject information = geoResult.getJSONObject(0);
			int speedLimit = Integer.parseInt(information.getString("maxSpeedKph"));

			speedUrlStringBuilder.setLength(0);

			if(speedLimit == 20){
				return JourneyClassifications.RoadSpeedClassification.TWENTY;
			}else if(speedLimit == 50){
				return JourneyClassifications.RoadSpeedClassification.FIFTY;
			}else if(speedLimit == 60){
				return JourneyClassifications.RoadSpeedClassification.SIXTY;
			}else if(speedLimit == 80){
				return JourneyClassifications.RoadSpeedClassification.EIGHTY;
			}else if(speedLimit == 100){
				return JourneyClassifications.RoadSpeedClassification.HUNDRED;
			}else{
				return JourneyClassifications.RoadSpeedClassification.MISCELLANEOUS;
			}


		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;

	}
}