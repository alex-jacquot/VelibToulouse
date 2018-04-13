package model;

/*JSONReader especially designed for reading */

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import main.Velib;

public class JSONReader {
	public static ArrayList<Station> readStation(String json) {
		ArrayList<Station> result = new ArrayList<>();
		JSONArray jsonArray = new JSONArray(json);

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonobject = jsonArray.getJSONObject(i);

			int number = jsonobject.getInt("number");
			String name = jsonobject.getString("name");
			String address = jsonobject.getString("address");

			JSONObject posObj = jsonobject.getJSONObject("position");
			Position pos = new Position(posObj.getDouble("lat"), posObj.getDouble("lng"));

			boolean state = (jsonobject.getInt("available_bike_stands") < Velib.MINIMUM_BIKES) ? true : false;
			Station s = new Station(number, name, address, pos, state);
			result.add(s);
		}

		return result;
	}

	public static String readRoutes(String json) {
		String result = "";
		// JSONArray jsonArray = new JSONArray(json);
		JSONObject globalJSON = new JSONObject(json);
		JSONArray routes = globalJSON.getJSONArray("routes");
		JSONObject polyline = routes.getJSONObject(0).getJSONObject("overview_polyline");
		String points = polyline.getString("points");
		// JSONObject routes = jsonArray.getJSONObject(0);
		//System.out.println("Global:" + globalJSON.toString());
		//System.out.println("Routes:" + routes);
		//System.out.println("Polyline:" + polyline);
		//System.out.println("Points:" + points);
		return points;
	}
}
