package de.unima.sempoi.server.model.export;

import java.util.List;

public class City extends Poi {
	
	@SuppressWarnings("unused")
	private List<Sight> sights;
	
	@SuppressWarnings("unused")
	private String in;

	public void setSights(List<Sight> sights) {
		this.sights = sights;
	}

	public void setIn(String in) {
		this.in = in;
	}
	
}
