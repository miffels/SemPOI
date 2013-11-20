package de.unima.sempoi.server.adapter.dbpedia;

import java.util.HashMap;
import java.util.Map;

import com.hp.hpl.jena.query.QuerySolution;

import de.unima.sempoi.server.model.dbpedia.DbpediaSight;

public class SightFactory {
	
	/**
	 * Index over sights, because we get multiple solutions per sight (multiple rdf:types)
	 */
	private Map<String, DbpediaSight> sightIndex = new HashMap<String, DbpediaSight>();
	
	public DbpediaSight createSightFrom(QuerySolution result) {
		String label = result.get("label").toString();
    	String description = result.get("comment").toString();
    	String wikiUrl = result.get("wikiLink").toString();
    	String photoUrl = result.get("photos").toString();
    	String type = Types.cleanTypeName(result.get("type").toString());
    	String dbpediaUrl = result.get("sight").toString();
    	
    	DbpediaSight sight = this.sightIndex.containsKey(dbpediaUrl) ?
    			this.sightIndex.get(dbpediaUrl) : new DbpediaSight(
    	    			label.substring(0, label.length() - 3),
    	    			description.substring(0, description.length() - 3),
    	    			wikiUrl,
    	    			photoUrl,
    	    			dbpediaUrl);
    			
    	this.sightIndex.put(dbpediaUrl, sight);
    			
    	sight.addType(type);
    	
    	return sight;
	}
	
}
