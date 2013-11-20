package de.unima.sempoi.server.adapter.processing;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import de.unima.sempoi.server.model.dbpedia.DbpediaSight;
import de.unima.sempoi.server.model.export.City;
import de.unima.sempoi.server.model.export.LatLng;
import de.unima.sempoi.server.model.export.Sight;
import de.unima.sempoi.server.model.freebase.Attraction;
import de.unima.sempoi.server.model.freebase.FreebaseCity;
import de.unima.sempoi.server.model.freebase.GeoLocation;
import de.unima.sempoi.server.model.freebase.Location;

public class Merger {
	
	private static final String FREEBASE = "Freebase";
	private static final String DBPEDIA = "Dbpedia";
	
	public List<City> merge(Map<FreebaseCity, Map<String, DbpediaSight>> data) {
		List<City> cities = new ArrayList<City>();
		for(Entry<FreebaseCity, Map<String, DbpediaSight>> entry : data.entrySet()) {
			cities.add(this.createCity(entry.getKey(), entry.getValue()));
		}
		return cities;
	}
	
	private City createCity(FreebaseCity freebaseCity, Map<String, DbpediaSight> dbpediaSights) {
		City city = new City();
		
		city.setName(freebaseCity.getName());
		city.addId(FREEBASE, freebaseCity.getId());
		GeoLocation location = freebaseCity.getLocation();
		city.setLocation(location == null ? null :
			new LatLng(location.getLatitude(), location.getLongitude()));
		
		List<Sight> sights = new ArrayList<Sight>();
		for(Attraction attraction : freebaseCity.getAttractions()) {
			DbpediaSight dbpediaSight = dbpediaSights.get(attraction.getName());
			sights.add(this.createSight(freebaseCity, attraction, dbpediaSight));
		}
		
		city.setSights(sights);
		
		StringBuffer in = new StringBuffer("");
		String separator = "";
		for(Location containedBy : freebaseCity.getContainedBy()) {
			in.append(separator);
			in.append(containedBy.getName());
			separator = ", ";
		}
		city.setIn(in.toString());
		
		return city;
	}
	
	private Sight createSight(FreebaseCity city, Attraction attraction, DbpediaSight dbpediaSight) {
		Sight sight = new Sight();
		sight.setLocation(this.getLocation(city, attraction));
		sight.setName(attraction.getName());
		sight.addId(FREEBASE, attraction.getId());
		if(dbpediaSight != null) {
			sight.addId(DBPEDIA, dbpediaSight.getDbpediaUrl());
			sight.setDescription(dbpediaSight.getDescription());
			sight.setPictures(dbpediaSight.getImages());
			sight.setWikiUrl(dbpediaSight.getWikiUrl());
			sight.setTypes(dbpediaSight.getTypes());
		}
		return sight;
	}
	
	private LatLng getLocation(FreebaseCity city, Attraction attraction) {
		GeoLocation location = attraction.getLocation();
		if(location == null) {
			location = city.getLocation();
		}
		if(location != null) {
			return new LatLng(location.getLatitude(), location.getLongitude());
		}
		return null;
	}
	
}
