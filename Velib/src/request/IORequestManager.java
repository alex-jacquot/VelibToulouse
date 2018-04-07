package request;


/**
 * Request Manager: handles all HTTP requests and Files creating and reading. Doesn't "JSONize" all the manipulated objects 
	TODO: File too big
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.json.JSONArray;

import model.Position;

public class IORequestManager {

	/**
	 * 
	 * @return the API key
	 * @throws IOException
	 */
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

	/**
	 * Send an HTTP request to the API server and save the dynamic info about
	 * stations in the "dynamics.json" file
	 * 
	 * @throws IOException
	 */
	public static void updateDynamicStations() throws IOException {

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
		printTo(result.toString(), "data/dynamics.json");
	}

	/**
	 * Gets the content of the local JSON file under a String format
	 * 
	 * @return
	 */
	public static String getDynamicStations() {
		String jsonString = null;
		try {
			jsonString = readFile("data/dynamics.json", StandardCharsets.UTF_8);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		}
		return jsonString;
	}

	public static String getStaticStations() {
		String jsonString = null;
		try {
			jsonString = readFile("data/statics.json", StandardCharsets.UTF_8);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		}

		return jsonString;
	}



	/**
	 * Returns the file content under the form of a String with proper encoding
	 * 
	 * @param path
	 * @param encoding
	 * @return
	 * @throws IOException
	 */
	static String readFile(String path, Charset encoding) throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, encoding);
	}

	public static void printTo(String content, String path) throws IOException {
		try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path), "utf-8"))) {
			writer.write(content);
		}
	}
	

	public static void main(String[] args) {
		try {
			System.out.println(getAPIKey());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
