package request;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import model.Position;
import model.Station;
import model.StationHandler;

public class GMapRequestManager {
	/**
	 * Gets the Google Maps API directions path between 2 points
	 * 
	 * @return The JSON data containing the path between 2 points
	 * @throws IOException 
	 */

	public static String getGooglePath(Position origin, Position destination) throws IOException {
		StationHandler sh = StationHandler.getInstance();
		String requestURL = new String("https://maps.googleapis.com/maps/api/directions/json?origin=" + origin
				+ "&destination=" + destination + "&alternatives=false&key=AIzaSyAG3bhdapKXj9TpHlic9DgluyQ0Be_Hw5A");

		StringBuilder result = new StringBuilder();
		URL url = new URL(requestURL);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String line;
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}
		rd.close();
		IORequestManager.printTo(result.toString(), "data/directions.json");
		return line;
	}
	
	public static String getStaticMap() {
		StationHandler sh = StationHandler.getInstance();
		String imageUrl = "https://maps.googleapis.com/maps/api/staticmap?center=43.60,1.44&zoom=13&size=630x600&maptype=roadmap&key=AIzaSyCcuKRt6PkePi0QmKBFR0i3G-DvXz0ToRg&format=jpg";
		for(Station s: sh.filterStationsByUncharged()) {
			imageUrl += "&markers=color:green%7Clabel:S%7C"+ s.getLatitude() + "," + s.getLongitude();
		}
		System.out.println(imageUrl);
		return imageUrl;
		
	}

}
