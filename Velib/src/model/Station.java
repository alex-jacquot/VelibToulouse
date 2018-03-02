package model;

public class Station {
	private double number;
	private String name;
	private String address;
	private Position position;
	public boolean isState() {
		return state;
	}

	private boolean state;

	public Station(double number, String name, String address, Position position, boolean state) {
		super();
		this.number = number;
		this.name = name;
		this.address = address;
		this.position = position;
		this.state = state;
	}

	@Override
	public String toString() {
		return "Station [number=" + number + ", name=" + name  + ", pos=" + position + ", needsCharging="+state+  "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Station) {
			Station ss = (Station) obj;
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
		return position.getLatitude();
	}

	public double getLongitude() {
		return position.getLongitude();
	}

}