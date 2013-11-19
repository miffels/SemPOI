package de.unima.sempoi.server.model.export;

import java.util.List;

public class Sight extends Poi {

	@SuppressWarnings("unused")
	private String description;
	
	@SuppressWarnings("unused")
	private List<String> pictures;
	
	@SuppressWarnings("unused")
	private String wikiUrl;

	public void setWikiUrl(String wikiUrl) {
		this.wikiUrl = wikiUrl;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setPictures(List<String> pictures) {
		this.pictures = pictures;
	}

}
