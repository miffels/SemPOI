package de.unima.sempoi.server.model.freebase;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.google.gson.annotations.SerializedName;

public class Response {
	
	@SerializedName("result")
	private List<FreebaseCity> cities;

	public Set<FreebaseCity> getCities() {
		return new LinkedHashSet<FreebaseCity>(this.cities);
	}
	
	@Override
	public String toString() {
		return this.cities.toString();
	}
	
}
