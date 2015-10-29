package org.p4p.backpocketdriver.driverlog.test;

import org.json.JSONException;
import org.p4p.backpocketdriver.driverlog.webservice.TomTomService;

public class TomTomTest {

	public static void main(String[] args) throws JSONException {
		
		TomTomService service = new TomTomService();

//       System.out.println(service.getRoadTypeFromService("-36.87377714,174.7772995"));
//		System.out.println(service.getRoadTypeFromService("-36.90926457,174.89836556"));
//
//		System.out.println(service.getSpeedLimit("-36.906797,174.896647"));
//		System.out.println(response);

	   
	   service.getSpeedLimit("-36.922905, 174.847244");

//		System.out.println(service.getRouteDistanceFromService(new String[]{"-36.91182605,174.86956573","-36.88122658,174.85223707"}));

		
		
		
//		try{
//			URL url = new URL("https://api.tomtom.com/lbs/services/flowSegmentData/3/absolute/15/json?key=v9dmxqkhmve3nrpg6f5nbhar&point=-36.899253,174.808972");
////			URL url = new URL("https://api.tomtom.com/lbs/services/trafficIcons/3/s3/6841263.950712,511972.674418,6886056.049288,582676.925582/11/1335294634919/json?key=v9dmxqkhmve3nrpg6f5nbhar&projection=EPSG900913&geometries=original&language=en&expandCluster=true");
//			HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
//			conn.setRequestMethod("GET");
////			conn.setRequestProperty("Accept", "application/json");
//			
//			if (conn.getResponseCode() != 200) {
//				throw new RuntimeException("Failed : HTTP error code : "
//						+ conn.getResponseCode());
//			}
//	 
//			BufferedReader br = new BufferedReader(new InputStreamReader(
//				(conn.getInputStream())));
//	 
//			String output = br.readLine();
//			System.out.println(output);
////			System.out.println("Output from Server .... \n");
//			JSONObject obj = new JSONObject(output);
//			JSONObject flowObj = obj.getJSONObject("flowSegmentData");
//			String frc = flowObj.getString("frc");
//			System.out.println(frc);
//			System.out.println(flowObj.toString());
//			
////			JSONArray pageName = obj.getJSONArray("routes");
////			JSONObject summary = pageName.getJSONObject(0);
////			JSONObject summaryObj = summary.getJSONObject("summary");
////			System.out.println(summaryObj.toString());
////			
//			
//			
////			while ((output = br.readLine()) != null) {
////				System.out.println(output);
////			}
//	 
//			conn.disconnect();
//			
//		}catch(MalformedURLException e){
//			e.printStackTrace();
//		}catch(IOException e){
//			e.printStackTrace();
//		}
//		
//	}

	}
}
