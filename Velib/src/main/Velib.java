package main;

/*Velib.java:
 * entry point of the program**/

import request.IORequestManager;
import java.io.IOException;
import java.util.ArrayList;

import model.JSONReader;
import model.Station;
import model.StationHandler;
import view.MainWindow;

public class Velib {

	public static int MINIMUM_BIKES = 1;//No bikes = Uncharged

	public static void main(String[] args) {
		try {
			StationHandler sh = StationHandler.getInstance();//call it before updating stations
			System.out.println("Initializing Handler..");

			IORequestManager.updateDynamicStations();// Update the JSON dynamic information of the stations via API call
			System.out.println("Requesting API Info for stations..");
			String dynamicsString = IORequestManager.getDynamicStations();// Get the string content of the updated JSON
																		// file

			sh.getInstance().updateStations(JSONReader.readStation(dynamicsString));

			//System.out.println(sh.getStationsList());// Put all info in an ArrayList for further manipulation
			// System.out.println(stations.size());
			
			System.out.println("Filtering uncharged stations..");
			ArrayList<Station> filteredStations = StationHandler.getInstance().getUncharged();
			//System.out.println(filteredStations);
			System.out.println(filteredStations.size() + " stations currently empty");

			System.out.println("API call from Google Maps Directions API..");
			ArrayList<Station> furthestStations = StationHandler.getInstance().filterFurthestStations();
			System.out.println("API call generating the Google Static Map..");
			System.out.println("Rendering..");
			MainWindow.create();// Creation of the main window containing the google image

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
