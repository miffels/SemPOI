package de.unima.sempoi.server.model.export;

import java.util.List;
import java.util.Map;

public class City extends Poi {
	
	@SuppressWarnings("unused")
	private List<Sight> sights;
	
	@SuppressWarnings("unused")
	private Map<String, Integer> types;
	
	@SuppressWarnings("unused")
	private String in;

	public void setSights(List<Sight> sights) {
		this.sights = sights;
	}
	
	public void setTypes(Map<String, Integer> types) {
		this.types = types;
	}

	public void setIn(String in) {
		this.in = in;
	}
	
}
