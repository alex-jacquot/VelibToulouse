package model;

/**
 * StationHandler.java
 * Stores the updated station information
 * Follows the Singleton Design Pattern: Only 1 instance of it at any given time. useful to store all the dynamic infos about stations since we 
 * are not supposed to update the stations info more than once during a single run of the app
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StationHandler {

	private StationHandler() {
	}

	/** Unique Instance */
	private static StationHandler INSTANCE = new StationHandler();

	private ArrayList<Station> stationList = new ArrayList<>();

	/** Singleton Access Point */
	public static StationHandler getInstance() {
		return INSTANCE;
	}

	public ArrayList<Station> getStationsList() {
		return stationList;
	}

	public ArrayList<Station> getUncharged() {
		ArrayList<Station> result = new ArrayList<>();
		for (Station s : this.stationList) {
			if (s.getState()) {
				result.add(s);
			}
		}
		return result;
	}

	public void updateStations(ArrayList<Station> newList) {
		this.stationList = newList;
	}

	/**
	 * Simple unoptimized but functional algorithm that takes the 2 farthest
	 * uncharged stations
	 */
	public ArrayList<Station> filterFurthestStations() {
		ArrayList<Station> furthestStations = new ArrayList<>();
		double max = 0, min = 1000;
		Station sMax = null, sMin = null;
		for (Station s : this.stationList) {
			double coeff = s.getLatitude() + s.getLongitude();
			if (max < coeff) {
				max = coeff;
				sMax = s;
			}
			if (min > coeff) {
				min = coeff;
				sMin = s;
			}
		}
		furthestStations.add(sMax);
		furthestStations.add(sMin);
		return furthestStations;
	}
	
	
}