package de.unima.sempoi.server.model.export;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Sight extends Poi {

	@SuppressWarnings("unused")
	private String description;
	
	@SuppressWarnings("unused")
	private List<String> pictures;
	
	@SuppressWarnings("unused")
	private String wikiUrl;
	
	private Set<String> types = new HashSet<String>();

	public void setWikiUrl(String wikiUrl) {
		this.wikiUrl = wikiUrl;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setPictures(List<String> pictures) {
		this.pictures = pictures;
	}
	
	public void setTypes(Set<String> types) {
		this.types = types;
	}

	public Set<String> getTypes() {
		return types;
	}
	
}
