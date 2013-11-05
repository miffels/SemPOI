package de.unima.sempoi.server.adapter.dbpedia;

import java.util.Set;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSet;

public class DbpediaAdapter {
	
	public void query(Set<String> sightNames) {
		Query query = QueryFactory.create(Dbpedia.getQuery(sightNames));
		QueryExecution qexec = QueryExecutionFactory.sparqlService("http://dbpedia.org/sparql", query);
		try {
		    ResultSet results = qexec.execSelect();
		    for (; results.hasNext();) {
		    	System.out.println(results.next());
		    }
		}
		finally {
		   qexec.close();
		}
	}
	
}
