package de.unima.sempoi.server.model.freebase;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.gson.annotations.SerializedName;

public class Response {
	
	@SerializedName("result")
	private List<Result> results;

	public List<Result> getResults() {
		return results;
	}
	
	public List<Attraction> getAttractions() {
		List<Attraction> attractions = new ArrayList<Attraction>();
		for(Result result : this.results) {
			attractions.addAll(result.getAttractions());
		}
		return attractions;
	}
	
	public Set<String> getAttractionNames() {
		Set<String> attractionNames = new HashSet<String>();
		for(Attraction attraction : this.getAttractions()) {
			attractionNames.add(attraction.getName());
		}
		return attractionNames;
	}
	
}
