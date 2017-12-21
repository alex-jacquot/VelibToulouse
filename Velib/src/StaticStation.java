
public class StaticStation {
	private double number;
	private String name;
	private String address;
	private double latitude;
	private double longitude;

	public StaticStation(double number, String name, String address, double latitude, double longitude) {
		super();
		this.number = number;
		this.name = name;
		this.address = address;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	@Override
	public String toString() {
		return "StaticStation [number=" + number + ", name=" + name + ", address=" + address + ", latitude=" + latitude
				+ ", longitude=" + longitude + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof StaticStation) {
			StaticStation ss = (StaticStation) obj;
			return ss.getNumber() == this.getNumber();
		}
		return false;
	}

	public double getNumber() {
		return number;
	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	public double getLatitude() {
		return latitude;
	}

	public double getLongitude() {
		return longitude;
	}

}
