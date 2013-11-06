package de.unima.sempoi.server.adapter.dbpedia;

import com.hp.hpl.jena.query.QuerySolution;

import de.unima.sempoi.server.model.dbpedia.DbpediaSight;

public class SightFactory {
	
	public static DbpediaSight createSightFrom(QuerySolution result) {
		String label = result.get("label").toString();
    	String description = result.get("comment").toString();
    	String wikiUrl = result.get("wikiLink").toString();
    	String photoUrl = result.get("photos").toString();
    	String dbpediaUrl = result.get("sight").toString();
    	return new DbpediaSight(
    			label.substring(0, label.length() - 3),
    			description.substring(0, description.length() - 3),
    			wikiUrl,
    			photoUrl,
    			dbpediaUrl);
	}

}
