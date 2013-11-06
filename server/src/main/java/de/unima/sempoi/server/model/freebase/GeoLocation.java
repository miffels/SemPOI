package de.unima.sempoi.server.model.freebase;

import com.google.gson.annotations.SerializedName;

public class GeoLocation {
	
	@SerializedName("/location/geocode/latitude")
	private double latitude;
	
	@SerializedName("/location/geocode/longitude")
	private double longitude;

	public double getLatitude() {
		return latitude;
	}

	public double getLongitude() {
		return longitude;
	}

}
