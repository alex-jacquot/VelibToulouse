import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class CSVReader {

	public static void main(String[] args) {
		System.out.println(getStationsFromCSV("data/Toulouse.csv"));
	}
	
	public static ArrayList<StaticStation> getStationsFromCSV(String filePath) {
		System.setProperty("file.encoding", "UTF-8");

		ArrayList<StaticStation> listeStations = new ArrayList<StaticStation>();

		String line = "";
		String cvsSplitBy = ",";
		BufferedReader br = null;

		try {

			br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "UTF8"));
			br.readLine();
			while ((line = br.readLine()) != null) {

				// use comma as separator
				String[] toulouse = line.split(cvsSplitBy);

				StaticStation s = new StaticStation(Integer.parseInt(toulouse[0]), toulouse[1], toulouse[2],
						Double.parseDouble(toulouse[3]), Double.parseDouble(toulouse[4]));
				listeStations.add(s);

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();

		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		/*
		 * for (StaticStation s : listeStations) System.out.println(s);
		 */
		return listeStations;
	}

}
