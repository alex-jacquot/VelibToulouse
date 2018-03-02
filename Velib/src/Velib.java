import java.io.IOException;

public class Velib {
	
	public static int MINIMUM_BIKES = 10;

	public static void main(String[] args) {
		try {
			RequestManager.updateDynamicStations();
			String dynamicsString = RequestManager.getDynamicStations();
			System.out.println(JSONReader.getStations(dynamicsString));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}				
	}

}
