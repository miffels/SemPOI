package de.unima.sempoi.server.model.export;

import java.util.HashMap;
import java.util.Map;

public class Poi {
	
	private Map<String, String> ids = new HashMap<String, String>();
	
	@SuppressWarnings("unused")
	private String name;
	
	@SuppressWarnings("unused")
	private LatLng location;
	
	public void addId(String name, String id) {
		this.ids.put(name, id);
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public void setLocation(LatLng location) {
		this.location = location;
	}

}
