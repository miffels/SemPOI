package de.unima.sempoi.server.model.freebase;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class Result {
	
	@SerializedName("tourist_attractions")
	private List<Attraction> attractions;

	public List<Attraction> getAttractions() {
		return attractions;
	}

}
