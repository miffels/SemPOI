package de.unima.sempoi.server.model;

import java.util.List;

public class City {
	
	private String name;
	private LatLng location;
	private List<Sight> sights;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LatLng getLocation() {
		return location;
	}

	public void setLocation(LatLng locaction) {
		this.location = locaction;
	}

	public List<Sight> getSights() {
		return sights;
	}

	public void setSights(List<Sight> sights) {
		this.sights = sights;
	}

}
