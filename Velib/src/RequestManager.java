import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

public class RequestManager {
	// GET
	// https://api.jcdecaux.com/vls/v1/stations?contract={contract_name}&apiKey={api_key}

	public static String getDynamicsStations() throws IOException {

		String requestURL = new String(
				"https://api.jcdecaux.com/vls/v1/stations?contract=Toulouse&apiKey=" + getAPIKey());

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
		return result.toString();

	}

	public static String getAPIKey() throws IOException {
		BufferedReader br = null;
		String key = "";
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream("data/APIkey.txt"), "UTF8"));
			key = br.readLine();
		} catch (UnsupportedEncodingException | FileNotFoundException e) {
			// TODO Auto-generated catch block
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
		return key;
	}

	public static void main(String[] args) {
		try {
			System.out.println(getAPIKey());
			System.out.println(getDynamicsStations());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
