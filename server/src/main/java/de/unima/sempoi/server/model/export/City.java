package de.unima.sempoi.server.model.export;

import java.util.List;

public class City {
	
	@SuppressWarnings("unused")
	private String name;
	
	@SuppressWarnings("unused")
	private LatLng location;
	
	@SuppressWarnings("unused")
	private List<Sight> sights;
	
	@SuppressWarnings("unused")
	private String in;

	public void setName(String name) {
		this.name = name;
	}

	public void setLocation(LatLng locaction) {
		this.location = locaction;
	}

	public void setSights(List<Sight> sights) {
		this.sights = sights;
	}

	public void setIn(String in) {
		this.in = in;
	}
	
}
