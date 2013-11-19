package de.unima.sempoi.server.model.freebase;

public class Location {
	
	private String id;
	private String name;

	public String getName() {
		return name;
	}
	
	public String getId() {
		return id;
	}

	@Override
	public String toString() {
		return this.id + ": " + this.name;
	}
	
}
