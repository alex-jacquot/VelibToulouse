import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import model.Position;
import model.Station;

public class JSONReader {
	public static ArrayList<Station> getStations(String json) {
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
}
