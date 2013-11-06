package de.unima.sempoi.server.model.freebase;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class Poi extends Location {
	
	@SerializedName("/location/location/geolocation")
	private List<GeoLocation> locations;
	
	public GeoLocation getLocation() {
		return locations == null || locations.size() == 0 ? null : locations.get(0);
	}

}
