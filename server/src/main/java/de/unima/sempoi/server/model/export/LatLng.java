package de.unima.sempoi.server.model.export;

public class LatLng {

	private double lat;
	private double lng;

	public LatLng(double lat, double lng) {
		super();
		this.lat = lat;
		this.lng = lng;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(long lat) {
		this.lat = lat;
	}

	public double getLng() {
		return lng;
	}

	public void setLng(long lng) {
		this.lng = lng;
	}
}
