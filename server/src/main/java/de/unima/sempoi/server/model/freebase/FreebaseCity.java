package de.unima.sempoi.server.model.freebase;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.gson.annotations.SerializedName;

public class FreebaseCity extends Poi {
	
	@SerializedName("tourist_attractions")
	private List<Attraction> attractions;
	
	@SerializedName("/location/location/containedby")
	private List<Location> containedBy;
	
	public List<Location> getContainedBy() {
		return containedBy;
	}

	public List<Attraction> getAttractions() {
		return attractions;
	}
	
	public Set<String> getAttractionNames() {
		Set<String> attractionNames = new HashSet<String>();
		for(Location attraction : this.attractions) {
			attractionNames.add(attraction.getName());
		}
		return attractionNames;
	}
	
	@Override
	public String toString() {
		return "City " + this.getName() + " (" + this.getLocation() + ") " +
					this.containedBy + " " + this.attractions;
	}
}
