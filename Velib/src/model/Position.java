package model;

public class Position {
	double latitude;
	double longitude;

	public Position(double lat, double lng) {
		this.latitude = lat;
		this.longitude = lng;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	@Override
	public String toString() {

		return "(" + latitude + "," + longitude + ")";
	}

}
