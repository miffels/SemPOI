package de.unima.sempoi.server.adapter.dbpedia;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QueryParseException;
import com.hp.hpl.jena.query.ResultSet;

import de.unima.sempoi.server.model.dbpedia.DbpediaSight;

public class DbpediaAdapter implements Callable<Map<String, DbpediaSight>> {
	
	private Set<String> sightNames;
	
	private SightFactory sightFactory = new SightFactory();
	
	public DbpediaAdapter(Set<String> sightNames) {
		this.sightNames = sightNames;		
	}
	
	public Map<String, DbpediaSight> query() {
		String queryString = Dbpedia.getQuery(sightNames);
		
		Query query = null;
		try {
			query = QueryFactory.create(queryString);
		} catch(QueryParseException e) {
			e.printStackTrace();
		}
		
		QueryExecution qexec = QueryExecutionFactory.sparqlService("http://dbpedia.org/sparql", query);
		Map<String, DbpediaSight> sights = new HashMap<String, DbpediaSight>();
		try {
		    ResultSet results = qexec.execSelect();
		    for (; results.hasNext();) {
		    	DbpediaSight sight = this.sightFactory.createSightFrom(results.next()); 
		    	sights.put(sight.getLabel(), sight);
		    }
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
		   qexec.close();
		}
		return sights;
	}

	@Override
	public Map<String, DbpediaSight> call() throws Exception {
		return this.query();
	}
	
}
