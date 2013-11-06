package de.unima.sempoi.server.model.freebase;

public class Attraction extends Poi {
	
	@Override
	public String toString() {
		return "Attraction " +  this.getName() + " (" + this.getLocation() + ")";
	}
	
}
