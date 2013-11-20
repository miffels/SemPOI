package de.unima.sempoi.server.model.export;

import java.util.List;
import java.util.Set;

public class City extends Poi {
	
	@SuppressWarnings("unused")
	private List<Sight> sights;
	
	@SuppressWarnings("unused")
	private Set<String> types;
	
	@SuppressWarnings("unused")
	private String in;

	public void setSights(List<Sight> sights) {
		this.sights = sights;
	}
	
	public void setTypes(Set<String> types) {
		this.types = types;
	}

	public void setIn(String in) {
		this.in = in;
	}
	
}
