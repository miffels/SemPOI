package de.unima.sempoi.server.model.dbpedia;

import java.util.List;

public class DbpediaSight {
	
	private String label;
	private String description;
	private String wikiUrl;
	private String photoUrl;
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

	public String toString() {
		return "DbpediaSight " + this.dbpediaUrl + ": " + this.getLabel();
	}

}
