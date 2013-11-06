package de.unima.sempoi.server.adapter.dbpedia;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSet;

import de.unima.sempoi.server.model.dbpedia.DbpediaSight;

public class DbpediaAdapter implements Callable<Map<String, DbpediaSight>> {
	
	private Set<String> sightNames;
	
	public DbpediaAdapter(Set<String> sightNames) {
		this.sightNames = sightNames;		
	}
	
	public Map<String, DbpediaSight> query() {
		Query query = QueryFactory.create(Dbpedia.getQuery(sightNames));
		QueryExecution qexec = QueryExecutionFactory.sparqlService("http://dbpedia.org/sparql", query);
		Map<String, DbpediaSight> sights = new HashMap<String, DbpediaSight>();
		try {
		    ResultSet results = qexec.execSelect();
		    for (; results.hasNext();) {
		    	DbpediaSight sight = SightFactory.createSightFrom(results.next()); 
		    	sights.put(sight.getLabel(), sight);
		    }
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
