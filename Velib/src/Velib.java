
/*Velib.java:
 * entry point of the program**/

import request.IORequestManager;
import java.io.IOException;
import java.util.ArrayList;

import model.Station;
import model.StationHandler;
import view.MainWindow;

public class Velib {

	public static int MINIMUM_BIKES = 1;

	public static void main(String[] args) {
		try {
			StationHandler sh = StationHandler.getInstance();//call it before updating stations

			IORequestManager.updateDynamicStations();// Update the JSON dynamic information of the stations via API call
			String dynamicsString = IORequestManager.getDynamicStations();// Get the string content of the updated JSON
																		// file

			sh.getInstance().updateStations(JSONReader.readStation(dynamicsString));

			//System.out.println(sh.getStationsList());// Put all info in an ArrayList for further manipulation
			// System.out.println(stations.size());
			
			ArrayList<Station> filteredStations = StationHandler.getInstance().filterStationsByUncharged();
			System.out.println(filteredStations);
			/* System.out.println(filteredStations.size()); */

			ArrayList<Station> furthestStations = StationHandler.getInstance().filterFurthestStations();
			MainWindow.create();// Creation of the main window containing the google image

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
