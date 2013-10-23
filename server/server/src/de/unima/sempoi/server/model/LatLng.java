package de.unima.sempoi.server.model;

public class LatLng {

	private long lat;
	private long lng;
	
	
	public LatLng(long lat, long lng) {
		super();
		this.lat = lat;
		this.lng = lng;
	}
	
	public long getLat() {
		return lat;
	}
	public void setLat(long lat) {
		this.lat = lat;
	}
	public long getLng() {
		return lng;
	}
	public void setLng(long lng) {
		this.lng = lng;
	}
}
