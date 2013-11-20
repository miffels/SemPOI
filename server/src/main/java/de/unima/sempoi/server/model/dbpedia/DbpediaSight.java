package de.unima.sempoi.server.model.dbpedia;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DbpediaSight {
	
	private String label;
	private String description;
	private String wikiUrl;
	private String photoUrl;
	private Set<String> types = new HashSet<String>();
	private String dbpediaUrl;
	
	private List<String> images;
	
	public DbpediaSight(String label, String description, String wikiUrl,
			String photoUrl, String dbpediaUrl) {
		super();
		this.label = label;
		this.description = description;
		this.wikiUrl = wikiUrl;
		this.photoUrl = photoUrl;
		this.dbpediaUrl = dbpediaUrl;
	}

	public String getLabel() {
		return label;
	}

	public String getDescription() {
		return description;
	}

	public String getWikiUrl() {
		return wikiUrl;
	}

	public String getPhotoUrl() {
		return photoUrl;
	}

	public String getDbpediaUrl() {
		return dbpediaUrl;
	}
	
	public List<String> getImages() {
		return images;
	}

	public void setImages(List<String> images) {
		this.images = images;
	}
	
	public Set<String> getTypes() {
		return this.types;
	}

	public String toString() {
		return "DbpediaSight " + this.dbpediaUrl + ": " + this.getLabel();
	}

	public void addType(String type) {
		this.types.add(type);
	}

}
