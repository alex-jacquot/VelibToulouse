import java.util.ArrayList;

public class StationManager {
	ArrayList<StaticStation> statics;
	ArrayList<StaticStation> dynamics;

	public StationManager() {
		statics = new ArrayList<>();
		dynamics = new ArrayList<>();
	}

	public ArrayList<StaticStation> getStatics() {
		return statics;
	}

	public ArrayList<StaticStation> getDynamics() {
		return dynamics;
	}

}
